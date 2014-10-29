<%@ page import="com.common.*"%>
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
 
 
 
 


  %>

<%
User agency = (User)session.getAttribute(Common.AGENCY_DATA);

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");
String currentuserid = (String)request.getParameter("currentuserid");
String currentusername = (String)request.getParameter("currentusername");



System.out.println("results_str"+results_str);
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

List dataList = new ArrayList();



int totalSize = 0;

Map m = BOFactory.getJobRequistionBO().myJobRequistionByVendor(agency,results,startIndex,dir_str,sort_str);

dataList = (List)m.get(Common.JOBS_LIST);
 
 totalSize = ((Integer)m.get(Common.JOBS_COUNT)).intValue();

	

System.out.println(totalSize);
String[] fields = {"jobreqId","jobreqName","jobTitle","organizationValue","departmentValue","locationValue","projectcodeValue","jobtypeValue","workshiftValue","hiringMgrValue","templateName","state","templateId","status","recruiterName","publishedDate","targetfinishdate","agencyRefferalSchemeName","uuid"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dataList,fields,agency)+
				 "}";
                

%>

<%=data%>
