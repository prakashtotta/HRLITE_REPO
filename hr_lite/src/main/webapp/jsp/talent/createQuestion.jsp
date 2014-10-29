<%@ include file="../common/include.jsp" %>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
<style>
span1{color:#ff0000;}
</style>
 <html>
  <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>

  

<style type="text/css">
	#myAutoComplete {
		width:25em; /* set width here or else widget will expand to fit its container */
		padding-bottom:2em;
	}

	body {
	margin:0;
	padding:0;
}
</style>

<STYLE>
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation
			{
			background-color:#6677DD;
			text-align:center;
			vertical-align:center;
			text-decoration:none;
			color:#FFFFFF;
			font-weight:bold;
			}  
	.TESTcpDayColumnHeader,
	.TESTcpYearNavigation,
	.TESTcpMonthNavigation,
	.TESTcpCurrentMonthDate,
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDate,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDate,
	.TESTcpCurrentDateDisabled,
	.TESTcpTodayText,
	.TESTcpTodayTextDisabled,
	.TESTcpText
			{
			font-family:arial;
			font-size:8pt;
			}
	TD.TESTcpDayColumnHeader
			{
			text-align:right;
			border:solid thin #6677DD;
			border-width:0 0 1 0;
			}
	.TESTcpCurrentMonthDate,
	.TESTcpOtherMonthDate,
	.TESTcpCurrentDate
			{
			text-align:right;
			text-decoration:none;
			}
	.TESTcpCurrentMonthDateDisabled,
	.TESTcpOtherMonthDateDisabled,
	.TESTcpCurrentDateDisabled
			{
			color:#D0D0D0;
			text-align:right;
			text-decoration:line-through;
			}
	.TESTcpCurrentMonthDate
			{
			color:#6677DD;
			font-weight:bold;
			}
	.TESTcpCurrentDate
			{
			color: #FFFFFF;
			font-weight:bold;
			}
	.TESTcpOtherMonthDate
			{
			color:#808080;
			}
	TD.TESTcpCurrentDate
			{
			color:#FFFFFF;
			background-color: #6677DD;
			border-width:1;
			border:solid thin #000000;
			}
	TD.TESTcpCurrentDateDisabled
			{
			border-width:1;
			border:solid thin #FFAAAA;
			}
	TD.TESTcpTodayText,
	TD.TESTcpTodayTextDisabled
			{
			border:solid thin #6677DD;
			border-width:1 0 0 0;
			}
	A.TESTcpTodayText,
	SPAN.TESTcpTodayTextDisabled
			{
			height:20px;
			}
	A.TESTcpTodayText
			{
			color:#6677DD;
			font-weight:bold;
			}
	SPAN.TESTcpTodayTextDisabled
			{
			color:#D0D0D0;
			}
	.TESTcpBorder
			{
			border:solid thin #6677DD;
			}
			legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
</STYLE>

 </HEAD>
<bean:define id="questionsform" name="questionForm" type="com.form.QuestionForm" />
 


<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>



<script language="javascript">

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		
			 parent.parent.GB_hide();
	   } 
	}

function closewindow(){
	
	 parent.parent.GB_hide();
}



function savedata(){
		var alertstr = "";
	var showalert=false;
	
		
	 
	var name=document.questionForm.questionName.value.trim();
	var typeVal=document.questionForm.typeVal.value.trim();
	var option=document.questionForm.answerOption.value;
	var corrtans=document.questionForm.correctAns.value;
    /*this comented code validation correct answer if does not matched to optipn list*/
	
	/*
	
	var m;	
    var checkoptioncorrect="false";
    var countoption=0;
    var k;
    var string1="";
    var myTempOption=new Array();
       for(k=0;k<option.length;k++){
         string1=string1+option[k];
         if(option[k]=="\n" || k==option.length-1){
         countoption++;
         myTempOption[countoption]=string1;
         string1="";
          }
       }

       if(corrtans.length>0 ){

			 for(m=0;m<myTempOption.length;m++){
               if(corrtans==myTempOption[m]){
               checkoptioncorrect="true";
               break;
			   }
			 }
               if(checkoptioncorrect=="false"){
                 alertstr = alertstr + "Correct ans does not matched to answer option<br>";
		         showalert = true;
		       }
     }

	 */
	
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.QuesName_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(typeVal == "" || typeVal == null || typeVal == "noVal"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Questtype_required",user1.getLocale())%><br>";
		showalert = true;
		}
	 	 
	

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.questionForm.action = "question.do?method=saveQuestion&quntype=MASTER";
      document.questionForm.submit();


 
	}
	

