package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int amount;
	private int rewards;
	private LocalDate transactionDate;
	private Long customerid;

	public Transaction(int amount) {
		this.amount=amount;
	}

	private LocalDate parseDate(String transdate) {
		if(StringUtils.isEmpty(transdate)){
			return null;
		}
		LocalDate lDate = LocalDate.parse(transdate);
		return lDate;
	}
}
