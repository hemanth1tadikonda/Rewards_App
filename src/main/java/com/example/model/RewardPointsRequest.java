package com.example.model;

import lombok.Data;

import java.util.List;
@Data
public class RewardPointsRequest {

	private int timePeriod;
	private Integer amount;
	private Long customerId;
	private  String transactionDate;

}