function updatedata(){
		var alertstr = "";
	var showalert=false;
		
	 
	var name=document.questionForm.questionName.value.trim();
	var typeVal=document.questionForm.typeVal.value.trim();
	var option=document.questionForm.answerOption.value;
	var corrtans=document.questionForm.correctAns.value;
    /*this comented code validation correct answer if does not matched to optipn list*/
	
	/*
	
	var m;	
    var checkoptioncorrect="false";
    var countoption=0;
    var k;
    var string1="";
    var myTempOption=new Array();
       for(k=0;k<option.length;k++){
         string1=string1+option[k];
         if(option[k]=="\n" || k==option.length-1){
         countoption++;
         myTempOption[countoption]=string1;
         string1="";
          }
       }
	if(corrtans.length>0 ){

			 for(m=1;m<myTempOption.length;m++){
               if(corrtans==myTempOption[m]){
               checkoptioncorrect="true";
               break;
			   }
			 }

			
              if(checkoptioncorrect=="false"){
                 alertstr = alertstr + "Correct ans does not matched to answer option<br>";
		        showalert = true;
		       }
     }

	 */
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.QuesName_required",user1.getLocale())%><br>";
		showalert = true;
		}
	if(typeVal == "" || typeVal == null || typeVal == "noVal"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Questtype_required",user1.getLocale())%><br>";
		showalert = true;
		}
    

	 	 

	 if (showalert){
     	alert(alertstr);
        return false;
          }
	  document.questionForm.action = "question.do?method=updateQuestion&id="+'<bean:write name="questionForm" property="questionId"/>';
      document.questionForm.submit();
 

 
 
	}
	
	



	function closewindow(){
	  parent.parent.GB_hide();
}




