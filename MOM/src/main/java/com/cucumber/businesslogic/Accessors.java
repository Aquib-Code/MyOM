package com.cucumber.businesslogic;

public class Accessors extends ProjectAccessors{
 private static String Req_Id="";
 private static String TestCaseName="";
 private static String StepId="";
 private static String FunctionalPointId="";
 private static String strStatus="PASSED";
 private static int itearationId=1;
 private static int ParentStepNumber=1;
 private static int ChildStepNumber=1;
 
 public void setReqirementId(String req_id) {
	 Req_Id = req_id;
 }
 public void setTestCaseName(String testcasename) {
	 TestCaseName = testcasename;	 
 }
 public void setStepId(String strpId) {
	 StepId = strpId;
 }
 public void setFPID(String fpId) {
	 FunctionalPointId = fpId;	 
 }
 public void setStatus(String status) {
	 strStatus = status;	 
 }
 public void setIterationId(int iteration) {
	 itearationId = iteration;
 }
 public void setParentStepNo(int parentstepno) {
	 ParentStepNumber = parentstepno;
 }
 public void setChildStepno(int childStepNo) {
	 ChildStepNumber = childStepNo;
 }
 public String getReqirementId() {
	 return Req_Id;
 }
 public String getTestCaseName() {
	 return TestCaseName;
 }
 public String getStepId() {
	 return StepId;
 }
 public String getFunctionalPointId() {
	 return FunctionalPointId;
 }
 public String getStatus() {
	 return strStatus;
 }
 public int getIteration() {
	 return itearationId;
 }
 public int getParentStepNumber() {
	 return ParentStepNumber;
 }
 public int getChildStepNumber() {
	 return ChildStepNumber;
 }
}
