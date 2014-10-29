
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.criteria.*"%>
<%
 
 
 
  


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

String vendorid = (String)request.getParameter("vendorid");
String reqid = (String)request.getParameter("reqid");
String appliedcri = (String)request.getParameter("appliedcri");
String fullname = (String)request.getParameter("fullname");
String interviewstate = (String)request.getParameter("interviewstate");
String prevorg = (String)request.getParameter("prevorg");
String email = (String)request.getParameter("email");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String applicantNo = (String)request.getParameter("applicantNo");
String fromdate = (String)request.getParameter("fromdate"); 
String todate = (String)request.getParameter("todate"); 

String orgId = (String)request.getParameter("orgId"); 
String departmentId = (String)request.getParameter("departmentId"); 
String projectcodeId = (String)request.getParameter("projectcodeId"); 

String onboardpstatus = (String)request.getParameter("onboardpstatus"); 
String tagId = (String)request.getParameter("tagName");

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




int totalSize = 0;


List dataList = new ArrayList();

ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
	criteria.setVendorId(vendorid);
	criteria.setReqid(reqid);
	criteria.setAppliedcri(appliedcri);
	criteria.setFullname(fullname);
	criteria.setInterviewstate(interviewstate);	
	criteria.setPrevorg(prevorg);
	criteria.setEmail(email);
	criteria.setApplicantNo(applicantNo);
	criteria.setFromdate(fromdate);
	criteria.setTodate(todate);
	criteria.setOrgId(orgId);
	criteria.setDepartmentId(departmentId);
	criteria.setProjectcodeId(projectcodeId);
	criteria.setOnboardingProcessStatus(onboardpstatus);
	

	if(!StringUtils.isNullOrEmpty(tagId) && !tagId.equals("null")){
		List<String> tagList = new ArrayList<String>();
		tagList.add(tagId);
		criteria.setTagList(tagList);
	}

	
	

 dataList = BOFactory.getApplicantBO().searchOnBoardingApplicantsForPagination(user1,criteria,results,startIndex,dir_str,sort_str);
totalSize = BOFactory.getApplicantBO().getCountOfsearchOnBoardApplicants(user1,criteria);



String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage(Common.ON_BOARDING_SEARCH_SCREEN);


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
