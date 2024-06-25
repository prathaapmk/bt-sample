package com.bt.sample.core.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.bt.sample.core.services.ReadDummyJson;

@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/practice", 
		"sling.servlet.resourceTypes=/apps/bt-sample-project/components/callservletajaxcall" ,
		"sling.servlet.methods=GET",
		"sling.servlet.extensions=txt" })
public class SaveDatainAEM extends SlingSafeMethodsServlet {

	@Reference
	ReadDummyJson readDummyJson;

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceresolver = request.getResourceResolver();
		try {

			// Getting the Data from 3rd Party APi via a Service
			String jsonfromService = readDummyJson.getDatafromDummyJsonApi();

			// Converting the String into Resource by using ResourceResolver
			Resource resource = resourceresolver.getResource("/content/bt-sample-project/data");

			if (Objects.nonNull(resource)) {
				// This MVM is used to Update/remove any of the Properties in JCR in AEM
				ModifiableValueMap map = resource.adaptTo(ModifiableValueMap.class);

				// to add the Data to AEM, ADD the values in the for Key value Pair
				map.put("json", jsonfromService);
				resourceresolver.commit();
			}
		} catch (Exception e) {
			response.getWriter().write("Json Creation is Unsuccesful" + e.getMessage());
			e.printStackTrace();
		}
		response.setContentType("text/plain");
		response.getWriter().write("Json Creation is Successfull");

	}

}
