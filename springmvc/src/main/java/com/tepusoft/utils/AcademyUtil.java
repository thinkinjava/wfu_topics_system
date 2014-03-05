package com.tepusoft.utils;

public class AcademyUtil {
	private String academyId;
	private String academyName;
	

	public AcademyUtil(String academyId, String academyName) {
		super();
		this.academyId = academyId;
		this.academyName = academyName;
	}
	public String getAcademyId() {
		return academyId;
	}
	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	
	
}
