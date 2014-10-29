
<%@ page import="com.bo.*"%>
<%@ page import="com.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.bean.criteria.*"%>
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

String keywords = (String)request.getParameter("keywords");
String fullname = (String)request.getParameter("fullname");
String reqid = (String)request.getParameter("reqid");
String prevorg = (String)request.getParameter("prevorg");
String email = (String)request.getParameter("email");


String appliedcri = (String)request.getParameter("appliedcri");
String searchpagedisplay = (String)request.getParameter("searchpagedisplay");
String applicantNo = (String)request.getParameter("applicantNo");
String interviewstate = (String)request.getParameter("interviewstate");
String fromdate = (String)request.getParameter("fromdate"); 
String todate = (String)request.getParameter("todate"); 
String tags = (String)request.getParameter("tagsname");



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
System.out.println("satyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+tags);
List<String> tagList = new ArrayList<String>();
if(!StringUtils.isNullOrEmpty(tags) && !tags.equals("null")){
			 tagList  = StringUtils.tokenizeString(tags, ",");
}

List dataList = new ArrayList();

ApplicantSearchCriteria criteria = new ApplicantSearchCriteria();
	criteria.setReqid(reqid);
	criteria.setAppliedcri(appliedcri);
	criteria.setFullname(fullname);
	criteria.setInterviewstate(interviewstate);	
	criteria.setPrevorg(prevorg);
	criteria.setEmail(email);
	criteria.setApplicantNo(applicantNo);
	criteria.setFromdate(fromdate);
	criteria.setTodate(todate);
	criteria.setTagList(tagList);
	criteria.setKeywords(keywords);
	criteria.setCountRequired(true);

int totalSize = 0;

Map m = BOFactory.getApplicantBO().searchOwnApplicantsForPaginationRecruiter(user1,criteria,results,startIndex,dir_str,sort_str);
	
	
dataList = (List)m.get(Common.APPLICANT_LIST);
 
 totalSize = ((Integer)m.get(Common.APPLICANT_COUNT)).intValue();

	
	//totalSize = ApplicantBO.getCountOfsearchOwnApplicantsForPaginationRecruiter(user1,criteria);


	

String[] fields = ScreenSettingUtils.getArrayOfApplicationScreenSettingsKeyListPage(Common.APP_SEARCH_SCREEN_RECRUITER);
/*String[] fields = {"applicantId","fullName","email","phone","city","heighestQualification","interviewState","appliedDate","joineddate","targerofferdate","offerreleasedate","targetjoiningdate","jobTitle","referrerName","ownername","uuid","createdBy","reqId"};*/


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
