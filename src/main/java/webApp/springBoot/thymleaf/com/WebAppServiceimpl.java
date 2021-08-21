package webApp.springBoot.thymleaf.com;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebAppServiceimpl implements WebAppService {
	
	@Autowired
	WebAppRepository webAppRepository;
	
	@Autowired
	StudentRepository studentRepository;
	

	@Override
	public WebAppEntity saveUserDetails(WebAppEntity webApp) {
		// TODO Auto-generated method stub
		return webAppRepository.save(webApp);
	}

	@Override
	public Boolean CheckUserExists(String email, String password) {
		// TODO Auto-generated method stub
		WebAppEntity user= webAppRepository.findByEmail(email);
		return ((user != null ? true : false) && user.getEmail().equals(email) && user.getPassword().equals(password)); 
	}

	@Override
	public WebAppEntity getUserDetails(String email) {
		// TODO Auto-generated method stub
		 return webAppRepository.findByEmail(email);
		 				
	}


	@Override
	public Iterable<WebAppEntity> getAllUser() {
		// TODO Auto-generated method stub
		return webAppRepository.findAll();
	}

	@Override
	public void deleteUserDetails(long id) {
		// TODO Auto-generated method stub
		 webAppRepository.deleteById(id);
	}

	@Override
	public WebAppEntity setisLoggedin(int isLogged) {
		// TODO Auto-generated method stub
		return webAppRepository.findByIsLogged(isLogged);
	}

	@Override
	public Iterable<StudentEntity> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.getallstudent();
	}

	

	@Override
	public void deleteStudentDetails(long id) {
		// TODO Auto-generated method stub
		studentRepository.DeleteStudent(id);
	}

	@Override
	public StudentEntity getStudentDetails(long id) {
		// TODO Auto-generated method stub
		return studentRepository.findusingid(id);
	}

	@Override
	public void updateStudentDetails(StudentEntity SE) {
		// TODO Auto-generated method stub
		studentRepository.updateStudent(SE.getHobby(), SE.getKnownlanguage(), SE.getLocation(), SE.getDesignation(), SE.getId());
	}

	@Override
	public void saveStudentDetails(StudentEntity SE) {
		// TODO Auto-generated method stub
		studentRepository.save(SE);
		
	}



	
}
