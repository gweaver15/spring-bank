package com.example.spring_bank.service;

import com.example.spring_bank.model.Account;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AccountService {
	private final Map<Long, Account> store = new ConcurrentHashMap<>();
	private final AtomicLong idGen = new AtomicLong(1);

	public Collection<Account> findAll() {
		return store.values();
	}

	public Optional<Account> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public Account create(Account account) {
		long id = idGen.getAndIncrement();
		account.setId(id);
		if (account.getBalance() == null) account.setBalance(java.math.BigDecimal.ZERO);
		store.put(id, account);
		return account;
	}

	public Optional<Account> update(Long id, Account account) {
		if (!store.containsKey(id)) return Optional.empty();
		account.setId(id);
		if (account.getBalance() == null) account.setBalance(store.get(id).getBalance());
		store.put(id, account);
		return Optional.of(account);
	}

	public boolean delete(Long id) {
		return store.remove(id) != null;
	}
}
