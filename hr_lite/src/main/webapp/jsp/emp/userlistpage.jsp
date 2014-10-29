<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>


<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc



String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");
String type = (String)request.getParameter("type");
String statuscri = (String)request.getParameter("statuscri");
String orgId = (String)request.getParameter("orgId");
String departmentId = (String)request.getParameter("departmentId");
String projectId = (String)request.getParameter("projectId");
String designationId = (String)request.getParameter("designationId");
String firstname = (String)request.getParameter("firstname");
String lastname = (String)request.getParameter("lastname");
String countryId = (String)request.getParameter("countryId");
String stateId = (String)request.getParameter("stateId");
String userName = (String)request.getParameter("userName");
String city = (String)request.getParameter("city");
String emailId = (String)request.getParameter("emailId");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
System.out.println("<<<<  designationId >>"+designationId);
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
if(type != null && type.equals(Common.USER_TYPE_VENDOR)){
	 dataList = UserBO.searchVendorsForPagination(user1,searchpagedisplay,statuscri,countryId,stateId,city,firstname,lastname,emailId,dir_str,sort_str,results,startIndex,type);
	 totalSize = UserBO.searchVendorsCount(user1,searchpagedisplay,statuscri,countryId,stateId,city,firstname,lastname,type,emailId);

}else{
	System.out.println("Project Id : "+projectId);
	
	 dataList = UserBO.searchUsersForPagination(user1,statuscri,orgId,departmentId,projectId,designationId,firstname,lastname,userName,emailId,dir_str,sort_str,results,startIndex,type);
	 totalSize = UserBO.searchUsersCount(user1,statuscri,orgId,departmentId,projectId,designationId,firstname,lastname,userName,type,emailId);


}





	


String[] fields = {"userId","firstName","lastName","emailId","orgName","userName","departmentName","projectcodeName","phoneOffice","roleValue","localeValue","city","phoneHome","countryValue","stateValue","designationValue","status","userIdEnc"};


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
