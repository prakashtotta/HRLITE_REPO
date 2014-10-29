<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>


<%


int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc

User user1 = (User)request.getSession().getAttribute(Common.AGENCY_DATA);

String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");
String requistionId = (String)request.getParameter("requistionId");
String state = (String)request.getParameter("state");

System.out.println("results_str satya"+results_str);
System.out.println("startIndex_str"+startIndex_str);
System.out.println("sort_str"+sort_str);
System.out.println("dir_str"+dir_str);
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

Map m = BOFactory.getApplicantBO().searchApplicantPaginationByReqIdAndVendorId(user1,requistionId,state,results,startIndex,dir_str,sort_str);
dataList = (List)m.get(Common.JOBS_LIST);
 
 totalSize = ((Integer)m.get(Common.JOBS_COUNT)).intValue();


	


String[] fields = {"applicantId","fullName","email","phone","countryName","city","heighestQualification","qualifications","interviewState","appliedDate","resumeHeader","jobTitle","referrerName","ownername","uuid"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dataList,fields,user1)+
				 "}";
                

%>

<%=data%>
<% System.out.println(totalSize);%>
