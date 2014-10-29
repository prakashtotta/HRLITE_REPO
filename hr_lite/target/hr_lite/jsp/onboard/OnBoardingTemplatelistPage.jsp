<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>


<%@ page import="com.bean.*"%>

<%@ page import="com.common.*"%>
 
 <%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
%>

<%
////response.setHeader("Cache-Control", "no-cache");
	//	//response.setHeader("Pragma", "no-cache");
	//	//response.setIntHeader("Expires", 0);
		response.setContentType("application/json");
%>

<%

int results = -1; // default get all
int startIndex = 0; // default start at 0
String sort = "templateid"; // default don't sort
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
String onboardtemname = (String)request.getParameter("onboardtemname");
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

	dataList=OnBoardingTemplateBO.getAllOnBoardingTemplateForPaginationSearch(user1.getSuper_user_key(),onboardtemname,primaryownid,results,startIndex,dir_str,sort_str);
    totalSize= OnBoardingTemplateBO.getAllOnBoardingTemplateDetailssearch(user1.getSuper_user_key(),onboardtemname,primaryownid);

}else{

dataList = OnBoardingTemplateBO.getAllOnBoardingTemplateForPagination(user1.getSuper_user_key(),results,startIndex,dir_str,sort_str);
totalSize = OnBoardingTemplateBO.getAllOnBoardingTemplateDetails(user1.getSuper_user_key());


}

System.out.println(totalSize);
String[] fields = {"templateid","templateName","templateDesc","createdBy","createdDate"}; 


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