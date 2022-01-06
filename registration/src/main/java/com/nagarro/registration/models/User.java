package com.nagarro.registration.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long usrId;
	
	String usrname;
	String usrmail;
	String usrpassword;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Review> reviews;
	
	public User() {}
	
	public User(long usrId, String usrname, String usrmail, String usrpassword) {
		super();
		this.usrId = usrId;
		this.usrname = usrname;
		this.usrmail = usrmail;
		this.usrpassword = usrpassword;
	}

	public String getUsrmail() {
		return usrmail;
	}

	public void setUsrmail(String usrmail) {
		this.usrmail = usrmail;
	}

	public long getUsrId() {
		return usrId;
	}

	public void setUsrId(long usrId) {
		this.usrId = usrId;
	}

	public String getUsrname() {
		return usrname;
	}

	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}

	public String getUsrpassword() {
		return usrpassword;
	}

	public void setUsrpassword(String usrpassword) {
		this.usrpassword = usrpassword;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return usrId + " " + usrname + " " + usrpassword;
	}
	
	
}
