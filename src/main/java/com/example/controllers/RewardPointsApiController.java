package com.example.controllers;

import java.util.List;

import com.example.model.Customer;
import com.example.model.Transaction;
import com.example.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.RewardPointsRequest;
import com.example.model.RewardsSummary;
import com.example.service.RewardPointsService;

@RequestMapping(value = "/api")
@RestController
public class RewardPointsApiController {

	@Autowired
	private RewardPointsService rewardPointsService;
	@Autowired
	CustomerRepository customerRepository;

//	@GetMapping(value = "/rewards")
//	public ResponseEntity<Transaction> getAllRewardsSummary() {
//
//		RewardPointsRequest request = new RewardPointsRequest();
//		request.setTimePeriod(3);
//
//		Transaction transaction = rewardPointsService.calculateRewardPoints(request);
//
//		return new ResponseEntity<>(transaction, HttpStatus.OK);
//	}

	@PostMapping(value = "/rewards")
	public ResponseEntity<?> calculateRewards(@RequestBody RewardPointsRequest rewardPointsRequest) {

		RewardPointsRequest request = new RewardPointsRequest();
		request.setTimePeriod(3);

		Transaction transaction = rewardPointsService.calculateRewardPoints(request);

		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}



	@GetMapping(value = "/{customerId}/rewards")
	public ResponseEntity<RewardsSummary> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){

		RewardsSummary customerRewards = rewardPointsService.getRewardsByCustomerId(customerId);
		return new ResponseEntity<>(customerRewards,HttpStatus.OK);
	}



	@PostMapping(value = "/customer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer){

		Customer customerRewards = customerRepository.save(customer);
		return new ResponseEntity<>(customerRewards,HttpStatus.OK);
	}
}
