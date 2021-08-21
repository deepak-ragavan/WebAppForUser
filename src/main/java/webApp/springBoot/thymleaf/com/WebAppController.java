package webApp.springBoot.thymleaf.com;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class WebAppController {
	@Autowired
	WebAppService webAppService;
	
	@Autowired
	WebAppRepository repository;
	
	
	@GetMapping("/Registration")
	public ModelAndView userRegistration() {
		System.out.println("hello");
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("Registration");
	    return mv;
	} 
	
	@PostMapping(value="/saveUserDetails", produces = "application/json")
	public ModelAndView saveUserDetails(WebAppEntity webApp) {
		ModelAndView mv = new ModelAndView();
		webAppService.saveUserDetails(webApp);
		mv.setViewName("redirect:/login");
		return mv;
	}
	
	
	@GetMapping("/login")
	public ModelAndView userLogin() {
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("login");
	    return mv;
	}
	
	@PostMapping("/homepage")
	public ModelAndView loginUser(WebAppEntity webApp,RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView();
		String email = webApp.getEmail();
		String password = webApp.getPassword();
	    if(webAppService.CheckUserExists(email,password)) {
	    	 WebAppEntity user = webAppService.getUserDetails(email);
	    	 user.setIsLogged(1);
	    	 webAppService.saveUserDetails(user);
	    	 mv.addObject("listofStudents", webAppService.getAllStudents());
	         mv.setViewName("Home");
	    }
	    else {
	    	redirectAttributes.addFlashAttribute("msg","Login Unsuccessful");
	    	//mv.addObject("msg","Login Unsuccessful");
	    	mv.setViewName("redirect:/login");
	    }
	    return mv;
	}
	
	
	@GetMapping("/homepage")
	public ModelAndView loginUser() {
		ModelAndView mv = new ModelAndView();
	    	 mv.addObject("listofStudents", webAppService.getAllStudents());
	         mv.setViewName("Home");
	         return mv;
	    }
	
    
	@GetMapping(value="/edituser/{id}")
	public ModelAndView getAlldetails(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();
		WebAppEntity user = repository.findById(id);
		mv.addObject("user",user);
		mv.setViewName("edituser");
		return mv;
		
	}
	@PostMapping("/updateuser")
	public ModelAndView updateUser(WebAppEntity webAppEntity) {
		ModelAndView mv = new ModelAndView();
		WebAppEntity user = webAppService.setisLoggedin(1);
		String isadmin = user.getIsAdmin();
		String email = user.getEmail();
		String password = user.getPassword();
		if(user.getId()==webAppEntity.getId()) {
			if(isadmin.equals("true")) {
				if((email.equals(webAppEntity.getEmail()))&&(password.equals(webAppEntity.getPassword()))) {
					webAppEntity.setIsLogged(1);
					webAppEntity.setIsAdmin(isadmin);
					webAppService.saveUserDetails(webAppEntity);
					mv.setViewName("redirect:/homepage");
				}
				else {
					webAppEntity.setIsLogged(1);
					webAppEntity.setIsAdmin(isadmin);
					webAppService.saveUserDetails(webAppEntity);
					mv.setViewName("redirect:/logout");
				}
			}
			else {
				if((email.equals(webAppEntity.getEmail()))&&(password.equals(webAppEntity.getPassword()))) {
					webAppEntity.setIsLogged(1);
					webAppEntity.setIsAdmin("false");
					webAppService.saveUserDetails(webAppEntity);
					mv.setViewName("redirect:/homepage");
				}
				else {
					webAppEntity.setIsLogged(1);
					webAppEntity.setIsAdmin("false");
					webAppService.saveUserDetails(webAppEntity);
					mv.setViewName("redirect:/logout");
				}
				
			}
			
		}
		else {
			webAppEntity.setIsAdmin("false");
			webAppService.saveUserDetails(webAppEntity);
			mv.setViewName("redirect:/homepage");
		}
		return mv;
		
	}
	
	@GetMapping("/deleteuser/{id}")
	public ModelAndView deleteUser(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();
		WebAppEntity user = webAppService.setisLoggedin(1);
		if(user.getId()==id) {
		    webAppService.deleteUserDetails(id);
		    mv.setViewName("Login");
		}
		else {
			webAppService.deleteUserDetails(id);
		    mv.setViewName("redirect:/homepage");
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView userLogout() {
	    ModelAndView mv = new ModelAndView();
	    WebAppEntity user = webAppService.setisLoggedin(1);
   	    user.setIsLogged(0);
   	    webAppService.saveUserDetails(user);
	    mv.setViewName("Login");
	    return mv;
	}
	
	@GetMapping("/profile")
	public ModelAndView userprofile() {
		 ModelAndView mv = new ModelAndView();
		 WebAppEntity logged =  webAppService.setisLoggedin(1);
		 String isadmin = logged.getIsAdmin();
		 int islogged = logged.getIsLogged();
		 if((isadmin.equals("true"))&&(islogged==1)) {
			 mv.addObject("listofDetails", webAppService.getAllUser());
			 mv.setViewName("userProfile");
		 }
		 else {
			 mv.addObject("listofDetails",  webAppService.setisLoggedin(1));
			 mv.setViewName("userProfile");
		 }
		 return mv;
	}
	
	@GetMapping("/AddStudent")
	public ModelAndView addStudent() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addStudent");
		return mv;
	}
	
	@PostMapping("/saveStudentDetails")
	public ModelAndView saveStudent(StudentEntity studentEntity) {
		ModelAndView mv = new ModelAndView();
		webAppService.saveStudentDetails(studentEntity);
		mv.setViewName("redirect:/homepage");
		return mv;
	}
	
	@GetMapping("/deleteStudent/{id}")
	public ModelAndView deleteStudent(@PathVariable long id) {
		ModelAndView mv =new ModelAndView();
		webAppService.deleteStudentDetails(id);
		mv.setViewName("redirect:/homepage");
		return mv;
		
	}
	
	@GetMapping(value="/editstudent/{id}")
	public ModelAndView getStudentdetails(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();
		StudentEntity user = webAppService.getStudentDetails(id);
		mv.addObject("user",user);
		mv.setViewName("studentedit");
		return mv;
		
	}
	
	@PostMapping("/updatestudent/{id}")
	public ModelAndView updateStudentdetails(@PathVariable long id,StudentEntity studentEntity) {
		ModelAndView mv = new ModelAndView();
		studentEntity.setId(id);
		webAppService.updateStudentDetails(studentEntity);
		mv.setViewName("redirect:/homepage");
		return mv;
		
	}
    
	
	
}
