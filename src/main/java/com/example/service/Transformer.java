package com.example.service;

import com.example.model.Transaction;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Transformer {


    public Transaction addTransaction(int amount, Long customerId, String transDate) {

        Transaction t = new Transaction(amount);
        t.setRewards(calculateRewards(amount));
        t.setTransactionDate(parseDate(transDate));
        t.setCustomerid(customerId);
      return t;
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
