package com.bt.sample.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import lombok.Getter;

/*
 * Documentation for SLing Models
//https://sling.apache.org/documentation/bundles/models.html
*/
@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = "/apps/bt-sample-project/components/demoonmultifieldsithmodel")
@Exporter(name="jackson",extensions = "json")
@Getter
public class MultiFeildDemoModel {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
	@ValueMapValue //@Inject
	private String[]  states;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@ChildResource
	List<CountrywithCurrencies> multicurandcountry;
	
	@SlingObject
	ResourceResolver rr;
	
	@SlingObject
	Resource currentResource;
	
	
	
	  @RequestAttribute /// This is used to get the value from the sightly, you are passing the data from sightly. 
	  private String value1;
	  
	  @RequestAttribute 
	  private String value2;
	  
	  private String manuplulatedData;
	  
	//HERE YOU CAN MANUPLUATE THE DATAAA AND CALL ANY KIND OF METHODS HERE
	  @PostConstruct public void init() { 
		  logger.
	  info("I am executing after the Inject/ValueMapvalue and RequestAttrbutes");
	String str=  slingHttpServletRequest.getPathInfo();
	  //By Using Resource API
	String path1[]= str.split(".html");
	Resource r = rr.getResource(path1[0].concat("/jcr:content"));
	
	ValueMap pageValues = r.adaptTo(ValueMap.class);
	
	String pageTitle = pageValues.get("jcr:title",String.class);
	//BY Using Page API
	
	PageManager pm = rr.adaptTo(PageManager.class);
	Page p = pm.getPage(path1[0]);
	String s = p.getTitle();
	  
	  logger.info("Completed the Init Method");
	  
	  }

	/*
	 * 
	 * @PostContruct
	 * @SlingObject
	 * @RequestAttributte
	 * adaptable=SlinghttpRequest.class
	 * adapters= if you want to use Interafce
	 * @Default
	 */
	
	
}
