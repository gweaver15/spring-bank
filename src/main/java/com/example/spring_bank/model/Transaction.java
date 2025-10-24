package com.example.spring_bank.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
	private Long id;
	private Long fromAccountId;
	private Long toAccountId;
	private BigDecimal amount;
	private LocalDateTime timestamp;
	private String type; // e.g. TRANSFER, DEPOSIT, WITHDRAWAL
	private String status; // e.g. PENDING, COMPLETED, FAILED
	private String description;

	public Transaction() {
		this.amount = BigDecimal.ZERO;
		this.timestamp = LocalDateTime.now();
		this.status = "PENDING";
	}

	public Transaction(Long id, Long fromAccountId, Long toAccountId, BigDecimal amount, LocalDateTime timestamp, String type, String status, String description) {
		this.id = id;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount == null ? BigDecimal.ZERO : amount;
		this.timestamp = timestamp == null ? LocalDateTime.now() : timestamp;
		this.type = type;
		this.status = status;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount == null ? BigDecimal.ZERO : amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transaction that = (Transaction) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"id=" + id +
				", fromAccountId=" + fromAccountId +
				", toAccountId=" + toAccountId +
				", amount=" + amount +
				", timestamp=" + timestamp +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
