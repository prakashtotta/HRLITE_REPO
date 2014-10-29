<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>


<%

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "employeeReferalId"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");


String employeecode = (String)request.getParameter("employeecode");
String employeename = (String)request.getParameter("employeename");
String employeeemail = (String)request.getParameter("employeeemail");
String status = (String)request.getParameter("status");
String orgId = (String)request.getParameter("orgId");
String departmentId = (String)request.getParameter("departmentId");
String projectId = (String)request.getParameter("projectId");
System.out.println("status in Jsp : "+status);

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
List dataList = new ArrayList();
int totalSize = 0;


	if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
		System.out.println("*** success  refferalempListPage");
		System.out.println(" status : "+status);
		 dataList = RefBO.searchRefferalEmpForPagination(employeecode,employeename,employeeemail,orgId,departmentId,projectId,status,dir,sort,results,startIndex);
		 totalSize = RefBO.searchRefferalEmpCount(employeecode,employeename,employeeemail,orgId,departmentId,projectId,status);
		
	}else{
		System.out.println("*** results : "+results+" startIndex : "+startIndex+" dir_str : "+dir_str+" sort_str : "+sort_str);
		dataList = RefBO.searchRefferalEmpForPagination(results,startIndex,dir,sort);
		totalSize = RefBO.searchRefferalEmpCount();

	}





	


String[] fields = {"employeeReferalId","employeecode","employeename","employeeemail","orgName","departmentName","projectcodeName","status"};


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

