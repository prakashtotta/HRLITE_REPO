
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%
  
 
 
  //response.setHeader("Expires", "Thursday, February 15 2009 12:00:00 GMT");


  %>

  <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
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


String emailtemplatename = (String)request.getParameter("emailtemplatename");
String emailSubject = (String)request.getParameter("emailSubject");
String createdBy = (String)request.getParameter("createdBy");
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

	
		
		dataList = BOFactory.getLovBO().getEmailTempltesForPagination(user1,emailtemplatename,emailSubject,createdBy,dir_str,sort_str,results,startIndex);
		totalSize = BOFactory.getLovBO().getCountOfAllEmailTemplates(user1,emailtemplatename,emailSubject,createdBy);
	
		
	
	

System.out.println(totalSize);
String[] fields = {"emailtemplateId","emailtemplatename","createdBy","createdDate","updatedDate","updatedBy","emailSubject"};


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
