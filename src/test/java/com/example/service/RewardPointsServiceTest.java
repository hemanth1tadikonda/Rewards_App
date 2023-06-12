package com.example.service;

import java.util.List;

import com.example.model.Transaction;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.RewardPointsRequest;
import com.example.model.RewardsSummary;

@SpringBootTest
public class RewardPointsServiceTest {

	@Autowired
	private RewardPointsService pointsService;

	@Autowired
	private Transformer transformer;
	@Test
	public void calculateRewardPointsTest() {

		RewardPointsRequest rewardPointsRequest = new RewardPointsRequest();
		rewardPointsRequest.setTimePeriod(2020);
		rewardPointsRequest.setAmount(1000);
		rewardPointsRequest.setCustomerId(123l);
		rewardPointsRequest.setTransactionDate("2023-01-01");
		Transaction transaction = pointsService.calculateRewardPoints(rewardPointsRequest);
		//	Mockito.when(transformer.addTransaction(Mockito.anyInt(),Mockito.anyLong(),Mockito.anyString())).thenReturn(new Transaction());
		org.springframework.util.Assert.notNull(transaction, "Assert fails due to test case failed");

	}

}