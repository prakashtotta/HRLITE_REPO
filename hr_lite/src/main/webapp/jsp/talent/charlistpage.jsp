<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>

<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
		response.setContentType("application/json");
%>


 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>


<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "selId"; // default don't sort
String dir = "asc"; // default sort dir is asc





String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");
String charname = (String)request.getParameter("charname");
System.out.println("charname : "+charname);

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

List dataList = BOFactory.getCharBO().getCharListForPagination(user1,results,startIndex,dir_str,sort_str,charname);


int totalSize = 0;

totalSize = BOFactory.getCharBO().getCountOfAllChar(user1,charname);

	

System.out.println(totalSize);
String[] fields = {"charId","charName","weight","createdBy","createdDate"};


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
	