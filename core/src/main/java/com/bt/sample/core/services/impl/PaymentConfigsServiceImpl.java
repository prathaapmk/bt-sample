package com.bt.sample.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.bt.sample.core.configurations.SampleOsgiConfigurations;
import com.bt.sample.core.services.PaymentConfigs;

@Component(service = PaymentConfigs.class,immediate = true)
@Designate(ocd = SampleOsgiConfigurations.class)
public class PaymentConfigsServiceImpl implements PaymentConfigs{
	
	private String url;
	private String datareadUrl;
	
	
	@Activate
	public void readConfig(SampleOsgiConfigurations sampleOsgiConfigurations)
	{
		url=sampleOsgiConfigurations.paymentUrl();
		datareadUrl=sampleOsgiConfigurations.jsonUrl();
		
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String getPaymentUrl() {
		
		return url;
	}

	@Override
	public String getJsonURL() {
		
		return datareadUrl;
	}

	
}
