package com.bt.sample.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bt.sample.core.services.MyFirstServicePrintLog;

@Component(service = MyFirstServicePrintLog.class,immediate = true)
public class MyFirstServicePrintLogImpl implements MyFirstServicePrintLog{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public String PrintLog() {
		logger.info("I am from Service");
		return "Some String from a Service";
	}
	
	@Activate 
	public void Activated()
	{
		logger.info(" Service Activated");
	}

	@Deactivate 
	public void DeActivated()
	{
		logger.info(" Service De-Activated");
	}
	
	@Modified // OSGi Configs
	public void ModifiedMethod()
	{
		logger.info(" Service Modified");
	}

}
