<%@ include file="../common/include.jsp" %>

<!doctype html>

<html>


<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="Hires360 hiring made easy. Talent aquisition connects agencies , employees , referrals and out siders" />
	<meta name="keywords" content="Hire360, talent , resume, free applicant tracking, applicant manage, requisitions , on board, offer, applicant tracking for startup, EEO report" />

	<link href="jsp/jobboard/css/reset.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/core.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/colors_blue_and_green.css" rel="stylesheet" type="text/css" title="Blue and green" media="screen" />
	
	<link href="jsp/jobboard/css/slider.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="jsp/jobboard/css/lightbox.css" rel="stylesheet" type="text/css" media="screen" />
	<!--[if lte IE 8]>
		<link href="css/ie.css" rel="stylesheet" type="text/css" media="screen" />
	<![endif]-->
	<link href="http://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="jsp/jobboard/scripts/styleswitcher.js"></script>

	<title>Hires360 Jobboard hiring made easy</title>

</head>

<script type="text/javascript" src="jsp/js/animatedcollapse.js"></script>

<%
String search_type = (String)request.getAttribute("search_type");
%>
<script type="text/javascript">
var stype ="<%=search_type%>";

/*if(stype == 'simple'){
animatedcollapse.addDiv('keywordsearch', 'fade=0,speed=400,group=pets1')
animatedcollapse.addDiv('advancesearch', 'fade=0,speed=400,group=pets1,hide=1')
}else{*/

//animatedcollapse.addDiv('keywordsearch', 'fade=0,speed=400,group=pets1,hide=0')
animatedcollapse.addDiv('advancesearch', 'fade=0,speed=400,group=pets1,hide=0')

	//}


animatedcollapse.ontoggle=function($, divobj, state){ 
}

animatedcollapse.init()

</script>
 <%
  String datepattern = Constant.getValue("defaultdateformat");
  String l_code = (String)request.getAttribute("l_code");
  String ensuperUserKey = (String)session.getAttribute(Common.SUPER_USER_KEY);
 
  String orgname = (String)session.getAttribute("orgname");
  com.bean.Locale locale = new com.bean.Locale();
  locale.setLocaleCode(l_code);
  User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
  if(user1 != null){
  datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
}

 UserRegData userregdata = BOFactory.getUserBO().getUserRegDataById(new Long(EncryptDecrypt.decrypt(ensuperUserKey)).longValue());

 String subdomainname = userregdata.getSubdomain()+"."+Constant.getValue("domain.name");
%>



 

<bean:define id="aform" name="jobRequisitionForm" type="com.form.JobRequisitionForm" />
<%
String datastring1 = (String)request.getAttribute("datastring1");
String searchpagedisplay = (String)request.getAttribute("searchpagedisplay");

%>
<script language="javascript">

 function searchjobs(){
	
document.jobRequisitionForm.action = "jobs.do?method=jobsearchsubmit&l_code=<%=l_code%>&search_type=advance&superUserKey=<%=ensuperUserKey%>";
document.jobRequisitionForm.submit();
}
function simplesearchjobs(){
	
document.jobRequisitionForm.action = "jobs.do?method=jobsearchsubmit&l_code=<%=l_code%>&search_type=simple&superUserKey=<%=ensuperUserKey%>";
document.jobRequisitionForm.submit();
}


function retrieveURL(url) {
   //convert the url to a string
    document.getElementById("loading").style.visibility = "visible";

	
	var ids ="";
 
  for (var i = 0; i < document.jobRequisitionForm.orgIds.options.length; i++){
    if (document.jobRequisitionForm.orgIds.options[ i ].selected){
		ids = ids + document.jobRequisitionForm.orgIds.options[ i ].value + ",";
	}
	}

	
	url=url+"&orgId="+ids;
	
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}
}
	 
