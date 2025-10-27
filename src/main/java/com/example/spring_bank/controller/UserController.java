package com.example.spring_bank.controller;

import com.example.spring_bank.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Map<Long, User> store = new ConcurrentHashMap<>();
	private static final AtomicLong idGen = new AtomicLong(1);

	@GetMapping
	public Collection<User> list() {
		return store.values();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		User u = store.get(id);
		if (u == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(u);
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		long id = idGen.getAndIncrement();
		user.setId(id);
		store.put(id, user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		User existing = store.get(id);
		if (existing == null) return ResponseEntity.notFound().build();
		user.setId(id);
		store.put(id, user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		User removed = store.remove(id);
		if (removed == null) return ResponseEntity.notFound().build();
		return ResponseEntity.noContent().build();
	}
}
