package DataManager;

public class FaceBookAccountModel {

	String username = "";
	String password = "";
	String uuid = "";
	String two_fa = "";
	String backup = "";

	FaceBookAccountModel(String _username, String _password, String _uuid, String _two_fa, String _backup) {
		this.backup = _backup;
		this.username = _username;
		this.password = _password;
		this.uuid = _uuid;
		this.two_fa = _two_fa;
	}

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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTwo_fa() {
		return two_fa;
	}

	public void setTwo_fa(String two_fa) {
		this.two_fa = two_fa;
	}

	public String getBackup() {
		return backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}

}