
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
 
 
 
 


 %>

 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

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

String searchpagedisplay =(String)request.getParameter("searchpagedisplay");
String fullName =(String)request.getParameter("fullName");
String applicant_numberstr =(String)request.getParameter("applicant_number");
String emailId =(String)request.getParameter("emailId");
String createdBy =(String)request.getParameter("createdBy");

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

long applicant_number = 0;
if(!StringUtils.isNullOrEmpty(applicant_numberstr) && !applicant_numberstr.equals("null")){
	applicant_number = new Long(applicant_numberstr).longValue();
}


List dataList = new ArrayList();
int totalSize = 0;
	dataList = ApplicantUserBO.getApplicantUsersForPagination(fullName,applicant_number,emailId,createdBy,user1.getSuper_user_key(),results,startIndex,dir_str,sort_str);
	totalSize = ApplicantUserBO.getCountOfAllApplicantUsers(fullName,applicant_number,emailId,createdBy,user1.getSuper_user_key());

	



String[] fields = {"applicantUserId","fullName","applicantId","emailId","jobTitle","interviewState","createdBy","status","uuid","applicant_number"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dataList,fields,user1)+
				 "}";
                
System.out.println(data);
%>

<%=data%>
