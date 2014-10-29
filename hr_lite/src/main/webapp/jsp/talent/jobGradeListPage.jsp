<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.criteria.*"%>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

%>

<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "jobgradeId"; // default don't sort
String dir = "asc"; // default sort dir is asc




String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

System.out.println("results_str"+results_str);
System.out.println("startIndex_str"+startIndex_str);
System.out.println("sort_str"+sort_str);
System.out.println("dir_str"+dir_str);


String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String jgradename = (String)request.getParameter("jobgradename");


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
List dataListsize = new ArrayList();
int totalSize = 0;



	dataList=BOFactory.getLovBO().getAllJobGradeDetailsForPaginationByJobgradeName(user1,jgradename,results,startIndex,dir_str,sort_str);
    dataListsize= BOFactory.getLovBO().getAllJobGradeDetailsByJoggradeName(user1,jgradename);
    totalSize=dataListsize.size();




System.out.println(totalSize);
String[] fields = 

{"jobgradeId","jobGradeName","jobGradeDesc","jobGradeResponsibility"};


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
