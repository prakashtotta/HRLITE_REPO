<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>



<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "questionId"; // default don't sort
String dir = "asc"; // default sort dir is asc




String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

String orgId = (String)request.getParameter("orgId");
String departmentId = (String)request.getParameter("departmentId");
System.out.println("orgId >> "+orgId);
System.out.println("departmentId >> "+departmentId);
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

List dataList = BOFactory.getProjectCodeBO().getAllProjectCodeDetailsForPagination(results,startIndex,dir_str,sort_str,new Long(orgId).longValue(),new Long(departmentId).longValue());

System.out.println("size"+dataList.size());
int totalSize = 0;

List allDataList = BOFactory.getProjectCodeBO().getAllProjectCodeDetailsforpagination(new Long(orgId).longValue(),new Long(departmentId).longValue());
totalSize = allDataList.size();
	

System.out.println(totalSize);
String[] fields = {"projectId","projCode","projName","effectiveStartDate","effectiveEndDate","orgName","departmentName"};


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
