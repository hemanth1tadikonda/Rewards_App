package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.RewardPointsRequest;
import com.example.model.RewardsSummary;
import com.example.service.RewardPointsService;

@RequestMapping(value = "/api")
@RestController
public class RewardPointsApiController {

	@Autowired
	private RewardPointsService rewardPointsService;

	@GetMapping(value = "/rewards")
	public ResponseEntity<List<RewardsSummary>> getAllRewardsSummary() {

		RewardPointsRequest request = new RewardPointsRequest();
		request.setTimePeriod(3);

		List<RewardsSummary> rewardsSummaries = rewardPointsService.calculateRewardPoints(request);

		return new ResponseEntity<List<RewardsSummary>>(rewardsSummaries, HttpStatus.OK);
	}

}
