package com.bt.sample.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.bt.sample.core.services.GetPathService;
import com.bt.sample.core.services.ReadDummyJson;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CallingAServiceinModel {
	
	private String pathofAsset;
	
	private String datafromUrl;
	
	@OSGiService(filter = "(component.name=com.bt.sample.core.services.impl.GetAssetPathServiceImpl1)")
	 GetPathService getPathService;
    @OSGiService
    ReadDummyJson readDummyJson;
	
	@PostConstruct
	public void init() throws Exception
	{
		pathofAsset =	getPathService.getPathOfTheAsset();
		datafromUrl = readDummyJson.getDatafromDummyJsonApi();
	}

	public String getPathofAsset() {
		return pathofAsset;
	}
	

	public String getDatafromUrl() {
		return datafromUrl;
	}
	
}
