package com.example.model;

import java.util.List;

public class RewardsSummary {

	private String customerName;
	private List<MonthlyRewards> rewardsPerMonth;
	private int totalRewardsEarned;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<MonthlyRewards> getRewardsPerMonth() {
		return rewardsPerMonth;
	}

	public void setRewardsPerMonth(List<MonthlyRewards> rewardsPerMonth) {
		this.rewardsPerMonth = rewardsPerMonth;
	}

	public int getTotalRewardsEarned() {
		return totalRewardsEarned;
	}

	public void setTotalRewardsEarned(int totalRewardsEarned) {
		this.totalRewardsEarned = totalRewardsEarned;
	}

}
