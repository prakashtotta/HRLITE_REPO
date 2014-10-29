
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
  //
// 
 //
  //response.setHeader("Expires", "Thursday, February 15 2009 12:00:00 GMT");


  %>

<%
User user1 = (User)session.getAttribute(Common.USER_DATA);

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");
String orgId = (String)request.getParameter("orgId");
String recruiterid = (String)request.getParameter("recruiterid");
String deptId = (String)request.getParameter("deptId");


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

DataTableBean dbean = BOFactory.getJobRequistionBO().jobRequistionsByOrgByRecruiterByDeptForPagination(orgId,recruiterid,deptId,results,startIndex,dir_str,sort_str);


String[] fields = {"jobreqId","jobreqName","organizationValue","departmentValue","locationValue","projectcodeValue","jobtypeValue","workshiftValue","hiringMgrValue","templateName","state","templateId","uuid"};


String data =  "{"+ "\n"+ 
               "\""+ "recordsReturned"+"\""+":"+dbean.getValueList().size()+ ","+"\n"+ 
                "\""+  "totalRecords"+"\""+":"+dbean.getTotalcount()+","+"\n"+ 
				 "\""+ "startIndex"+"\""+":"+startIndex+","+"\n"+ 
				 "\""+ "sort"+"\""+":"+"\""+sort+"\""+","+"\n"+ 
				 "\""+ "dir"+"\""+":"+"\""+dir+"\""+","+"\n"+ 
				  "\""+ "pageSize"+"\""+":"+results+","+"\n"+ 
				  "\""+"records"+"\""+":"+StringUtils.createJasonDataTable(dbean.getValueList(),fields,user1)+
				 "}";
                

%>

<%=data%>