function processStateChange() {
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		spanElements = splitTextIntoSpan(req.responseText);

    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(spanElements);
		    //onOtherStateSel();
    	} 
    	document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtml(newTextElements){
	//loop through newTextElements
  	for(var i=newTextElements.length-1;i>=0;--i){	//check that this begins with <span
     	if(newTextElements[i].indexOf("<span")>-1){
	      	//get the span name - sits between the 1st and 2nd quote mark Make sure your spans are in the format
    	  	//<span id="someName">NewContent</span>
	    	  startNamePos=newTextElements[i].indexOf('"')+1;
	    	  endNamePos=newTextElements[i].indexOf('"',startNamePos);
		      name=newTextElements[i].substring(startNamePos,endNamePos);
    	                    
	    	  //get the content - everything  after the first > mark
		      startContentPos=newTextElements[i].indexOf('>')+1; 
    		  content=newTextElements[i].substring(startContentPos);
                        
	    	 //Now update the existing Document with this element, checking that this element exists in the document
	    	  if(document.getElementById(name)){
				  
		    	  if(name="departments")	document.getElementById(name).innerHTML = content;
			  }	   			
   	 	}
	}
}

function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}

 function resetData(){
	  document.jobRequisitionForm.keyword1.value="";
  document.jobRequisitionForm.jobTitle.value="";
 
  document.jobRequisitionForm.orgIds.value="";
  //document.jobRequisitionForm.departmentIds.value="";
  document.jobRequisitionForm.cri.value="";
  document.jobRequisitionForm.jobtypeIds.value="";
  document.jobRequisitionForm.jobreqcode.value="";
  document.jobRequisitionForm.jobgradeIds.value="";

	
    
 } 

 function clearForm(oForm) {
    
  var elements = oForm.elements; 
    
  //oForm.reset();

  for(i=0; i<elements.length; i++) {
      
	field_type = elements[i].type.toLowerCase();
	
	switch(field_type) {
	
		case "text": 
		case "password": 
		case "textarea":
	        case "hidden":	
			
			elements[i].value = ""; 
			break;
        
		case "radio":
		case "checkbox":
  			if (elements[i].checked) {
   				elements[i].checked = false; 
			}
			break;

		case "select-one":
		case "select-multi":
            		elements[i].selectedIndex = -1;
			break;

		default: 
			break;
	}
    }
}


</script>



<body class="yui-skin-sam">

<%
String wsint = (String)session.getAttribute("wsint");
if(wsint != null && !wsint.equals("yes")){
%>

	<div id="header_wrap">
    
	<div id="header">
		<%
		    
			if(userregdata.getLogoPhotoId() == 0){
			%>
			
		<a href="http://<%=subdomainname%>" ><img src="jsp/jobboard/images/logo_green.png" width="165" height="53" alt="" border="0"/></a>
			<%}else{
				String imgurl = "jsp/emp/profilePhoto.jsp?id="+userregdata.getLogoPhotoId();
			%>

			<a href="#" ><img src="<%=imgurl%>" border="0" width="165" height="53" alt="" /></a>

			<%}%>
		
			<ol id="nav">
				<li><a href="#"> Jobs at <b><%=orgname%> </b></a></li>
							
			</ol>
						
			<div class="clear"></div>

		</div> <!-- End of header -->
	<%}%>

	</div> <!-- End of header wrap -->
		
<table width="98%">
<tr>
<td width="800px" valign="top">


			<html:form action="/jobs.do?method=jobsearchsubmit">

<div style="background: #f3f3f3;"><a href="#" rel="toggle[advancesearch]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a> Search </div>
<div id="advancesearch" style="width  100%;">


<br>


<table border="0" width="100%">

<tr>
<td><%=Constant.getResourceStringValue("aquisition.applicant.keyword",locale)%></td>
<td><html:text size="50" property="keyword1" maxlength="500"/>
</td>
<td><%=Constant.getResourceStringValue("Requisition.jobtitle",locale)%></td>
<td><html:text  property="jobTitle" size="50" maxlength="600"/></td>
</tr>
		
	<!--<tr>
			<td><%=Constant.getResourceStringValue("Requisition.JobPostedDate",locale)%></td>
			<td>
			<select name="cri">
		
			<% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("before")){%>
  <option value="before" selected="yes" ><%=Constant.getResourceStringValue("Requisition.brfore",locale)%></option>
  <%}else {%>
<option value="before"><%=Constant.getResourceStringValue("Requisition.brfore",locale)%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("on")){%>
  <option value="on" selected="yes" ><%=Constant.getResourceStringValue("Requisition.on",locale)%></option>
  <%}else {%>
<option value="on"><%=Constant.getResourceStringValue("Requisition.on",locale)%></option>
  <%}%>
  <% if(!StringUtils.isNullOrEmpty(aform.getAppliedcri()) && aform.getAppliedcri().equals("after")){%>
  <option value="after" selected="yes" ><%=Constant.getResourceStringValue("Requisition.after",locale)%></option>
  <%}else {%>
<option value="after"><%=Constant.getResourceStringValue("Requisition.after",locale)%></option>
  <%}%>
 
