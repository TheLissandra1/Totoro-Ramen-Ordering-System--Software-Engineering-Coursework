
public class HelperControl {

	public void skipConfirm() {
		ConfirmPage c = new ConfirmPage();
		c.createOrder(); 
		c.createButton();
	}
	
	public void skipPay(int choose,int need) {
		PayPage pay =  new PayPage();
		if(choose==0) 
			pay.setNeed(need);
		else 
			pay.noStamp();
	}
	public void skiploginSuccessfully() {
		LoginSuccessfully logsuccess = new LoginSuccessfully();
	}
	public void skipPayment() {
		Payment pay = new Payment();
	}
	public void skipMembership() {
		Membership membership = new Membership();
	}
	public void skipIdentityChoose() {
		IdentityChoose IC = new IdentityChoose();
	}
	public void skipManagerOptions(){
		ManagerOptions  mo = new ManagerOptions();
	}
	public void skipManager(){
		Manager newMa = new Manager();
		newMa.setVisible(true);
	}

	public void skipOrderMenu(){
		OrderMenu newOrder = new OrderMenu();
	}

	public void skipModifyMenu(){
		ModifyMenu newModify = new ModifyMenu();
	}
	
	public void skipRegisterPage(){
		RegisterPage register = new RegisterPage();
		register.setColor();
		register.showQuesqions();
		register.showTextBox();
		register.showButton();
	}

	public void skipStaticsPage(){
		new ViewStatistics();
	}

}

