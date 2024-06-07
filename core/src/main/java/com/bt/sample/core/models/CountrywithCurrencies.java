package com.bt.sample.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class CountrywithCurrencies {
	
	@ValueMapValue
	private String country;
	
	@ValueMapValue
	private String currency;

	public String getCountry() {
		return country;
	}

	public String getCurrency() {
		return currency;
	}
	
	
	

}
