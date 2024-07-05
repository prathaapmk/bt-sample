package com.bt.sample.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class CommonUtils {
	
	
	public static ResourceResolver getReadResolver(ResourceResolverFactory resolverFactory) throws LoginException
	{
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "readService");
		ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);
		return resolver;
	}
	
	public static ResourceResolver getWriteResolver(ResourceResolverFactory resolverFactory) throws LoginException
	{
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "writeService");
		ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);
		return resolver;
	}
	
	public static ResourceResolver getupdateResolver(ResourceResolverFactory resolverFactory) throws LoginException
	{
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "updateservice");
		ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param);
		return resolver;
	}

	
}
