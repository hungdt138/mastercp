package com.crm.provisioning.thread.mo;

import com.crm.provisioning.thread.MOListenerThread;
import com.crm.thread.DispatcherThread;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MOServerListener extends Thread
{
	private MOListenerThread	dispatcher	= null;

	private int					port		= 5000;
	private int					backLog		= 500;
	private int					numThreads	= 50;
	private boolean				closed		= true;

	private ServerBootstrap		bootstrap	= null;
	private Channel				channel		= null;
	private EventLoopGroup		eventGroup	= new NioEventLoopGroup();
	private EventLoopGroup		childGroup	= new NioEventLoopGroup();

	public boolean isClosed()
	{
		return closed;
	}

	public MOServerListener(MOListenerThread dispatcher, int port, int backLog, int numThreads)
	{
		this.port = port;
		this.backLog = backLog;
		this.numThreads = numThreads;
		this.dispatcher = dispatcher;
	}

	public DispatcherThread getDispatcher()
	{
		return dispatcher;
	}

	public void debugMonitor(Object message)
	{
		dispatcher.debugMonitor(message);
	}

	public void logMonitor(Object message)
	{
		if (dispatcher != null)
		{
			dispatcher.logMonitor(message);
		}
	}

	public void shutdown()
	{
		debugMonitor("HTTP server is closing...");

		try
		{
			if ((channel != null) && !isClosed())
			{
				ChannelFuture channelFuture = channel.close();
				channelFuture.awaitUninterruptibly();

				channel.close();
			}
			childGroup.shutdownGracefully();
			eventGroup.shutdownGracefully();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			closed = true;
		}

		debugMonitor("HTTP server closed.");
	}

	public void run()
	{
		debugMonitor("HTTP server is starting...");

		try
		{
			closed = false;

			bootstrap = new ServerBootstrap();
			eventGroup = new NioEventLoopGroup();
			childGroup = new NioEventLoopGroup();

			bootstrap.option(ChannelOption.SO_BACKLOG, backLog);
			bootstrap.option(ChannelOption.SO_LINGER, 1);
			bootstrap.option(ChannelOption.SO_REUSEADDR, true);
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_RCVBUF, 40 * 1024);
			bootstrap.option(ChannelOption.SO_SNDBUF, 40 * 1024);

			bootstrap = bootstrap.group(eventGroup, childGroup);
			bootstrap = bootstrap.channel(NioServerSocketChannel.class);
			bootstrap = bootstrap.childHandler(new MOServerInitializer(this));

			channel = bootstrap.bind(port).sync().channel();

			channel.closeFuture().sync();
		}
		catch (Exception e)
		{
			debugMonitor(e);
		}
		finally
		{
			shutdown();
		}
	}
}
