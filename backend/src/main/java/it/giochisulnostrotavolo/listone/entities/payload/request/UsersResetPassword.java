// package it.giochisulnostrotavolo.listone.entities.payload.request;

// import java.io.Serializable;
// import java.sql.Timestamp;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

// @Entity
// @Table(name = "users_reset_password")
// public class UsersResetPassword implements Serializable{

// 	private static final long serialVersionUID = 5895619742777466267L;

// 	@Id
// 	@Column
// 	private String username;
	
// 	@Column
// 	private Timestamp date;

// 	@Column
// 	private String md5;

// 	public UsersResetPassword() {
// 	}

// 	public UsersResetPassword(String username, Timestamp date, String md5) {
// 		super();
// 		this.username = username;
// 		this.date = date;
// 		this.md5 = md5;
// 	}

// 	public String getUsername() {
// 		return username;
// 	}

// 	public void setUsername(String username) {
// 		this.username = username;
// 	}

// 	public Timestamp getDate() {
// 		return date;
// 	}

// 	public void setDate(Timestamp date) {
// 		this.date = date;
// 	}

// 	public String getMd5() {
// 		return md5;
// 	}

// 	public void setMd5(String md5) {
// 		this.md5 = md5;
// 	}
	
// }
