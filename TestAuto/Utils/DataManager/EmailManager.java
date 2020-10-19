package DataManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.search.FlagTerm;



public class EmailManager {
	Store store;
	String text_FromEmail;
	String link_FromEmail;

	public EmailManager(String emailHost, String email, String password) {
		try {
			connectToEmail(emailHost, email, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Connect to Email
	 * @param emailHost	host of email provider. Read from properties file. Currently support IMAP method with Gmail (YahooMail will be supported later) 
	 * @param email		email to login Gmail. Read from properties file
	 * @param password	password to login Gmail. Read from properties file
	 * @author LamNguyen
	 */
	public void connectToEmail(String emailHost, String email, String password) throws MessagingException {
		//Properties props = System.getProperties();
		Properties props = new Properties();
		
		//set email protocol to IMAP
		props.setProperty("mail.store.protocol", "imaps");
		//props.setProperty("mail.imap.ssl.enable", "true");

		//set up the session
		Session session = Session.getDefaultInstance(props,null);
		store = session.getStore("imaps");
		
		//Connect to your email account
		store.connect(emailHost, email, password);
		System.out.println("Connected to Email server successfully");
	}	

	
	/**
	 * Get any text from Email
	 * @param emailSubject	Subject of to search in Inbox
	 * @param textStart		The beginning text to search in email content
	 * @param textEnd		The ending text to search in email content
	 * @author LamNguyen
	 */
	public String getText_FromAnEmail(String emailSubject, String textStart, String textEnd) throws Exception {
		Folder inboxFolder = store.getFolder("INBOX");
		inboxFolder.open(Folder.READ_WRITE);
		
		//Check unread mail
	    Flags seen = new Flags(Flags.Flag.SEEN);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);		
		Message[] messages = inboxFolder.search(unseenFlagTerm);
		System.out.println("The total of unread mail is : " + messages.length);
		for (int i = 0; i < messages.length; i++) {			
			String mailSubject = messages[i].getSubject();
			String Subject_term = emailSubject;
			
			//Search for Subject contains a Subject_term. Ex: 'Password Reset'
			if (mailSubject.contains(Subject_term)) {
				Message message = messages[i];
				String mailContent = (String) message.getContent();
				
				//Get text from 'textStart' to 'textEnd'. Ex: 'Click Here to Reset Your Password'
				int indexStart = mailContent.indexOf(textStart);
				int indexEnd = mailContent.indexOf(textEnd,indexStart);
				text_FromEmail = mailContent.substring(indexStart, indexEnd-1);
				System.out.println("There's a '" + Subject_term + "' email from Kryptono Exchange");				
			}else {
				System.out.println("There's no '" + Subject_term + "' email from Kryptono Exchange");
			}			
		}
		//close the store and folder objects
		inboxFolder.close(false);
	    store.close();
		return text_FromEmail;
	}
	
	/**
	 * Get any Link URL from Email
	 * @param emailSubject	Subject of email to search in Inbox
	 * @param linkKeyword	Apart of link. it's used to locate the exact link in email content
	 * @author LamNguyen
	 */
	public String getLink_FromAnEmail(String emailSubject, String linkKeyword) throws Exception {
		Folder inboxFolder = store.getFolder("INBOX");
		inboxFolder.open(Folder.READ_WRITE);
		
		//Check unread mail
	    Flags seen = new Flags(Flags.Flag.SEEN);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);		
		Message[] messages = inboxFolder.search(unseenFlagTerm);
		System.out.println("The total of unread mail is : " + messages.length);
		for (int i = 0; i < messages.length; i++) {			
			String mailSubject = messages[i].getSubject();
			String Subject_term = emailSubject;
			
			//Search for Subject contains a Subject_term. Ex: 'Password Reset'
			if (mailSubject.contains(Subject_term)) {
				System.out.println("There's a '" + Subject_term + "' email from Kryptono Exchange");			
				Message message = messages[i];
				String mailContent = (String) message.getContent();
				
				//Get all links in email content
				Pattern linkPattern = Pattern.compile("href=\"([^\"]*)",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
				Matcher linkMatcher = linkPattern.matcher(mailContent);
				ArrayList<String> links = new ArrayList<String>();
				while(linkMatcher.find()){
				    links.add(linkMatcher.group(1));
				}
				
				//Get only link contains linkKeyword. Ex: 'Reset password'
				for(String temp : links) {
					if (temp.contains(linkKeyword)) {
						System.out.println("Link of '" + Subject_term + "' is : " + temp);
						link_FromEmail = temp;
						break;
					}									
				}				
			}else {
				System.out.println("There's no '" + Subject_term + "' email from Kryptono Exchange");
			}
		}
		//close the store and folder objects
		inboxFolder.close(false);
	    store.close();
		return link_FromEmail;
	}
	
//	public String getResetLink_Txt() {
//		try {
//			getResetLinkText_AfterSearchInboxMail();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return text_FromEmail;
//	}
//	

	/**
	 * Get 'Reset Passsword' Link URL from Email
	 * @author LamNguyen
	 */
	public String getResetPasswordLink_FromEmail() {
    	String emailSubject = "Password Reset";
    	String linkKeyword = "reset_password";
		try {
			getLink_FromAnEmail(emailSubject, linkKeyword);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return link_FromEmail;		
	}
	
	/**
	 * Get 'Verify to complete Registration' Link URL from Email
	 * @author LamNguyen
	 */
	public String getVerifyAccountLink_FromEmail() {
    	String emailSubject = "Verification Email";
    	String linkKeyword = "verify_email";
		try {
			getLink_FromAnEmail(emailSubject, linkKeyword);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return link_FromEmail;		
	}
}
