package com.example.spring_bank.repository;

import com.example.spring_bank.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
	private final Map<Long, User> store = new ConcurrentHashMap<>();
	private final AtomicLong idGen = new AtomicLong(1);

	public Collection<User> findAll() {
		return store.values();
	}

	public Optional<User> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	public User save(User user) {
		if (user.getId() == null) {
			long id = idGen.getAndIncrement();
			user.setId(id);
		}
		store.put(user.getId(), user);
		return user;
	}

	public boolean existsById(Long id) {
		return store.containsKey(id);
	}

	public void deleteById(Long id) {
		store.remove(id);
	}
}
