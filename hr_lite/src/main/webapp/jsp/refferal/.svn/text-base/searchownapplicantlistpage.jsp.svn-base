
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
 
 
 
  


  %>

  <%
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
%>

<%


int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

String fullname = (String)request.getParameter("fullname");
String reqid = (String)request.getParameter("reqid");
String prevorg = (String)request.getParameter("prevorg");
String email = (String)request.getParameter("email");

String appdate = (String)request.getParameter("appdate");
String cri = (String)request.getParameter("cri");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String applicantNo = (String)request.getParameter("applicantNo");



if(startIndex_str != null && startIndex_str.length() > 0){
  startIndex = Integer.parseInt(startIndex_str);
}
if(sort_str != null && sort_str.length() > 0){
  sort = sort_str;
}
if(dir_str != null && dir_str.length() > 0){
  dir = dir_str;
}
if(results_str != null && results_str.length() > 0){
  results = Integer.parseInt(results_str);
}


int totalSize = 0;
List dataList = new ArrayList();



	dataList = BOFactory.getApplicantBO().searchOwnRefferalApplicantsForPagination(user1,fullname,reqid,prevorg,email,appdate,cri,applicantNo,results,startIndex,dir_str,sort_str);
	totalSize = BOFactory.getApplicantBO().getCountOfsearchOwnRefferalApplicant(user1,fullname,reqid,prevorg,email,appdate,cri,applicantNo);
	

System.out.println(totalSize);
String[] fields = {"applicantId","fullName","email","phone","city","heighestQualification","interviewState","appliedDate","jobTitle","referrerName","ownername","uuid","createdBy","reqId"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTableWithDefaultDateFormat(dataList,fields)+
				 "}";
                
System.out.println(data);
%>

<%=data%>
