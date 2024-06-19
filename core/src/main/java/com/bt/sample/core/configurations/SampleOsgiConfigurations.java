package com.bt.sample.core.configurations;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(description = "Bt Sample Configuration Description", name = "BT Sample Config")
public @interface SampleOsgiConfigurations {

	@AttributeDefinition(name = "Payment Gateway URL", description = "Payment Config url", type = AttributeType.STRING)
	String paymentUrl() default "https://www.payment.gateway.com";
	
	@AttributeDefinition(name = "JSOn  URL", description = "JSON Config url", type = AttributeType.STRING)
	String jsonUrl() default "https://dummyjson.com/todos/10";

}
