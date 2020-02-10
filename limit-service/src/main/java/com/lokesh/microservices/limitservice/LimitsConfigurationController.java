package com.lokesh.microservices.limitservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	@Autowired
	private Configuration configuration;
	@GetMapping("/limits")

	public LimitConfiguration retrivelLimitsFromConfigurations(){
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
	public LimitConfiguration retriveConfiguration() {
		throw new RuntimeException("Not available");
	}
	public LimitConfiguration fallbackRetrieveConfiguration() {
		return new LimitConfiguration(9,999);
	}
	
}