</select>


<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx">
var cal1xx = new CalendarPopup("testdiv1");
cal1xx.showNavigationDropdowns();

</SCRIPT>


<INPUT TYPE="text" NAME="searchposteddate" readonly="true" value="<%=(aform.getSearchposteddate() == null) ? "" :aform.getSearchposteddate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx.select(document.jobRequisitionForm.searchposteddate,'anchor1xx','<%=datepattern%>'); return false;" TITLE="select date" NAME="anchor1xx" ID="anchor1xx"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
<DIV ID="testdiv1" STYLE="position:absolute;visibility:none;background-color:white;layer-background-color:white;"></DIV>

</td>
			<td><%=Constant.getResourceStringValue("Requisition.minexp",locale)%></td>
			<td>
			<html:text styleClass="multipleselect" property="minyearsofExpRequired" maxlength="2"/>
			</td>

		
		</tr>

-->
		<tr>

			<td><%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",locale)%></td>
			<td>
			<html:select multiple="true" size="3"  name="jobRequisitionForm"  property="locationIds">
			<bean:define name="jobRequisitionForm" property="locationList" id="locationList" />

            <html:options collection="locationList" property="locationId"  labelProperty="locationName"/>
			</html:select>
		</td>
			<td><%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",locale)%></td>
			<td>
			<html:select multiple="true" size="3" property="primarySkills">
			
			<bean:define name="jobRequisitionForm" property="primarySkillList" id="primarySkillList" />
            <html:options collection="primarySkillList" property="technialSkillName"  labelProperty="technialSkillName"/>
			</html:select>
			</td>
		</tr>


		
		
<tr>
<td>
<input type="button" name="search" value="<%=Constant.getResourceStringValue("hr.button.search",locale)%>" onClick="searchjobs()" class="button">
</td>
<td></td>
</tr>
</table>
</div>

</html:form>

