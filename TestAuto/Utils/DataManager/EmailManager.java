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
			System.out.println("Can't connect to " + email + " via IMAP" );
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
//		props.setProperty("mail.imap.port", "993");
		props.setProperty("mail.imap.ssl.enable", "true"); // required for Gmail
//		props.setProperty("mail.imap.auth.mechanisms", "XOAUTH2");

		//set up the session
//		Session session = Session.getDefaultInstance(props,null);
		Session session = Session.getInstance(props);
		store = session.getStore("imaps");
		
		//Connect to your email account
		store.connect(emailHost, email, password);
		System.out.println("Connected to Email server successfully with email : " + email);
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
		inboxFolder.open(Folder.READ_ONLY);
		
		//Check unread mail
	    Flags seen = new Flags(Flags.Flag.RECENT);
//		Flags recent = new Flags(Flags.Flag.RECENT);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, true);		
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
	 * @param emailSubject_Keyword	Subject of email to search in Inbox
	 * @param linkInEmail_Keyword	Apart of link. it's used to locate the exact link in email content
	 * @author LamNguyen
	 */
	public String getLink_FromAnEmail(String emailSubject_Keyword, String linkInEmail_Keyword) throws Exception {
		Folder inboxFolder = store.getFolder("Inbox");
		inboxFolder.open(Folder.READ_ONLY);
		
		//Check unread mail
	    Flags seen = new Flags(Flags.Flag.SEEN);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);		
		Message[] messages = inboxFolder.search(unseenFlagTerm);
		System.out.println("The total of unread mail is : " + messages.length);
		for (int i = 0; i < messages.length; i++) {			
			String emailSubject = messages[i].getSubject();
			
			String emailSender; 
			Address[] add = messages[i].getFrom();
			for (Address address : add) {
				System.out.println(address.toString());
				emailSender = address.toString();				
			}
			
			
			System.out.println("Subject of the email " + (i+1) + " is : " + emailSubject );
			
			String Subject_term = emailSubject_Keyword;
//			String Sender_term = emailSender_Keyword;
			
			//Search for Subject contains a Subject_term. Ex: 'Password Reset'
			if (emailSubject.contains(Subject_term)) {
				System.out.println("There's a '" + Subject_term + "' email");			
				Message message = messages[i];
				String mailContent = (String) message.getDataHandler().getContent();
				
				//Get all links in email content
				Pattern linkPattern = Pattern.compile("href=\"([^\"]*)",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
				Matcher linkMatcher = linkPattern.matcher(mailContent);
				ArrayList<String> links = new ArrayList<String>();
				while(linkMatcher.find()){
				    links.add(linkMatcher.group(1));
				}
				
				//Get only link contains linkKeyword. Ex: 'Reset password'
				for(String temp : links) {
					if (temp.contains(linkInEmail_Keyword)) {
						System.out.println("Link of '" + Subject_term + "' is : " + temp);
						link_FromEmail = temp;
						break;
					}									
				}				
			}else {
				System.out.println("There's no '" + Subject_term + "' email ");
			}
		}
		//close the store and folder objects
		inboxFolder.close(false);
	    store.close();
		return link_FromEmail;
	}
	
	/**
	 * Get any Link URL from Email
	 * @param emailSubject_Keyword	Subject of email to search in Inbox
	 * @param linkInEmail_Keyword	Apart of link. it's used to locate the exact link in email content
	 * @author LamNguyen
	 */
	public String getLink_FromAnEmail(String folder, String emailSubject_Keyword, String linkInEmail_Keyword) throws Exception {
		Folder inboxFolder = store.getFolder(folder);
		inboxFolder.open(Folder.READ_ONLY);
		
		//Check unread mail
	    Flags seen = new Flags(Flags.Flag.SEEN);
	    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);		
		Message[] messages = inboxFolder.search(unseenFlagTerm);
		System.out.println("The total of unread mail is : " + messages.length);
		for (int i = 0; i < messages.length; i++) {			
			String emailSubject = messages[i].getSubject();
			
			String emailSender; 
			Address[] add = messages[i].getFrom();
			for (Address address : add) {
				System.out.println(address.toString());
				emailSender = address.toString();				
			}
			
			
			System.out.println("Subject of the email " + (i+1) + " is : " + emailSubject );
			
			String Subject_term = emailSubject_Keyword;
//			String Sender_term = emailSender_Keyword;
			
			//Search for Subject contains a Subject_term. Ex: 'Password Reset'
			if (emailSubject.contains(Subject_term)) {
				System.out.println("There's a '" + Subject_term + "' email");			
				Message message = messages[i];
				String mailContent = (String) message.getDataHandler().getContent();
				
				//Get all links in email content
				Pattern linkPattern = Pattern.compile("href=\"([^\"]*)",  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
				Matcher linkMatcher = linkPattern.matcher(mailContent);
				ArrayList<String> links = new ArrayList<String>();
				while(linkMatcher.find()){
				    links.add(linkMatcher.group(1));
				}
				
				//Get only link contains linkKeyword. Ex: 'Reset password'
				for(String temp : links) {
					if (temp.contains(linkInEmail_Keyword)) {
						System.out.println("Link of '" + Subject_term + "' is : " + temp);
						link_FromEmail = temp;
						break;
					}									
				}				
			}else {
				System.out.println("There's no '" + Subject_term + "' email ");
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
