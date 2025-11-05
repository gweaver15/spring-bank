package com.example.spring_bank.repository;

import com.example.spring_bank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransactionRepository {
	private final Map<Long, Transaction> store = new ConcurrentHashMap<>();
	private final AtomicLong idGen = new AtomicLong(1);

	public Collection<Transaction> findAll() {
		return store.values();
	}

	public Optional<Transaction> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public Transaction save(Transaction tx) {
		if (tx.getId() == null) {
			long id = idGen.getAndIncrement();
			tx.setId(id);
		}
		if (tx.getAmount() == null) tx.setAmount(java.math.BigDecimal.ZERO);
		if (tx.getTimestamp() == null) tx.setTimestamp(java.time.LocalDateTime.now());
		if (tx.getStatus() == null) tx.setStatus("PENDING");
		store.put(tx.getId(), tx);
		return tx;
	}

	public boolean existsById(Long id) {
		return store.containsKey(id);
	}

	public void deleteById(Long id) {
		store.remove(id);
	}
}
