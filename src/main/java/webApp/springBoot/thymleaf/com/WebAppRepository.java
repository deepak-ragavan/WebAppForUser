package webApp.springBoot.thymleaf.com;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebAppRepository extends CrudRepository<WebAppEntity, Long> {
	public WebAppEntity findByEmail(String email);
	public WebAppEntity findById(long id);
	public WebAppEntity findByIsLogged(int islogged);
}
