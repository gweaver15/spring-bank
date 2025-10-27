package com.example.spring_bank.controller;

import com.example.spring_bank.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private static final Map<Long, Account> store = new ConcurrentHashMap<>();
	private static final AtomicLong idGen = new AtomicLong(1);

	@GetMapping
	public Collection<Account> list() {
		return store.values();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> get(@PathVariable Long id) {
		Account a = store.get(id);
		if (a == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(a);
	}

	@PostMapping
	public ResponseEntity<Account> create(@RequestBody Account account) {
		long id = idGen.getAndIncrement();
		account.setId(id);
		if (account.getBalance() == null) account.setBalance(java.math.BigDecimal.ZERO);
		store.put(id, account);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
		Account existing = store.get(id);
		if (existing == null) return ResponseEntity.notFound().build();
		account.setId(id);
		if (account.getBalance() == null) account.setBalance(existing.getBalance());
		store.put(id, account);
		return ResponseEntity.ok(account);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Account removed = store.remove(id);
		if (removed == null) return ResponseEntity.notFound().build();
		return ResponseEntity.noContent().build();
	}
}
