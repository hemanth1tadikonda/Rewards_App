package com.example.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.MonthlyRewards;
import com.example.model.RewardPointsRequest;
import com.example.model.RewardsSummary;
import com.example.model.Transaction;
import com.example.model.TransactionList;

@Service
public class RewardPointsService {

	public List<RewardsSummary> calculateRewardPoints(RewardPointsRequest request) {

		TransactionList list = new TransactionList();
		Map<Integer, List<Transaction>> customerTList = mapToCustomer(list.getTransactions());

		List<RewardsSummary> rewardsSummaries = new ArrayList<>();

		for (Map.Entry<Integer, List<Transaction>> t : customerTList.entrySet()) {
			Customer customer = Customer.getCustomerById(t.getKey());
			if (customer != null) {
				RewardsSummary summary = new RewardsSummary();
				summary.setCustomerName(customer.getCustomerName());
				summary.setRewardsPerMonth(rewardsPerMonth(t.getValue(), request.getTimePeriod()));
				summary.setTotalRewardsEarned(getTotalRewardsEarned(summary.getRewardsPerMonth()));
				rewardsSummaries.add(summary);
			}
		}
		return rewardsSummaries;
	}

	private Map<Integer, List<Transaction>> mapToCustomer(List<Transaction> tList) {

		Map<Integer, List<Transaction>> keyMap = new HashMap<>();

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

}
