package com.cucumber.businesslogic;

public class ProjectAccessors {
	private static String ApplicationName="";
	private static String ReleaseNumber="";
	public void setApplicationName(String AppName){
		ApplicationName = AppName;
	}
	public void setRelease(String releaseNo) {
		ReleaseNumber= releaseNo;
	}
	public String getApplicationName() {
		return ApplicationName;
		
	}
	public String getReleaseNumber() {
		return ReleaseNumber;
		
	}
}
