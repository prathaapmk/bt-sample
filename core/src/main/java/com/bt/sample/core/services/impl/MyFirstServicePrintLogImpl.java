package com.bt.sample.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bt.sample.core.services.GetPathService;
import com.bt.sample.core.services.MyFirstServicePrintLog;

@Component(service = MyFirstServicePrintLog.class,immediate = true)
public class MyFirstServicePrintLogImpl implements MyFirstServicePrintLog{

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference(target = "(component.name=com.bt.sample.core.services.impl.GetAssetPathServiceImpl1)")
	GetPathService getPathService;
	
	@Override
	public String PrintLog() {
		logger.info("I am from Service");
		return getPathService.getPathOfTheAsset();
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
