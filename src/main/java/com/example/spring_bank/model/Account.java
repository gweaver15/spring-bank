package com.example.spring_bank.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
	private Long id;
	private String accountNumber;
	private BigDecimal balance;
	private Long userId; // owner reference
	private String type; // e.g. CHECKING, SAVINGS

	public Account() {
		this.balance = BigDecimal.ZERO;
	}

	public Account(Long id, String accountNumber, BigDecimal balance, Long userId, String type) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance == null ? BigDecimal.ZERO : balance;
		this.userId = userId;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance == null ? BigDecimal.ZERO : balance;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(id, account.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", accountNumber='" + accountNumber + '\'' +
				", balance=" + balance +
				", userId=" + userId +
				", type='" + type + '\'' +
				'}';
	}
}
