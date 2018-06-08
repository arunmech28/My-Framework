package Framework.Utilities;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import sun.net.smtp.SmtpClient;

public class SendMail {
	 public static void main(String[] args) throws Exception
	 {
	 /*Properties maildata = ReadPropertiesFileData.read("MailData.properties"); 
	 String[] to= maildata.getProperty("to").split(",");
	 String[] cc = {};
	 String[] bcc = {};
	 if(!maildata.getProperty("cc").equals("") && maildata.getProperty("cc") != null ){
	 cc= maildata.getProperty("cc").split(",");
	 }
	 if(!maildata.getProperty("bcc").equals("") && maildata.getProperty("bcc") != null){
	 bcc = maildata.getProperty("bcc").split(",");
	 }*/
		 String[] to= {"vishnuprasad.mohan@cognizant.com"};
		 String[] cc = {};
		 String[] bcc = {};
	 SendMail.sendMail("arunpandian.sekar@cognizant.com",
	 "Rani1993^^",
	 "smtp-mail.outlook.com",
"587",
	 "true",
	 "true",
	 true,
	 "javax.net.ssl.SSLSocketFactory",
	 "false",
	 to,
	 cc,
	 bcc,
	 "Test",
	 "Test Message",
	 "nothing",
	 "nothing");
	 }
	 public static boolean sendMail(String userName,
	 String passWord,
	 String host,
	 String port,
	 String starttls,
	 String auth,
	 boolean debug,
	 String socketFactoryClass,
	 String fallback,
	 String[] to,
	 String[] cc,
	 String[] bcc,
	 String subject,
	 String text,
	 String err,
	 String name){
	 //Object Instantiation of a properties file.
	 Properties props = new Properties();
	 props.put("mail.smtp.user", userName);
	 props.put("mail.smtp.host", host);
	 if(!"".equals(port)){
	 props.put("mail.smtp.port", port);
	 }
	 if(!"".equals(starttls)){
	 props.put("mail.smtp.starttls.enable",starttls);
	 props.put("mail.smtp.auth", auth);
	 }
	 if(debug){
	 props.put("mail.smtp.debug", "true");
	 }else{
	 props.put("mail.smtp.debug", "false");
	 }
	 if(!"".equals(port)){
	 props.put("mail.smtp.socketFactory.port", port);
	 }
	 if(!"".equals(socketFactoryClass)){
	 props.put("mail.smtp.socketFactory.class",socketFactoryClass);
	 }
	 if(!"".equals(fallback)){
	 props.put("mail.smtp.socketFactory.fallback", fallback);
	 }
	 try{
	 Session session = Session.getDefaultInstance(props, null);
	 session.setDebug(debug);
	 MimeMessage msg = new MimeMessage(session);
	 msg.setText(text);
	 msg.setSubject(subject);
	 Multipart multipart = new MimeMultipart();
	 /*MimeBodyPart messageBodyPart = new MimeBodyPart();
	 FileDataSource source = new FileDataSource(attachmentPath);
	 messageBodyPart.setDataHandler(new DataHandler(source));
	 messageBodyPart.setFileName(attachmentName);*/
	// 
	 //newly added code
	 BodyPart messageBodyPart1 = new MimeBodyPart(); 
	 String body_text = "Please find the below message \n\n\nMethod Name : "+name+"\n\nReason for failing : "+err;
	 messageBodyPart1.setText(body_text); 
	 multipart.addBodyPart(messageBodyPart1);
	 msg.setContent(multipart);
	 msg.setFrom(new InternetAddress(userName));
	 for(int i=0;i<to.length;i++){
	 msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
	 }
	 for(int i=0;i<cc.length;i++){
	 msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
	 }
	 for(int i=0;i<bcc.length;i++){
	 msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
	 }
	 msg.saveChanges();
	 Transport transport = session.getTransport("smtp");
	 transport.connect(host, userName, passWord);
	 transport.sendMessage(msg, msg.getAllRecipients());
	 transport.close();
	 return true;
	 } catch (Exception mex){
	 mex.printStackTrace();
	 return false;
	 }
	 }
	}
