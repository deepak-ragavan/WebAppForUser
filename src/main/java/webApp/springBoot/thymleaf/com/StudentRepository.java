package webApp.springBoot.thymleaf.com;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {

	
	public StudentEntity findById(long id);
	
	@Query(value = "SELECT * FROM student_entity", nativeQuery = true)
	List<StudentEntity> getallstudent();
	
	@Query(value = "SELECT * FROM student_entity WHERE id = ?1", nativeQuery = true)
	StudentEntity findusingid(long id);
	
	@Query(value = "UPDATE student_entity SET hobby = ?1, knownlanguage = ?2, location = ?3 , designation = ?4 WHERE id=?5", nativeQuery= true)
	@Modifying
	public void updateStudent(String hobby,String knownlanguage,String location,String designation,long id);
	
	@Query(value="DELETE FROM student_entity WHERE id= ?1", nativeQuery=true)
	@Modifying
	public void DeleteStudent(long id);
	
//	@Query(value="INSERT INTO student_entity (hobby,knownlanguage,location,designation) VALUES (?1,?2,?3,?4)",nativeQuery=true)
//	@Modifying
//	public void saveDetails(String hobby,String knownlanguage,String location,String designation);
	
	
	
}
