package com.vframework.core;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreServlet.class);
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequestInfo(request);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getRequestInfo(request);
	}
	
	@Override
	public void destroy() {
		
	}
	
	private void getRequestInfo(HttpServletRequest request) {
		String address = request.getRemoteAddr();
		String host = request.getRemoteHost();
		int port = request.getRemotePort();
		String user = request.getRemoteUser();
		
		LOGGER.info("Request info (1) - Host: {}, Address: {}, Port: {}, User: {}", host, address, port, user);
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		long sessionCreationTime = session.getCreationTime();
		long sessionLastAccessedTime = session.getLastAccessedTime();
		long sessionsMaxInactiveInterval = session.getMaxInactiveInterval();
		
		LOGGER.info("Request info (2) - Session: {}, Creation Time: {}, Last Accessed Time: {}, Max Inactive Interval: {}", sessionId, sessionCreationTime, sessionLastAccessedTime, sessionsMaxInactiveInterval);
		
		Enumeration<String> sessionAttributeNames = session.getAttributeNames();
		String attributeName = null;
		Object attribute = null;
		
		while(sessionAttributeNames.hasMoreElements()) {
			attributeName = sessionAttributeNames.nextElement();
			attribute = session.getAttribute(attributeName);
			LOGGER.info("Request info (3) - Session Attribute name: {} value: {} ", attributeName, attribute);
		}
	}
}
