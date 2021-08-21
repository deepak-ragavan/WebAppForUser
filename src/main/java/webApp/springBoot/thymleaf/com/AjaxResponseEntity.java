package webApp.springBoot.thymleaf.com;

import java.util.List;

public class AjaxResponseEntity {
	
	private String status;
	List<WebAppEntity> result;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<WebAppEntity> getResult() {
		return result;
	}
	public void setResult(List<WebAppEntity> result) {
		this.result = result;
	}
	

}
