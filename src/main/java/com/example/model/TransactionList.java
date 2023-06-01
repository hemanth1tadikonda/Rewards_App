package com.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionList {

	private List<Transaction> transactions;

	public TransactionList() {
		transactions = new ArrayList<>();
		addTransaction(120, 1111, "2022-09-02");
		addTransaction(154, 1111, "2022-10-02");
		addTransaction(54, 1111, "2022-11-02");
		addTransaction(200, 2222, "2022-10-02");
		addTransaction(20, 2222, "2022-11-02");
		addTransaction(300, 3333, "2022-11-02");
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void addTransaction(int amount, int customerId, String transDate) {

		Transaction t = new Transaction(amount);
		t.setRewards(calculateRewards(amount));
		t.setTransactionDate(parseDate(transDate));
		t.setCustomerid(customerId);
		this.transactions.add(t);
	}

	private LocalDate parseDate(String transdate) {
		LocalDate lDate = LocalDate.parse(transdate);
		return lDate;
	}

	private int calculateRewards(int amount) {
		if (amount >= 50 && amount < 100) {
			return amount - 50;
		} else if (amount > 100) {
			return (2 * (amount - 100) + 50);
		}
		return 0;
	}

}
