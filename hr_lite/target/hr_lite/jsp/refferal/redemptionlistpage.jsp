
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

String applicantname = (String)request.getParameter("applicantname");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");




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


if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	//dataList = ApplicantBO.searchOwnRefferalApplicantsForPagination(user1,fullname,reqid,prevorg,email,appdate,cri,applicantNo,results,startIndex,dir_str,sort_str);
//	totalSize = ApplicantBO.getCountOfsearchOwnRefferalApplicant(user1,fullname,reqid,prevorg,email,appdate,cri,applicantNo);
}else{
	dataList = RefBO.searchOwnRefferalRedemptionsForPagination(user1,applicantname,results,startIndex,dir_str,sort_str);
	totalSize = RefBO.getCountOfsearchOwnRefferalRedemptions(user1,applicantname);
}
	

System.out.println(totalSize);
String[] fields = {"refredid","applicantName","JobTitle","refferalSchemeName","refferalSchemeAmount","uom","refferalSchemeTypeName","ruleName","state","applicantstate","eventdate","releaseddate","isPaid","uuid"};


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
