public class UserBean {
	private String familyName;
	private String givenName;
	private String contactWay;
	private String password;
	
	public UserBean() {
		super();
	}
	
	public UserBean(String familyName,String givenName,String contactWay,String password){
		super();
		this.familyName = familyName;
		this.givenName = givenName;
		this.contactWay = contactWay;
		this.password = password;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getContactWay() {
		return contactWay;
	}
	
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "UserImformation: \n" + "Family name: " + familyName+ "\n" 
				 + "Given name: " + givenName + "\n" 
				 + "Contact way: " + contactWay +  "\n" 
				 + "Password: " + password +  "\n" 
				 + "Stamps: "+"0";
	}
	
	
	
}