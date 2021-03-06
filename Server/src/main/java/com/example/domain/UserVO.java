package com.example.domain;

import java.sql.Date;
import java.util.Arrays;

public class UserVO {
	private String[] imgpath;
	private int[] typeid;
	private String[] content;
	private int[] hobbyid;
	private String voicepath;
	private int imgshow;
	private String userid;
	private String userheight;
	private String userpassword;
	private String userpass;
	private String username;
	private String userjob;
	private String usercomment;
	private int userage;
	private String usernickname;
	private String localcode;
	private String localname;
	private Date regdate;
	private String gendername;
	private boolean loginstatus;
	private int userpoint;
	private int gendercode;
	private int reasonid;
	private String soloimg;
	private String latitude;
	private String longitude;
	private String apaddress;
	private String apdate;
	private String aptime;
	private int pfno;
	private int profit;
	private String token;

	
	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPfno() {
		return pfno;
	}

	public void setPfno(int pfno) {
		this.pfno = pfno;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public String getApaddress() {
		return apaddress;
	}

	public void setApaddress(String apaddress) {
		this.apaddress = apaddress;
	}

	public String getApdate() {
		return apdate;
	}

	public void setApdate(String apdate) {
		this.apdate = apdate;
	}

	public String getAptime() {
		return aptime;
	}

	public void setAptime(String aptime) {
		this.aptime = aptime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String[] getImgpath() {
		return imgpath;
	}

	public void setImgpath(String[] imgpath) {
		this.imgpath = imgpath;
	}

	public String getVoicepath() {
		return voicepath;
	}

	public void setVoicepath(String voicepath) {
		this.voicepath = voicepath;
	}

	public int[] getTypeid() {
		return typeid;
	}

	public void setTypeid(int[] typeid) {
		this.typeid = typeid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserheight() {
		return userheight;
	}

	public void setUserheight(String userheight) {
		this.userheight = userheight;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserjob() {
		return userjob;
	}

	public void setUserjob(String userjob) {
		this.userjob = userjob;
	}

	public String getUsercomment() {
		return usercomment;
	}

	public void setUsercomment(String usercomment) {
		this.usercomment = usercomment;
	}

	public int getUserage() {
		return userage;
	}

	public void setUserage(int userage) {
		this.userage = userage;
	}

	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public String getLocalcode() {
		return localcode;
	}

	public void setLocalcode(String localcode) {
		this.localcode = localcode;
	}

	public String getLocalname() {
		return localname;
	}

	public void setLocalname(String localname) {
		this.localname = localname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public String getGendername() {
		return gendername;
	}

	public void setGendername(String gendername) {
		this.gendername = gendername;
	}

	public boolean isLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(boolean loginstatus) {
		this.loginstatus = loginstatus;
	}

	public int getUserpoint() {
		return userpoint;
	}

	public void setUserpoint(int userpoint) {
		this.userpoint = userpoint;
	}

	public int getGendercode() {
		return gendercode;
	}

	public void setGendercode(int gendercode) {
		this.gendercode = gendercode;
	}

	public int[] getHobbyid() {
		return hobbyid;
	}

	public void setHobbyid(int[] hobbyid) {
		this.hobbyid = hobbyid;
	}

	public int getReasonid() {
		return reasonid;
	}

	public void setReasonid(int reasonid) {
		this.reasonid = reasonid;
	}

	public int getImgshow() {
		return imgshow;
	}

	public void setImgshow(int imgshow) {
		this.imgshow = imgshow;
	}

	public String getSoloimg() {
		return soloimg;
	}

	public void setSoloimg(String soloimg) {
		this.soloimg = soloimg;
	}

	@Override
	public String toString() {
		return "UserVO [imgpath=" + Arrays.toString(imgpath) + ", typeid=" + Arrays.toString(typeid) + ", content="
				+ Arrays.toString(content) + ", hobbyid=" + Arrays.toString(hobbyid) + ", voicepath=" + voicepath
				+ ", imgshow=" + imgshow + ", userid=" + userid + ", userheight=" + userheight + ", userpassword="
				+ userpassword + ", username=" + username + ", userjob=" + userjob + ", usercomment=" + usercomment
				+ ", userage=" + userage + ", usernickname=" + usernickname + ", localcode=" + localcode
				+ ", localname=" + localname + ", regdate=" + regdate + ", gendername=" + gendername + ", loginstatus="
				+ loginstatus + ", userpoint=" + userpoint + ", gendercode=" + gendercode + ", reasonid=" + reasonid
				+ ", soloimg=" + soloimg + ", latitude=" + latitude + ", longitude=" + longitude + ", apaddress="
				+ apaddress + ", apdate=" + apdate + ", aptime=" + aptime + ", pfno=" + pfno + ", profit=" + profit
				+ ", token=" + token + "]";
	}

}