function onclickquestionType(fields){ 

var type=fields.value;
var tempvaltype=document.questionForm.tempvaltype.value;
var tempvalcorrectans=document.questionForm.tempvalcorrectans.value;
var tempvaloption=document.questionForm.tempvaloption.value;
var myTempOption=new Array();



if(type!=null && type=="text"){	
document.getElementById("answerchoicelabel").style.display = 'none';
document.getElementById("dropdowncheckboxradio").style.display = 'none';
document.getElementById("correctanswerlabel").style.display = 'block';
document.getElementById("datelabel").style.display = 'none';
document.getElementById("date").style.display='none';
document.getElementById("correctanswer").style.display = 'block';

if(tempvaltype!="text"){
document.questionForm.correctAns.value="";
}else if(tempvaltype=="text"){
document.questionForm.correctAns.value=tempvalcorrectans;
}
}

if(type!=null && type=="number"){	
document.getElementById("answerchoicelabel").style.display = 'none';
document.getElementById("dropdowncheckboxradio").style.display = 'none';
document.getElementById("correctanswerlabel").style.display = 'block';
document.getElementById("datelabel").style.display = 'none';
document.getElementById("date").style.display='none';
document.getElementById("correctanswer").style.display = 'block';

if(tempvaltype!="number"){
document.questionForm.correctAns.value="";
}else if(tempvaltype=="number"){
document.questionForm.correctAns.value=tempvalcorrectans;
}
}

if(type!=null && type=="checkbox"){	
document.getElementById("answerchoicelabel").style.display = 'block';
document.getElementById("dropdowncheckboxradio").style.display = 'block';
document.getElementById("correctanswerlabel").style.display = 'block';
document.getElementById("datelabel").style.display = 'none';
document.getElementById("date").style.display='none';
document.getElementById("correctanswer").style.display='block';

if(tempvaltype!="checkbox"){
	
document.questionForm.answerOption.value="";
document.questionForm.correctAns.value="";
}else if(tempvaltype=="checkbox"){
document.questionForm.answerOption.value=document.questionForm.answerOption1.value;
document.questionForm.correctAns.value=tempvalcorrectans;
}
}

if(type!=null && type=="dropdown"){
document.getElementById("answerchoicelabel").style.display = 'block';
document.getElementById("dropdowncheckboxradio").style.display = 'block';
document.getElementById("correctanswerlabel").style.display = 'block';
document.getElementById("datelabel").style.display = 'none';
document.getElementById("date").style.display='none';
document.getElementById("correctanswer").style.display='block';

if(tempvaltype!="dropdown"){
document.questionForm.answerOption.value="";
document.questionForm.correctAns.value="";
}else if(tempvaltype=="dropdown"){
document.questionForm.answerOption.value=document.questionForm.answerOption1.value;
document.questionForm.correctAns.value=tempvalcorrectans;
}
}

if(type!=null && type=="radio"){
document.getElementById("answerchoicelabel").style.display = 'block';
document.getElementById("dropdowncheckboxradio").style.display = 'block';
document.getElementById("correctanswerlabel").style.display = 'block';
document.getElementById("datelabel").style.display = 'none';
document.getElementById("date").style.display='none';
document.getElementById("correctanswer").style.display='block';

if(tempvaltype!="radio"){
document.questionForm.answerOption.value="";
document.questionForm.correctAns.value="";
}else if(tempvaltype=="radio"){
document.questionForm.answerOption.value=document.questionForm.answerOption1.value;
document.questionForm.correctAns.value=tempvalcorrectans;
}
}
if(type!=null && type=="date"){

document.getElementById("answerchoicelabel").style.display = 'none';
document.getElementById("dropdowncheckboxradio").style.display = 'none';
document.getElementById("correctanswerlabel").style.display = 'none';
document.getElementById("correctanswer").style.display = 'none';
document.getElementById("datelabel").style.display = 'block';
document.getElementById("date").style.display = 'block';

if(tempvaltype!="date"){
document.questionForm.correctAns.value="";
}else if(tempvaltype=="date"){
document.questionForm.correctAns.value=tempvalcorrectans;
}
}
}

</script>

<body class="yui-skin-sam" >


<%
String saveQuestion = (String)request.getAttribute("saveQuestion");
String updateQuestion = (String)request.getAttribute("updateQuestion");
String deleteQuestion = (String)request.getAttribute("deleteQuestion");

%>
<%
String viewdetailsforselector = (String)request.getAttribute("viewdetailsforselector");
System.out.println("viewdetailsforselector"+viewdetailsforselector);
%>