<% if(searchpagedisplay != null && searchpagedisplay.equals("yes")){
	
	String postdate = null;
	if(user1 != null){ 
	if(!StringUtils.isNullOrEmpty(aform.getSearchposteddate())){
            postdate =  DateUtil.convertFromTimezoneToTimezoneDate(aform.getSearchposteddate(),datepattern,user1.getTimezone().getTimezoneCode(),null);
	}
	}else{
   postdate = aform.getSearchposteddate();
	}
	
	
	%>

	<br>

<h2>Current Openings</h2>
<div id="dynamicdata" class="div"></div>

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->



<script type="text/javascript">

YAHOO.example.DynamicData = function() {




var formatUrl1 = function(elCell, oRecord, oColumn, sData) {
			var ndata1 = sData + '&nbsp;<img src="jsp/images/open_button.gif" border="0" alt="Job details" title="Job details" height="20"  width="19"  align="middle"/>';
         elCell.innerHTML = "<a href='jobs.do?method=jobdetailsext&reqid="+ oRecord.getData("jobreqId") +"&secureid="+oRecord.getData("uuid") +"&source=OURCOMPANY" + "'"+ ">" + sData + "</a>";
		 
        //elCell.innerHTML = "<a href='#' onclick='test()'"+ ">" + sData + "</a>";
        }

    // Column definitions
    var myColumnDefs = [
			{key:"jobreqId", hidden:true},
		{key:"uuid", hidden:true},
		{key:"templateId", hidden:true},		
            {key:"jobTitle", label:"<%=Constant.getResourceStringValue("Requisition.jobtitle",locale)%>", sortable:true, resizeable:true,formatter:formatUrl1,width:150},
   		//{key:"departmentValue", label:"<%=Constant.getResourceStringValue("admin.Deparment.depts",locale)%>", sortable:false, resizeable:true},
		  	{key:"locationValue", label:"<%=Constant.getResourceStringValue("admin.organization.orglistbody.Location",locale)%>", sortable:false, resizeable:true,width:100},
		 {key:"jobtypeValue", label:"<%=Constant.getResourceStringValue("Requisition.jobtype",locale)%>", sortable:false, resizeable:true},
		{key:"workshiftValue", label:"<%=Constant.getResourceStringValue("Requisition.workshift",locale)%>", sortable:false, resizeable:true},
		{key:"targetfinishdate", label:"<%=Constant.getResourceStringValue("Requisition.Target.finish.date",locale)%>", sortable:true, resizeable:true},
		{key:"primarySkill", label:"<%=Constant.getResourceStringValue("aquisition.applicant.Primaryskill",locale)%>", sortable:false, resizeable:true}		          
        ];



    // Custom parser
    var stringToDate = function(sData) {
        var array = sData.split("-");
        return new Date(array[1] + " " + array[0] + ", " + array[2]);
    };
    

    // DataSource instance
    var myDataSource = new YAHOO.util.DataSource("jobs.do?method=jobSearchListpage&search_type=<%=search_type%>&ensuperUserKey=<%=ensuperUserKey%>&keyword=<%=aform.getSerachCriteria().getKeyword()%>&jobTitle=<%=aform.getJobTitle()%>&postdate=<%=postdate%>&>&cri=<%=aform.getAppliedcri()%>&locationId=<%=aform.getSerachCriteria().getLocationId()%>&jobreqid=<%=aform.getSerachCriteria().getJobreqId()%>&orgId=<%=aform.getSerachCriteria().getOrgId()%>&departmentId=<%=aform.getSerachCriteria().getDepartmentIds()%>&jobgradeId=<%=aform.getSerachCriteria().getJobgradeId()%>&jobtypeId=<%=aform.getSerachCriteria().getJobtypeId()%>&statuscri=<%=aform.getSerachCriteria().getStatus()%>&statecri=<%=aform.getSerachCriteria().getState()%>&jobreqcode=<%=aform.getSerachCriteria().getJobreqcode()%>&uuid=<%=aform.getUuid()%>&budgetcodeid=<%=aform.getSerachCriteria().getBudgetcodeId()%>&workshiftId=<%=aform.getSerachCriteria().getWorkshiftId()%>&categoryId=<%=aform.getSerachCriteria().getCategoryId()%>&primarySkill=<%=aform.getSerachCriteria().getPrimarySkill()%>&minyearsofExpRequired=<%=aform.getMinyearsofExpRequired()%>&ddd="+(new Date).getTime()+"&");


   
    
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	 myDataSource.maxCacheEntries    = 1;
    myDataSource.responseSchema = {
        resultsList: "records",
        fields: [
            {key:"jobreqId"},
			{key:"uuid"},
             {key:"templateId"},
            {key:"jobTitle"},
            {key:"organizationValue"},
		   {key:"departmentValue"},
			{key:"locationValue"},
		{key:"projectcodeValue"},
			{key:"jobtypeValue"},
			{key:"workshiftValue"},
			{key:"publishedDate"},
			{key:"targetfinishdate"},
			{key:"primarySkill"},
			{key:"categoryValue"},
			{key:"jobGradeValue"}
		
			
        ],
        metaFields: {
            totalRecords: "totalRecords" // Access to value in the server response
        }
    };
    
    // DataTable configuration
    var myConfigs = {
        initialRequest: "sort=publishedDate&dir=desc&startIndex=0&results=15", // Initial request for first page of data
        dynamicData: true, // Enables dynamic server-driven data
        sortedBy : {key:"jobreqId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
        paginator: new YAHOO.widget.Paginator({ rowsPerPage:15 }) // Enables pagination 
    };
    
    // DataTable instance
    var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
 // Enable row highlighting
    myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
    myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

    // Update totalRecords on the fly with value from server
    myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
        oPayload.totalRecords = oResponse.meta.totalRecords;
        return oPayload;
    };

    
    return {
        ds: myDataSource,
        dt: myDataTable
    };
        
}();

</script>
<%}%>



	<div class="clear"></div>

</td>
<td width="10px">
</td>
<td valign="top" align="left" width="600px">
<div id="content">
<%
String defaultvalue="Thanks for visiting our Job Board. Please review our open positions and apply to the positions that match your qualifications";
%>
<%=(userregdata.getCompanyInfo()==null)?defaultvalue:userregdata.getCompanyInfo()%>
</div>
</td>
</tr>
</table>
</html>
</body>

	


