package com.bt.sample.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.bt.sample.core.services.MyFirstServicePrintLog;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class,adapters = SampleInterface.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SampleModelWithAdapters implements SampleInterface{

	@ValueMapValue
	private String sampleText;
	
	@ValueMapValue
	private String sampleTextArea;
	
	@OSGiService  //@Inject
	MyFirstServicePrintLog myFirstServicePrintLog;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@ValueMapValue
	private String path;
	
	@SlingObject
	Resource currentResource;
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	
	private String pageTitleViaSling;
	
	private String pageTitleViaAEM;
	
	private String stringFromService;
	
	@Override
	public String getPath() {
		return path;
	}

	@Override
	public String getSampleText() {
		// TODO Auto-generated method stub
		return sampleText;
	}

	@Override
	public String getSampleTextArea() {
		// TODO Auto-generated method stub
		return sampleTextArea;
	}
	
	@PostConstruct
	public void init()
	{
		
		// Assinging via Sling Model
		stringFromService = myFirstServicePrintLog.PrintLog();
		
		/// By Using Sling 
		
		
		String paths[] = slingHttpServletRequest.getPathInfo().split(".html");

		Resource currentPagePath = resourceResolver.getResource(paths[0].concat("/jcr:content"));

		ValueMap vm = currentPagePath.adaptTo(ValueMap.class);
		pageTitleViaSling = vm.get("jcr:title", String.class);

		//// By Using AEM

		PageManager pm = resourceResolver.adaptTo(PageManager.class);
		Page page = pm.getPage(paths[0]);
		pageTitleViaAEM = page.getTitle(); 
		 
		
		
	}

	public String getPageTitleViaSling() {
		return pageTitleViaSling;
	}

	public String getPageTitleViaAEM() {
		return pageTitleViaAEM;
	}

	public String getStringFromService() {
		return stringFromService;
	}

	

	
	

}
