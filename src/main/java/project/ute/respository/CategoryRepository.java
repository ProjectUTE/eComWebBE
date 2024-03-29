package project.ute.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.ute.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	@Query("SELECT c FROM Category c WHERE c.status = true")
	public List<Category> getAllCategoryStillInBusiness();
	
	@Query("SELECT c FROM Category c WHERE c.id = ?1")
	public Category getCategoryById(String id);
}
