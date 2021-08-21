package webApp.springBoot.thymleaf.com;


public interface WebAppService {
	WebAppEntity saveUserDetails(WebAppEntity webApp);
	public Boolean CheckUserExists(String email,String password);
	WebAppEntity getUserDetails(String email);
	Iterable<WebAppEntity> getAllUser();
	public void deleteUserDetails(long id);
	public WebAppEntity setisLoggedin(int isLogged);
	Iterable<StudentEntity> getAllStudents();
	public void updateStudentDetails(StudentEntity studentEntity);
	public void deleteStudentDetails(long id);
	StudentEntity getStudentDetails(long id);
	public void saveStudentDetails(StudentEntity studentEntity);
}
