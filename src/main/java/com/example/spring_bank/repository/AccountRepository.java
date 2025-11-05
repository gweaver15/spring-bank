package com.example.spring_bank.repository;

import com.example.spring_bank.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepository {
	private final Map<Long, Account> store = new ConcurrentHashMap<>();
	private final AtomicLong idGen = new AtomicLong(1);

	public Collection<Account> findAll() {
		return store.values();
	}

	public Optional<Account> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public Account save(Account account) {
		if (account.getId() == null) {
			long id = idGen.getAndIncrement();
			account.setId(id);
		}
		if (account.getBalance() == null) account.setBalance(java.math.BigDecimal.ZERO);
		store.put(account.getId(), account);
		return account;
	}

	public boolean existsById(Long id) {
		return store.containsKey(id);
	}

	public void deleteById(Long id) {
		store.remove(id);
	}
}
