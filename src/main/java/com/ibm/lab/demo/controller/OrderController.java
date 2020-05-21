package com.ibm.lab.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ibm.lab.demo.domain.Item;
import com.ibm.lab.demo.domain.User;

@RestController
@RequestMapping("/users")
public class OrderController {
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


	@GetMapping("/{name}")
	public List<User> getProducts(@PathVariable String name) {

		logger.info("User service " + name);
		List<User> usersList = new ArrayList<User>();

		List<Item> itemList = (List<Item>) restTemplate.exchange("http://demo-product:8082/products/" + name + "/items",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Item>>() {
		}).getBody();
		usersList.add(new User(name, "myemail@google.com", itemList));

		return usersList;

	}

}
