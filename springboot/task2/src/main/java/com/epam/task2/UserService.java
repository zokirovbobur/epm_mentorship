package com.epam.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements GeneralService<UserEntity> {

	@Autowired
	private UserRepository repository;

	public Optional<UserEntity> findByUserName(String userName){
		return repository.findByUserName(userName);
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public UserEntity save(UserEntity object) {
		return repository.save(object);
	}

	@Override
	public List<UserEntity> getAll() {
		return repository.findAll();
	}

	public void removeById(Long id){
		repository.deleteById(id);
	}
}
