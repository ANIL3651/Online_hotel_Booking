package corporateReG;

public class C_regLibs {
	// Default variables
	int postalCode;
	String fName, lName, address, gender, phoneNumber, creditCard, email, userName, password,
			confirmPassword, type;

	// Default Constructor
	public C_regLibs() {
		this.fName = "";
		this.lName = "";
		this.address = "";
		this.postalCode = 0;
		this.gender = "";
		this.phoneNumber = "";
		this.creditCard = "";
		this.email = "";
		this.userName = "";
		this.password = "";
		this.confirmPassword = "";
		this.type = "corpcustomer";
	}

// Parameterized Constructor
	public C_regLibs(String fName, String lName, String address, int postalCode, String gender, String phoneNumber,
			String creditCard, String email, String userName, String password, String confirmPassword,
			String type) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.postalCode = postalCode;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.creditCard = creditCard;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.type = type;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "registration [postalCode=" + postalCode + ", fName=" + fName + ", lName=" + lName + ", address="
				+ address + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", creditCard=" + creditCard
				+ ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", type=" + type + "]";
	}
	
	

}
