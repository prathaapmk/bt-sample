package com.bt.sample.core.models;

import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageWithTextModel {

	@ValueMapValue
	@Required
	private String name;
	
	@ValueMapValue
	private String text;
	
	@ValueMapValue
	private String path;
	
	@ValueMapValue
	private String bottomlefttext;
	
	@ValueMapValue
	private String toplefttext;
	
	@ValueMapValue
	private String toprighttext;
	
	@ValueMapValue
	private String bottomrighttext;
	
	@ValueMapValue
	private String centeredtext;


	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getPath() {
		return path;
	}

	public String getBottomlefttext() {
		return bottomlefttext;
	}

	public String getToplefttext() {
		return toplefttext;
	}

	public String getToprighttext() {
		return toprighttext;
	}

	public String getBottomrighttext() {
		return bottomrighttext;
	}

	public String getCenteredtext() {
		return centeredtext;
	}
	
	
	
}
