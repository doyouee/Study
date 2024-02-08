package com.inzent.commonAPI.vo;

public class MailHistoryVO {
	
	private String mail_history_id;
	private String mail_title;
	private String mail_reciever;
	private String mail_content;
    private String mail_status;
    
    public MailHistoryVO(String mail_history_id, String mail_title, String mail_reciever, String mail_content, String mail_status) {
    	this.mail_history_id = mail_history_id;
    	this.mail_title = mail_title;
        this.mail_reciever = mail_reciever;
        this.mail_content = mail_content;
        this.mail_status = mail_status;
    }
    
	public String getMail_history_id() {
		return mail_history_id;
	}

	public void setMail_history_id(String mail_history_id) {
		this.mail_history_id = mail_history_id;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getMail_reciever() {
		return mail_reciever;
	}

	public void setMail_reciever(String mail_reciever) {
		this.mail_reciever = mail_reciever;
	}

	public String getMail_content() {
		return mail_content;
	}

	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}

	public String getMail_status() {
		return mail_status;
	}

	public void setMail_status(String mail_status) {
		this.mail_status = mail_status;
	}
}
