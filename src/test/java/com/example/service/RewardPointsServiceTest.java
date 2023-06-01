package com.example.service;

import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.RewardPointsRequest;
import com.example.model.RewardsSummary;

@SpringBootTest
public class RewardPointsServiceTest {
	
	@Autowired
	private RewardPointsService pointsService;
	
	@Test
	public void calculateRewardPointsTest() {
		
		RewardPointsRequest rewardPointsRequest = new RewardPointsRequest();
		rewardPointsRequest.setTimePeriod(2020);
		List<RewardsSummary> rewardsSummary = pointsService.calculateRewardPoints(rewardPointsRequest);
		org.springframework.util.Assert.noNullElements(rewardsSummary, "Assert fails due to test case failed");
		
	}

}
