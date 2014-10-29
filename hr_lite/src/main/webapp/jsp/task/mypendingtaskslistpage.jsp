<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>

 

<%


int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "username"; // default don't sort
String dir = "asc"; // default sort dir is asc

User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);

String results_str = (String)request.getParameter("results");
String startIndex_str = (String)request.getParameter("startIndex");
String sort_str = (String)request.getParameter("sort");
String dir_str = (String)request.getParameter("dir");

String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String tasktype = (String)request.getParameter("tasktype");
String assignedbyUserId = (String)request.getParameter("assignedbyUserId");


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
dataList = TaskBO.getPendingTasksForPaginationBysearchTypeandAssignedby(user1.getUserId(),tasktype,assignedbyUserId,results,startIndex,dir_str,sort_str);
totalSize = TaskBO.getCountOfPendingTasksBysearchTypeandAssignedby(user1.getUserId(),tasktype,assignedbyUserId);
}else{

dataList = TaskBO.getPendingTasksForPagination(user1.getUserId(),results,startIndex,dir_str,sort_str);
totalSize = TaskBO.getCountOfPendingTasks(user1.getUserId());
}

String[] fields = {"taskId","idvalue","taskname","tasktype","assignedbyUserName","assignedtoUserName","status","createdBy","createdDate","updatedBy","updatedDate","uuid","assignedtoUserName1","eventdate"};


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
