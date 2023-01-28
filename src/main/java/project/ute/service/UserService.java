package project.ute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import project.ute.model.User;

public interface UserService{

	void delete(User entity);

	public boolean checkLogin(User user);

	Optional<User> findById(String id);

	public <S extends User> boolean save(S entity); 
	
	Optional<User> loadUserByEmail(String email);
	
	public void deleteById(String id);
	
	public List<User> findAll();
	
}
