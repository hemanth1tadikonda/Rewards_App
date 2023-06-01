package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Integer customerId;
	private String customerName;

	public Customer(Integer customerId, String customerName) {
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public static Customer getCustomerById(int customerId) {
		List<Customer> list = new ArrayList<>();
		list.add(new Customer(1111, "Robert"));
		list.add(new Customer(2222, "Smith"));
		list.add(new Customer(3333, "Jeffery"));

		for (Customer c : list) {
			if (c.getCustomerId().intValue() == customerId) {
				return c;
			}
		}
		return null;
	}
}
