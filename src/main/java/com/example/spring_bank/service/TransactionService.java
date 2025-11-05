package com.example.spring_bank.service;

import com.example.spring_bank.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransactionService {
	private final Map<Long, Transaction> store = new ConcurrentHashMap<>();
	private final AtomicLong idGen = new AtomicLong(1);

	public Collection<Transaction> findAll() {
		return store.values();
	}

	public Optional<Transaction> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public Transaction create(Transaction tx) {
		long id = idGen.getAndIncrement();
		tx.setId(id);
		if (tx.getAmount() == null) tx.setAmount(java.math.BigDecimal.ZERO);
		if (tx.getTimestamp() == null) tx.setTimestamp(java.time.LocalDateTime.now());
		if (tx.getStatus() == null) tx.setStatus("PENDING");
		store.put(id, tx);
		return tx;
	}

	public Optional<Transaction> update(Long id, Transaction tx) {
		if (!store.containsKey(id)) return Optional.empty();
		tx.setId(id);
		if (tx.getTimestamp() == null) tx.setTimestamp(store.get(id).getTimestamp());
		if (tx.getAmount() == null) tx.setAmount(store.get(id).getAmount());
		store.put(id, tx);
		return Optional.of(tx);
	}

	public boolean delete(Long id) {
		return store.remove(id) != null;
	}
}
