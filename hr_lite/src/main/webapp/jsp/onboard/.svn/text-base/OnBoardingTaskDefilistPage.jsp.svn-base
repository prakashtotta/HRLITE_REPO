<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="com.form.*" %>


<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "taskdefid"; // default don't sort
String dir = "asc"; // default sort dir is asc

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);


String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

System.out.println("results_str"+results_str);
System.out.println("startIndex_str"+startIndex_str);
System.out.println("sort_str"+sort_str);
System.out.println("dir_str"+dir_str);

String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String taskname = (String)request.getParameter("taskname");
String primaryownid = (String)request.getParameter("primaryownerid");

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

	dataList=BOFactory.getOnBoardingTaskDefiBO().getAllOnBoardingTaskDefinationPaginationSearch(user1.getSuper_user_key(),taskname,primaryownid,results,startIndex,dir_str,sort_str);
    totalSize=BOFactory.getOnBoardingTaskDefiBO().getAllOnBoardingTaskDetailssearch(user1.getSuper_user_key(),taskname,primaryownid);

}else{

 dataList =BOFactory.getOnBoardingTaskDefiBO().getAllOnBoardingTaskDefiForPagination(user1.getSuper_user_key(),results,startIndex,dir_str,sort_str);



List allDataList =BOFactory.getOnBoardingTaskDefiBO().getAllOnBoardingTaskDefiDetails(user1.getSuper_user_key());
totalSize = allDataList.size();

}	

System.out.println(totalSize);



String[] fields = {"taskdefid","taskName","taskDesc","primaryOwnerName","isGroup","primaryOwnerId","eventType","createdBy","createdDate"}; 


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
