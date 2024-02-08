package com.inzent.commonAPI.vo;

public class MailInfoVO {
	
	private String mail_info_id;
	private String mail_title;
    private String mail_template;
    private String sub_system;
    
    public MailInfoVO(String mail_info_id, String mail_title, String mail_template, String sub_system) {
        this.mail_info_id = mail_info_id;
        this.mail_title = mail_title;
        this.mail_template = mail_template;
        this.sub_system = sub_system;
    }

	public String getMail_info_id() {
		return mail_info_id;
	}

	public void setMail_info_id(String mail_info_id) {
		this.mail_info_id = mail_info_id;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getMail_template() {
		return mail_template;
	}

	public void setMail_template(String mail_template) {
		this.mail_template = mail_template;
	}

	public String getSub_system() {
		return sub_system;
	}

	public void setSub_system(String sub_system) {
		this.sub_system = sub_system;
	}
}
