/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wap.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author HungDT
 */
public class SubController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("sub");
        String serviceId = request.getParameter("x");
        String timeStamp = request.getParameter("t");
        String securityKey = request.getParameter("k");
        String url = request.getParameter("r");
        String requestId = request.getParameter("rid");
        
        
        
        return mv;
    }

}
