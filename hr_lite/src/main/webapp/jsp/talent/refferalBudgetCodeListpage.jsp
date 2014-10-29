<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>

<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "ref_budgetId"; // default don't sort
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


List dataList = BOFactory.getRefferalBudgetCodeBO().getAllRefferalBudgetDetailsForPagination(user1,results,startIndex,dir_str,sort_str);


int totalSize = 0;

List allDataList = BOFactory.getRefferalBudgetCodeBO().getAllRefferalBudgetDetails(user1);
totalSize = allDataList.size();

System.out.println(totalSize);
String[] fields = {"ref_budgetId","ref_budgetCode","ref_budgetCentreName","ref_budgetamount","ref_budgetCurrency","orgName","deptName","ref_budgetYear"}; 


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
