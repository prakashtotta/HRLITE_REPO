
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
  
 
 
  //response.setHeader("Expires", "Thursday, February 15 2009 12:00:00 GMT");


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

String fullname = (String)request.getParameter("fullname");
String reqid = (String)request.getParameter("reqid");
String prevorg = (String)request.getParameter("prevorg");
String email = (String)request.getParameter("email");

String appdate = (String)request.getParameter("appdate");
String cri = (String)request.getParameter("cri");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String applicantNo = (String)request.getParameter("applicantNo");
String interviewstate = (String)request.getParameter("interviewstate");




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



	dataList = BOFactory.getApplicantBO().searchOwnApplicantsForPagination(user1,fullname,reqid,prevorg,email,appdate,cri,applicantNo,interviewstate,results,startIndex,dir_str,sort_str);
	

if(request.getSession().getAttribute(Common.APPLICANT_COUNT) == null){
 totalSize = BOFactory.getApplicantBO().getCountOfsearchOwnApplicant(user1,fullname,reqid,prevorg,email,appdate,cri,applicantNo,interviewstate);
request.getSession().setAttribute(Common.APPLICANT_COUNT,new Integer(totalSize));
 }else{
totalSize = ((Integer)request.getSession().getAttribute(Common.APPLICANT_COUNT)).intValue();
 }

System.out.println(totalSize);
String[] fields = {"applicantId","fullName","email","phone","city","heighestQualification","interviewState","appliedDate","jobTitle","referrerName","ownername","uuid","createdBy","ownerId","ownergroupId","ownernamegroup","isGroup"};

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
