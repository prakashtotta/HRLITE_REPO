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
String sort = "orgemailtemplid"; // default don't sort
String dir = "asc"; // default sort dir is asc




String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");


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

List dataList = BOFactory.getOrganizationEmailTemplateGroupBO().getAllOrgEmailTemplateGroupDetailsForPagination(results,startIndex);

System.out.println("size"+dataList.size());
int totalSize = 0;

List allDataList = BOFactory.getOrganizationEmailTemplateGroupBO().getAllOrgEmailTemplateGroupDetails();
totalSize = allDataList.size();
	

System.out.println(totalSize);
String[] fields = {"orgemailtemplid","eventCode","orgEmailName","orgName","createdBy","createdDate","emailtemplatename","updatedBy","updatedDate"};


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