<html:form action="/question.do?method=saveQuestion" >


	
<% if(deleteQuestion != null && deleteQuestion.equals("yes")){%>
<div class="msg"><font color="white"><%=Constant.getResourceStringValue("admin.Questions.deletemsg",user1.getLocale())%></font><!-- <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></div>
	
<%}else{%>

<!-- value display for selector page-->

<% if(viewdetailsforselector != null && viewdetailsforselector.equals("yes")){%>

<br>
	 <fieldset><legend><%=Constant.getResourceStringValue("admin.Questions.details",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
 <tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.name",user1.getLocale())%></td>
			<td><%=questionsform.getQuestionName()%></td>
		</tr>

	  <tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.type",user1.getLocale())%></td>
			<td><%=questionsform.getQuestionType()%></td>
		</tr>
			
		 
		
            </table>
		<fieldset>
     
	
<!--start for create question-->
<%}else{%>

<% if(saveQuestion != null && saveQuestion.equals("yes")){%>
   <div class="msg"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Questions.savemsg",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font> </a> --></div>
<%}%>
<% if(updateQuestion != null && updateQuestion.equals("yes")){%>
  <div class="msg"><img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;<font color="white"><%=Constant.getResourceStringValue("admin.Questions.updatemsg",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%> </font> </a> --></div>
<%}%>
<Br>
<div align="center" class="div">

<table border="0" width="100%">
	<font color = red ><html:errors /> </font>

	<tr>
			<td></td>
			<td></td>
		</tr>
	   
        <tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.name",user1.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="questionName" rows="3" cols="50"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.type",user1.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select property="typeVal" onchange="onclickquestionType(document.questionForm.typeVal)">
			<option value="noVal"><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.selectoption.text",user1.getLocale())%></option>

      





			 <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("text")){%>
			<option  value="text" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.text_fields",user1.getLocale())%></option>
            <%}else{%>
						<option value="text"><%=Constant.getResourceStringValue("admin.Questions.text_fields",user1.getLocale())%></option>			
             <%}%>


            <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("radio")){%>	
			<option  value="radio" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.radio",user1.getLocale())%></option>
			<%}else{%>
            <option value="radio"><%=Constant.getResourceStringValue("admin.Questions.radio",user1.getLocale())%></option>
             <%}%>

            <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("dropdown")){%>	
			<option  value="dropdown" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.drop_down_menu",user1.getLocale())%></option>
			<%}else{%>
            <option value="dropdown"><%=Constant.getResourceStringValue("admin.Questions.drop_down_menu",user1.getLocale())%></option>
             <%}%>


			 <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("checkbox")){%>	
			<option  value="checkbox" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.checkbox",user1.getLocale())%></option>
			<%}else{%>
            <option value="checkbox"><%=Constant.getResourceStringValue("admin.Questions.checkbox",user1.getLocale())%></option>
             <%}%>

			  <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("date")){%>	
			<option  value="date" selected="selected"><%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%></option>
			<%}else{%>
            <option value="date"><%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%></option>
             <%}%>
		   


		   <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("number")){%>	
			<option  value="number" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.type.number",user1.getLocale())%></option>
			<%}else{%>
            <option value="number"><%=Constant.getResourceStringValue("admin.Questions.type.number",user1.getLocale())%></option>
             <%}%>


			</html:select>
			
			</td>
		</tr>


		<tr>
		      <td>
<% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("checkbox") || questionsform.getTypeVal().equals("dropdown") || questionsform.getTypeVal().equals("radio")){%>	
 
 <div id="answerchoicelabel" style="display:block;">
 <%=Constant.getResourceStringValue("admin.Questions.createpage.answerchoices",user1.getLocale())%><br>
 <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.typeoneanswerperline",user1.getLocale())%></span>
 
 
 
 </div>
<%}%>

<% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("date")){%>	
 
  <div id="datelabel" style="display:block;">
  <%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%><br>
  <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
  <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user1.getLocale())%></span>
  
  </div>
