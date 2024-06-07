package com.bt.sample.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/*
 * Documentation for SLing Models
//https://sling.apache.org/documentation/bundles/models.html
*/
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiFeildDemoModel {

	
	@ValueMapValue
	private String[]  states;
	
	@ChildResource
	List<CountrywithCurrencies> multicurandcountry;
	
	/*
	 * 
	 * @PostContruct
	 * @SlingObject
	 * @RequestAttributte
	 * adaptable=SlinghttpRequest.class
	 * adapters= if you want to use Interafce
	 * @Default
	 */

	public String[] getStates() {
		return states;
	}

	public List<CountrywithCurrencies> getMulticurandcountry() {
		return multicurandcountry;
	}
	
	
	
}
