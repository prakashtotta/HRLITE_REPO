
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

String friendsname = (String)request.getParameter("friendsname");
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

	dataList = RefBO.searchfriendsForPagination(user1,friendsname,results,startIndex,dir_str,sort_str);
	totalSize = RefBO.getCountOfsearchfriends(user1,friendsname);
}else{
	dataList = RefBO.searchfriendsForPagination(user1,results,startIndex,dir_str,sort_str);
	totalSize = RefBO.getCountOfsearchfriends(user1);

	
}
	

System.out.println(totalSize);
String[] fields = {"referafriendId","name","emailId","note","status","jobRequisitionId","jobTitle"};


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