<%}%>

		        <div id="answerchoicelabel" style="display:none;">
				<%=Constant.getResourceStringValue("admin.Questions.createpage.answerchoices",user1.getLocale())%><br>
				<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.typeoneanswerperline",user1.getLocale())%></span>
				
				</div>
		        <div id="datelabel" style="display:none;">
				<%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%><br>
				<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
				 
				 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user1.getLocale())%></span>
				
				</div>
		      
			  
			  </td>
		      <td>

			  <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("checkbox") || questionsform.getTypeVal().equals("dropdown") || questionsform.getTypeVal().equals("radio")){%>	
                 <div id="dropdowncheckboxradio" style="display:block;">       
		  <textarea name="answerOption" id="answerOption" rows="3" cols="50"><%=questionsform.getOptionssetList()%></textarea> 
			    
			     </div>
               <%}else{%>

			     <div id="dropdowncheckboxradio" style="display:none;">
		  
			    <textarea name="answerOption" id="answerOption" rows="3" cols="50"></textarea> 
			     </div>

				 <%}%>
				
             <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("date")){%>	
                 <div id="date" style="display:block;"> 
				
	             <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
                 var cal1xx1 = new CalendarPopup("testdiv2");
                  cal1xx1.showNavigationDropdowns();
                 </SCRIPT>
	<INPUT TYPE="text" NAME="correctAnsDate" readonly="true" value="<%=questionsform.getCorrectAnsDate()%>" SIZE=25>
<A HREF="#" onClick="cal1xx1.select(document.questionForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" TITLE="cal1xx1.select(document.questionForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" TITLE="select date"></A>

<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
    </div>           

           <%}else{%>
 
		<div id="date" style="display:none;"> 
	             <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
                 var cal1xx1 = new CalendarPopup("testdiv1");
                 cal1xx1.showNavigationDropdowns();
                 </SCRIPT>
	
                <INPUT TYPE="text" NAME="correctAnsDate" readonly="true" value="<%=(questionsform.getCorrectAnsDate()==null)?"":questionsform.getCorrectAnsDate()%>" SIZE=25>
                <A HREF="#" onClick="cal1xx1.select(document.questionForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" TITLE="cal1xx1.select(document.questionForm.correctAnsDate,'anchor1xx1','<%=datepattern%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" TITLE="select date"></A>

                <DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			    </div>
				<%}%>
            </td>
	  </tr>
      <tr>
            


            <td>


          <% if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("checkbox") || questionsform.getTypeVal().equals("dropdown") || questionsform.getTypeVal().equals("radio") || questionsform.getTypeVal().equals("text") || questionsform.getTypeVal().equals("number")){%>	

				
                 <div id="correctanswerlabel" style="display:block;">
				 <%=Constant.getResourceStringValue("admin.Questions.createpage.correctanswer",user1.getLocale())%><br>
				 <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
				 
				 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user1.getLocale())%></span>
				 
				 
				 </div>
             <%}else{%>



			   <div id="correctanswerlabel" style="display:none;">
			   <%=Constant.getResourceStringValue("admin.Questions.createpage.correctanswer",user1.getLocale())%><br>
			   <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
				 
				 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user1.getLocale())%></span>
			   
			   </div>
			   <%}%>
			</td>
            <td>

         <%
         if (questionsform.getTypeVal()!=null && questionsform.getTypeVal().equals("checkbox") || questionsform.getTypeVal().equals("dropdown") || questionsform.getTypeVal().equals("radio") || questionsform.getTypeVal().equals("text") || questionsform.getTypeVal().equals("number")){%>
             <div id="correctanswer" style="display:block;"> 
			  <html:text property="correctAns" maxlength="200" size="53"/>
			  </div>
         <%}else{%>
			  <div id="correctanswer" style="display:none;"> 
			  <html:text property="correctAns" maxlength="200" size="53"/>
			  </div>

			  <%}%>
           </td>
     </tr>

	
   <!--start for edit question-->
 
   <table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	
			
       <tr>
			<td colspan="2">	
			<% if(questionsform.getQuestionId()!=0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			
			<%}else{%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata()" class="button">
			
			<%}%>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button"></td>
			<td></td>
		</tr>     
		<tr><td></td>
		<td>
   <input type="hidden" name="tempvaloption" value="<%=questionsform.getOptionssetList()%>"/>
    <input type="hidden" name="tempvalcorrectans" value="<%=questionsform.getCorrectAns()%>"/>
  
   <input type="hidden" name="tempvaltype" value="<%=questionsform.getTypeVal()%>"/>
   <textarea name="answerOption1" id="answerOption1" rows="3" cols="50" style="visibility:hidden"><%=questionsform.getOptionssetList()%></textarea> 
</td>
</tr>	
			
			
<%}%>
<%}%>
		
		</table>



</table>
</div>

</html:form>



</body>

