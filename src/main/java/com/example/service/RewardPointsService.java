package com.example.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import com.example.model.repository.CustomerRepository;
import com.example.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.MonthlyRewards;
import com.example.model.RewardPointsRequest;
import com.example.model.RewardsSummary;
import com.example.model.Transaction;
@Service
public class RewardPointsService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private Transformer transformer;

	public  Transaction calculateRewardPoints(RewardPointsRequest request) {

		Customer customer= customerRepository.findByCustomerId(request.getCustomerId());
		List<RewardsSummary> rewardsSummaries = new ArrayList<>();
		Transaction transaction= transformer.addTransaction(request.getAmount(),request.getCustomerId(),request.getTransactionDate());

		transaction= transactionRepository.save(transaction);
		if (customer!=null) {
			RewardsSummary summary = new RewardsSummary();
			summary.setCustomerName(customer.getCustomerName());
			summary.setRewardsPerMonth(rewardsPerMonth(Arrays.asList(transaction), request.getTimePeriod()));
			summary.setTotalRewardsEarned(getTotalRewardsEarned(summary.getRewardsPerMonth()));
			rewardsSummaries.add(summary);
		}

		return transaction;
	}

	private Map<Long, List<Transaction>> mapToCustomer(List<Transaction> tList) {

		Map<Long, List<Transaction>> keyMap = new HashMap<>();

		for (Transaction t : tList) {
			List<Transaction> list = keyMap.get(t.getCustomerid());
			if (list != null) {
				list.add(t);
			} else {
				list = new ArrayList<>();
				list.add(t);
				keyMap.put(t.getCustomerid(), list);
			}
		}
		return keyMap;
	}

	private List<MonthlyRewards> rewardsPerMonth(List<Transaction> list, int timePeriod) {

		List<MonthlyRewards> rewards = new ArrayList<>();

		Set<String> monthNames = new HashSet<>();
		int month = LocalDate.now().getMonthValue() - timePeriod;
		list.forEach(t -> {
			if ((t.getTransactionDate().getMonthValue()) > month) {
				monthNames.add(t.getTransactionDate().getMonth().name());
			}
		});

		for (String monthName : monthNames) {
			int totalRewardsPerMonth = 0;
			for (Transaction t : list) {
				String tMonth = t.getTransactionDate().getMonth().name();
				if (tMonth.equals(monthName)) {
					totalRewardsPerMonth += t.getRewards();
				}
			}
			MonthlyRewards monthlyReward = new MonthlyRewards();
			monthlyReward.setMonth(monthName);
			monthlyReward.setRewards(totalRewardsPerMonth);
			rewards.add(monthlyReward);
		}

		return rewards;
	}

	private int getTotalRewardsEarned(List<MonthlyRewards> monthlyRewards) {
		int totalrewards = 0;
		for (MonthlyRewards monthlyReward : monthlyRewards) {
			totalrewards += monthlyReward.getRewards();
		}
		return totalrewards;
	}

	public RewardsSummary getRewardsByCustomerId(Long customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId);
		if(customer == null)
		{
			throw new RuntimeException("Invalid / Missing customer Id ");
		}
		List<Transaction> transactions = transactionRepository.findAllByCustomerid(customerId);
		RewardsSummary rewardsSummary= new RewardsSummary();
		rewardsSummary.setCustomerName(customer.getCustomerName());
		rewardsSummary.setRewardsPerMonth(transactions.stream().map(transaction -> {
			MonthlyRewards monthlyRewards= new MonthlyRewards();
			monthlyRewards.setMonth(transaction.getTransactionDate().getMonth().name());
			monthlyRewards.setRewards(transaction.getRewards());
			return monthlyRewards;
		}).collect(Collectors.toList()));
          return rewardsSummary;
	}
}
