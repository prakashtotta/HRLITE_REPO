
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
  
 
 
  //response.setHeader("Expires", "Thursday, February 15 2009 12:00:00 GMT");


  %>

<%

String data = (String)request.getAttribute("data");

System.out.println("jobtemplate data : >>>>>>> "+data);
/*
int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

String templatename = (String)request.getParameter("templatename");
String orgId = (String)request.getParameter("orgId");
String departmentId = (String)request.getParameter("departmentId");
String jobgradeId = (String)request.getParameter("jobgradeId");
String jobtypeId = (String)request.getParameter("jobtypeId");
String statuscri = (String)request.getParameter("statuscri");
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
dataList = BOFactory.getJobRequistionBO().getJobRequistionTemplatesForPagination(templatename,orgId,departmentId,jobgradeId,jobtypeId,statuscri,results,startIndex,dir_str,sort_str);
totalSize = BOFactory.getJobRequistionBO().getCountOfAllJobRequistionTemplates(templatename,orgId,departmentId,jobgradeId,jobtypeId,statuscri);
}else {
	dataList = BOFactory.getJobRequistionBO().getJobRequistionTemplatesForPagination(results,startIndex,dir_str,sort_str);
totalSize = BOFactory.getJobRequistionBO().getCountOfAllJobRequistionTemplates();
}




	

System.out.println(totalSize);
String[] fields = {"templateId","templateName","organizationValue","departmentValue","locationValue","projectcodeValue","jobtypeValue","workshiftValue","hiringMgrValue","status"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dataList.size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+totalSize+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dataList,fields)+
				 "}";
                
*/
%>

<%=data%>
