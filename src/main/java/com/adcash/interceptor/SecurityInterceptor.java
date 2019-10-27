package com.adcash.interceptor;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 
 * @author krishnasfamily
 * 
 * This class will be used for OWASP Secure Headers & CSRF token check.
 *
 */
public class SecurityInterceptor  implements HandlerInterceptor{
	private static final Logger logger = LogManager.getLogger(SecurityInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler) throws Exception {
    	Enumeration<String> headerNames = request.getHeaderNames();
    	while(headerNames.hasMoreElements()) {
    	  String headerName = (String)headerNames.nextElement();
    	  logger.info("" + headerName);
    	  logger.info("" + request.getHeader(headerName));
    	}
        return true;
    }

    
}
