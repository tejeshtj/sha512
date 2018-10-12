package serure;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Scanner;
public class Algorithm {
	

	public String sha512(String passwordToHash, String   salt){
	String generatedPassword = null;
	    try {
	         MessageDigest md = MessageDigest.getInstance("SHA-512");
	         md.update(salt.getBytes(StandardCharsets.UTF_8));
	         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
	         StringBuilder sb = new StringBuilder();
	         for(int i=0; i< bytes.length ;i++){
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	         }
	         generatedPassword = sb.toString();
	        } 
	       catch (NoSuchAlgorithmException e){
	        e.printStackTrace();
	       }
	    return generatedPassword;
	}
	public static void main(String[] args) throws Exception{
		Scanner in=new Scanner(System.in);
		
		while(true) {
			System.out.println("1-to generate secured password");
			System.out.println("0-to exit");
			int option=Integer.parseInt(in.nextLine());
			switch (option) {
			case 1:
				Algorithm alg=new Algorithm();
				System.out.println("enter tht passcode to generate hashcode");
				String password=in.nextLine();
				Properties prop=new Properties();
				FileReader fr=new FileReader("E:\\saltstring.properties");//change according to the property file n give ur salt string
				prop.load(fr);
				final String salt=prop.getProperty("salt");
				String EncryptedPassword=alg.sha512(password, salt);
				System.out.println("your encrypted passcode is "+EncryptedPassword);
				
				break;
			case 0:
				break;
			default:
				break;
			}
			
		}
		
	}
}
