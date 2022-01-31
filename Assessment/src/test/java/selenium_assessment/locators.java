package selenium_assessment;

import org.openqa.selenium.By;

public class locators {
	
	private String username ="txtUsername";
	private String password= "txtPassword";
    private String login ="Submit";
    private String empusername ="systemUser[userName]";
    private String emppass ="systemUser[password]";
    private String conpass = "systemUser[confirmPassword]";
    private String empname="systemUser_employeeName_empName";
    private String admin = "menu_admin_viewAdminModule";
    private String addbtn = "//*[@id=\"btnAdd\"]";
    private String heading = "//*[@id=\"UserHeading\"]";
    private String status = "systemUser_userType";
    private String save = "//*[@id=\"btnSave\"]";
    
	

    public String getUsername() {
	return username;
	}
	public void setUsername(String username) {
	this.username = username;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	this.password = password;
	
	}
	
	public String getLogin() {
		return login;
		}
		public void setLogin(String login) {
		this.login = login;
		}
		
		
	public String getEmpusername() {
		return empusername;
			}
	public void setEmpusername(String empusername) {
		this.empusername = empusername;
			}
			
	public String getEmppass() {
		return emppass;
				}
	public void setEmppass(String emppass) {
		this.emppass = emppass;
				}
				
	public String getConpass() {
		return conpass;
					}
	public void setConpass(String conpass) {
		this.conpass = conpass;
					}	
	
	public String getEmpname() {
		return empname;
				}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	
    public String getAdmin() {
		return admin;
		}
    public void setAdmin(String admin) {
		this.username = admin;
		}
		
    public String getAddbtn() {
			return addbtn;
			}
    public void setAddbtn(String addbtn) {
			this.addbtn = addbtn;
}
    public String getheading() {
		return heading;
		}
    public void setheading(String heading) {
		this.heading = heading;
}

    public String getStatus() {
	return status;
	}
	public void setStatus(String status) {
	this.status = status;
	}
	
    public String getSave() {
		return save;
		}
   public void setSave(String save) {
		this.save = save;
		}	
}
