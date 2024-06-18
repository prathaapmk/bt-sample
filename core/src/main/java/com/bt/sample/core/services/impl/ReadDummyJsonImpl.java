package com.bt.sample.core.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.osgi.service.component.annotations.Component;

import com.bt.sample.core.services.ReadDummyJson;

@Component(service = ReadDummyJson.class,immediate = true)
public class ReadDummyJsonImpl implements ReadDummyJson{

	@Override
	public String getDatafromDummyJsonApi() throws Exception{
		URL dummyJsonURL = new URL("https://dummyjson.com/todos/1");
		StringBuilder responseBuilder = new StringBuilder();
	    HttpURLConnection httpCon = (HttpURLConnection) dummyJsonURL.openConnection();
	    httpCon.setDoOutput(true);
	    httpCon.setDoInput(true);

	    int resCode = httpCon.getResponseCode();
	    InputStream is = null;
	    if (resCode == 200) {
	         is = httpCon.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(dummyJsonURL.openStream()));

		String response = null;
        while (true) {
            response = in.readLine();
            if (response == null)
                break;
            responseBuilder.append(response);
        }
   }
   return responseBuilder.toString();
	}

}
