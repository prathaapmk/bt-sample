package com.bt.sample.core.models;

import java.net.*;
import java.io.*;

public class ReadURlData {

	public static void main(String[] args) throws Exception {

		URL oracle = new URL("https://dummyjson.com/todos/1");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();
	}
}
