package com.example.spring_bank.controller;

import com.example.spring_bank.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private static final Map<Long, Transaction> store = new ConcurrentHashMap<>();
	private static final AtomicLong idGen = new AtomicLong(1);

	@GetMapping
	public Collection<Transaction> list() {
		return store.values();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> get(@PathVariable Long id) {
		Transaction t = store.get(id);
		if (t == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(t);
	}

	@PostMapping
	public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
		long id = idGen.getAndIncrement();
		transaction.setId(id);
		if (transaction.getAmount() == null) transaction.setAmount(java.math.BigDecimal.ZERO);
		if (transaction.getTimestamp() == null) transaction.setTimestamp(LocalDateTime.now());
		if (transaction.getStatus() == null) transaction.setStatus("PENDING");
		store.put(id, transaction);
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody Transaction transaction) {
		Transaction existing = store.get(id);
		if (existing == null) return ResponseEntity.notFound().build();
		transaction.setId(id);
		if (transaction.getTimestamp() == null) transaction.setTimestamp(existing.getTimestamp());
		if (transaction.getAmount() == null) transaction.setAmount(existing.getAmount());
		store.put(id, transaction);
		return ResponseEntity.ok(transaction);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Transaction removed = store.remove(id);
		if (removed == null) return ResponseEntity.notFound().build();
		return ResponseEntity.noContent().build();
	}
}
