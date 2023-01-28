package project.ute.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import project.ute.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, QueryByExampleExecutor<User>{
	Optional<User> getByEmail(String email);

}
