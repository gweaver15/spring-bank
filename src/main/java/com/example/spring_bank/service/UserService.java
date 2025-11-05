package com.example.spring_bank.service;

import com.example.spring_bank.model.User;

import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.Map;

import java.util.Optional;

import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
	private final Map<Long, User> store = new ConcurrentHashMap<>();

	private final AtomicLong idGen = new AtomicLong(1);

	public Collection<User> findAll() {
		return store.values();
	}

	public Optional<User> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public User create(User user) {
		long id = idGen.getAndIncrement();
		user.setId(id);
		store.put(id, user);
		return user;
	}

	public Optional<User> update(Long id, User user) {
		if (!store.containsKey(id)) return Optional.empty();
		user.setId(id);
		store.put(id, user);
		return Optional.of(user);
	}

	public boolean delete(Long id) {
		return store.remove(id) != null;
	}
}
