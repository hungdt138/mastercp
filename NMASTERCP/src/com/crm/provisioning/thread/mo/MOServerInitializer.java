package com.crm.provisioning.thread.mo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class MOServerInitializer extends ChannelInitializer<SocketChannel>
{
	private MOServerListener moServer = null;
	
	public MOServerInitializer(MOServerListener callbackServer)
	{
		super();
		this.moServer = callbackServer;
	}
	
	@Override
	protected void initChannel(SocketChannel channel) throws Exception
	{
        // Create a default pipeline implementation.
        ChannelPipeline p = channel.pipeline();

        p.addLast("decoder", new HttpRequestDecoder());
        p.addLast("encoder", new HttpResponseEncoder());
		p.addLast("handler", new MOServerHandler(moServer));
	}

}
