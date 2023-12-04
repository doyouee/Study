package com.soap.soapListener;

import javax.xml.ws.Endpoint;

public class Exporter {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8888/soap", new EmployeeServiceImpl());
	}
}
