package DataManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class JsonManager {
	
//	String acc1_TxtFile = "WalletAccountFile/lamwallet1-account.txt";
	JsonObject jsonObject = null;
//	String name_TxtFile = "name";
//	String privateKey_TxtFile = "private_key";
	

	
	public JsonManager(String jsonFile) {		
		Gson gs = new Gson();		
		try {
			jsonObject = gs.fromJson(new FileReader(jsonFile), JsonObject.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("NAME: " + jsonObject.get(name_TxtFile) + "\nPRIVATE KEY: " + jsonObject.get(privateKey_TxtFile));
	}



	public String getValueFromKey(String keyName) {
		return jsonObject.get(keyName).getAsString();				
	}
}
