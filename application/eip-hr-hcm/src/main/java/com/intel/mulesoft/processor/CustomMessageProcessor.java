package com.intel.mulesoft.processor;

import org.mule.api.MuleException;
import org.mule.api.security.Authentication;
import org.springframework.security.core.userdetails.User;
import org.mule.api.lifecycle.Callable;
import org.mule.api.MuleEventContext;

public class CustomMessageProcessor implements Callable {

	//Custom code to extract username from the Spring Security Module -rarober1
	@Override
	public Object onCall(MuleEventContext eventContext) throws MuleException {
		try{
			Authentication auth = eventContext.getSession().getSecurityContext().getAuthentication();
			User user = (User) auth.getPrincipal();
			eventContext.getMessage().setInvocationProperty("securityUserName", user.getUsername());
		}
		catch(Exception ex)
		{
			eventContext.getMessage().setInvocationProperty("securityUserName", "none");
		}

		return eventContext.getMessage(); 
	}
}