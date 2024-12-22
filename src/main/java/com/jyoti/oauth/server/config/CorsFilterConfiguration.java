package com.jyoti.oauth.server.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilterConfiguration implements Filter {
	
	@Value("${cors.allowed.uri}")
	private String allowedUri;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		//Need to allow for swagger url 
		//resp.setHeader("Access-Control-Allow-Origin", allowedUri);
		resp.setHeader("Access-Control-Allow-Methods", "GET");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization");
		
		if("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			resp.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			chain.doFilter(request, response);
		}

	}

}
