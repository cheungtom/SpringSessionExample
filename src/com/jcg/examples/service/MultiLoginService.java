package com.jcg.examples.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionManager;

public class MultiLoginService
{
		public static void createMultiLogin(HttpServletRequest httpRequest)
		{
				HttpSessionManager sessionManager =(HttpSessionManager) httpRequest.getAttribute(HttpSessionManager.class.getName());
				System.out.println("(((( MultiLoginService Started. HttpSessionManager = " + sessionManager);
				
				String alias = httpRequest.getParameter("_s");
				System.out.println("Request Parameter '_s' = " + alias);
				
				@SuppressWarnings("unchecked")
				SessionRepository<Session> sessionRepository =
                (SessionRepository<Session>) httpRequest.getAttribute(SessionRepository.class.getName());
				System.out.println("SessionRepository = " + sessionRepository);
				
				for(Map.Entry<String, String> entry : sessionManager.getSessionIds(httpRequest).entrySet()) {
		            String aliasId = entry.getKey();
		            String sessionId = entry.getValue();
		            
		            Session storedSession = sessionRepository.getSession(sessionId);
		            System.out.println("Stored Spring session object = " + storedSession + ". " + (storedSession != null ? "Stored 'Username' attribute = " + storedSession.getAttribute("Username") : ""));
		            
		            HttpSession httpSession = httpRequest.getSession(false);
		            
		            if(storedSession != null && storedSession.getAttribute("Username") != null && (httpSession.getAttribute("Username") == null || ((String)httpSession.getAttribute("Username")).trim().length() == 0)) 
		            {
		            	httpSession.setAttribute("Username", storedSession.getAttribute("Username"));
		            	System.out.println("Set Http session with Stored Spring Session value for attribute 'Username'. Value = " + httpSession.getAttribute("Username"));
		            }
		            
		            
		            System.out.println(aliasId +" : "+sessionId);
				}
						
				if(alias == null || "".equals(alias.trim()))
				{
						alias = sessionManager.getNewSessionAlias(httpRequest);
						System.out.println("Craete a new alias = " + alias);
				}
				
				//Use request attribute alias to enable multiple session at the same browser, enabled SSO 
				httpRequest.setAttribute("alias",alias);
				System.out.println("))) - SSO alias value = " + httpRequest.getAttribute("alias"));
		}
}
