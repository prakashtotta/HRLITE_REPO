<%@ include file="../common/include.jsp" %>
<%@ include file="../common/greybox.jsp" %>
<%@ page import="com.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%@ page import="com.dao.*"%>
<%
String path4 = (String)request.getAttribute("filepath4");
User user14 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern14 = DateUtil.getDatePatternFormat(user14.getLocale());
%>
<style type="text/css">

.initial2{font-weight:bold;background-color:#c00;color:#fff;}

</style>
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
<bean:define id="questionsgroupform" name="questionGroupForm" type="com.form.QuestionsGroupForm" />
 


<SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>



<script language="javascript">

function change(e, color){
   var el=window.event? event.srcElement: e.target
   if (el.tagName=="INPUT"&&el.type=="button")
   el.style.backgroundColor=color
}



function backtoaddquestionpafe1(){

var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user14.getLocale())%>");
if (doyou == true){
document.questionGroupForm.action = "questiongroup.do?method=backAddQuestionpage&questiongroupId=<%=questionsgroupform.getQuestiongroupId()%>";
      document.questionGroupForm.submit();

}
}
function savedata(){
	var alertstr = "";
	var showalert=false;
	var name=document.questionGroupForm.questionName.value.trim();
	var typeVal=document.questionGroupForm.typeVal.value.trim();
	var option=document.questionGroupForm.answerOption.value;
	 /*this comented code validation correct answer if does not matched to optipn list*/
	
	
	/*  
	
	var corrtans=document.questionGroupForm.correctAns.value;
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

	    if(typeVal=="radio" || typeVal=="checkbox" || typeVal=="dropdown"){
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
	}
	*/
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.QuesName_required",user14.getLocale())%><br>";
		showalert = true;
		}
	if(typeVal == "" || typeVal == null || typeVal=="no" ){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Questtype_required",user14.getLocale())%><br>";
		showalert = true;
		}
  
	 if (showalert){
     	alert(alertstr);
        return false;
          }
	
	  document.questionGroupForm.action = "questiongroup.do?method=saveQuestionwithgroup&quntype=SLAVE&questiongroupId=<%=questionsgroupform.getQuestiongroupId()%>";
      document.questionGroupForm.submit();

 
}

function updatedata(){
		var alertstr = "";
		var showalert=false;
	    var name=document.questionGroupForm.questionName.value.trim();
	    var typeVal=document.questionGroupForm.typeVal.value.trim();
	    var option=document.questionGroupForm.answerOption.value;
		var corrtans=document.questionGroupForm.correctAns.value;
	  /*this comented code validation correct answer if does not matched to optipn list*/
	  
	  /*  var m;	
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
     	  alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.QuesName_required",user14.getLocale())%><br>";
		  showalert = true;
		}
	    if(typeVal == "" || typeVal == null || typeVal=="no"){
     	  alertstr = alertstr + "<%=Constant.getResourceStringValue("validation.Questtype_required",user14.getLocale())%><br>";
		  showalert = true;
		}
	
	    if (showalert){
     	alert(alertstr);
        return false;
          }
	
	    document.questionGroupForm.action = "questiongroup.do?method=updateQuestion&questionid=<%=questionsgroupform.getQuestionId()%>&questiongroupid=<%=questionsgroupform.getQuestiongroupId()%>";
       document.questionGroupForm.submit();
 
}

function onclickquestionType(fields){ 
	var type=fields.value;
    var tempvaltype=document.questionGroupForm.tempvaltype.value;
    var tempvalcorrectans=document.questionGroupForm.tempvalcorrectans.value;
    var tempvaloption=document.questionGroupForm.tempvaloption.value;




  if(type!=null && type=="text"){	
      document.getElementById("answerchoicelabel").style.display = 'none';
      document.getElementById("dropdowncheckboxradio").style.display = 'none';
      document.getElementById("correctanswerlabel").style.display = 'block';
      document.getElementById("datelabel").style.display = 'none';
      document.getElementById("date").style.display='none';
      document.getElementById("correctanswer").style.display = 'block';

      if(tempvaltype!="text"){
        document.questionGroupForm.correctAns.value="";
      }else if(tempvaltype=="text"){
        document.questionGroupForm.correctAns.value=tempvalcorrectans;
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
       document.questionGroupForm.correctAns.value="";
     }else if(tempvaltype=="number"){
      document.questionGroupForm.correctAns.value=tempvalcorrectans;
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
      document.questionGroupForm.answerOption.value="";
      document.questionGroupForm.correctAns.value="";
      }else if(tempvaltype=="checkbox"){
      document.questionGroupForm.answerOption.value=document.questionGroupForm.answerOption1.value;
      document.questionGroupForm.correctAns.value=tempvalcorrectans;
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
     document.questionGroupForm.answerOption.value="";
     document.questionGroupForm.correctAns.value="";
     }else if(tempvaltype=="dropdown"){
     document.questionGroupForm.answerOption.value=document.questionGroupForm.answerOption1.value;
     document.questionGroupForm.correctAns.value=tempvalcorrectans;
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
    document.questionGroupForm.answerOption.value="";
    document.questionGroupForm.correctAns.value="";
    }else if(tempvaltype=="radio"){
    document.questionGroupForm.answerOption.value=document.questionGroupForm.answerOption1.value;
    document.questionGroupForm.correctAns.value=tempvalcorrectans;
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
   document.questionGroupForm.correctAns.value="";
   }else if(tempvaltype=="date"){
   document.questionGroupForm.correctAns.value=tempvalcorrectans;
  }
 }
}

</script>

<%
String saveQuestion = (String)request.getAttribute("saveQuestion");
String updateQuestion = (String)request.getAttribute("updateQuestion");
String deleteQuestion = (String)request.getAttribute("deleteQuestion");
String editquestion1 = (String)request.getAttribute("editquestion");
%>

<html:form action="/questiongroup.do?method=createnewQuestion33" target="_parent">


<div align="center">
	


<table border="0" width="100%">
	<font color = "red" ><html:errors /> </font>
 <tr>
<% if(editquestion1 != null && editquestion1.equals("yes")){%>

      
			<td><b><%=Constant.getResourceStringValue("admin.Questions.editquescreenname",user14.getLocale())%></b></td>
			<td></td>
		
<%}else{%>

<td><b><%=Constant.getResourceStringValue("admin.Questions.CreateQuestion",user14.getLocale())%></b></td>
			<td></td>
<%}%>
	</tr>
			
	   
        <tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.name",user14.getLocale())%><font color="red">*</font></td>
			<td><html:textarea property="questionName" rows="3" cols="50"/></td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Questions.type",user14.getLocale())%><font color="red">*</font></td>
			<td>
			<html:select property="typeVal" onchange="onclickquestionType(document.questionGroupForm.typeVal)">
			<option value="no"><%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.selectoption.text",user14.getLocale())%></option>

      





			 <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("text")){%>
			<option  value="text" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.text_fields",user14.getLocale())%></option>
            <%}else{%>
						<option value="text"><%=Constant.getResourceStringValue("admin.Questions.text_fields",user14.getLocale())%></option>			
             <%}%>


            <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("radio")){%>	
			<option  value="radio" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.radio",user14.getLocale())%></option>
			<%}else{%>
            <option value="radio"><%=Constant.getResourceStringValue("admin.Questions.radio",user14.getLocale())%></option>
             <%}%>

            <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("dropdown")){%>	
			<option  value="dropdown" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.drop_down_menu",user14.getLocale())%></option>
			<%}else{%>
            <option value="dropdown"><%=Constant.getResourceStringValue("admin.Questions.drop_down_menu",user14.getLocale())%></option>
             <%}%>


			 <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("checkbox")){%>	
			<option  value="checkbox" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.checkbox",user14.getLocale())%></option>
			<%}else{%>
            <option value="checkbox"><%=Constant.getResourceStringValue("admin.Questions.checkbox",user14.getLocale())%></option>
             <%}%>

			  <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("date")){%>	
			<option  value="date" selected="selected"><%=Constant.getResourceStringValue("hr.dates.date",user14.getLocale())%></option>
			<%}else{%>
            <option value="date"><%=Constant.getResourceStringValue("hr.dates.date",user14.getLocale())%></option>
             <%}%>
		   


		   <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("number")){%>	
			<option  value="number" selected="selected"><%=Constant.getResourceStringValue("admin.Questions.type.number",user14.getLocale())%></option>
			<%}else{%>
            <option value="number"><%=Constant.getResourceStringValue("admin.Questions.type.number",user14.getLocale())%></option>
             <%}%>


			</html:select>
			
			</td>
		</tr>


		<tr>
		      <td>
<% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("checkbox") || questionsgroupform.getTypeVal().equals("dropdown") || questionsgroupform.getTypeVal().equals("radio")){%>	
 
 <div id="answerchoicelabel" style="display:block;">
 <%=Constant.getResourceStringValue("admin.Questions.createpage.answerchoices",user14.getLocale())%><br>
  <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
  
  <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.typeoneanswerperline",user14.getLocale())%></span>
  
 
 </div>
<%}else{%>
 <div id="answerchoicelabel" style="display:none;">
 <%=Constant.getResourceStringValue("admin.Questions.createpage.answerchoices",user14.getLocale())%><br>
 <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.typeoneanswerperline",user14.getLocale())%></span>
 
 
 
 </div>
<%}%>
<% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("date")){%>	
 
  <div id="datelabel" style="display:block;">
  <%=Constant.getResourceStringValue("hr.dates.date",user14.getLocale())%><br>
  <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
  <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user14.getLocale())%></span>
  
  </div>
<%}else{%>
<div id="datelabel" style="display:none;">
<%=Constant.getResourceStringValue("hr.dates.date",user14.getLocale())%><br>
<span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
  <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user14.getLocale())%></span>

</div>

<%}%>

		       
		        
		      
			  
			  </td>
		      <td>

			  <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("checkbox") || questionsgroupform.getTypeVal().equals("dropdown") || questionsgroupform.getTypeVal().equals("radio")){%>	
                 <div id="dropdowncheckboxradio" style="display:block;">       
		  <textarea name="answerOption" id="answerOption" rows="3" cols="50"><%=questionsgroupform.getAnswerOption()%></textarea> 
			    
			     </div>
               <%}else{%>

			     <div id="dropdowncheckboxradio" style="display:none;">
		  
			    <textarea name="answerOption" id="answerOption" rows="3" cols="50"></textarea> 
			     </div>

				 <%}%>
				
             <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("date")){%>	
                 <div id="date" style="display:block;"> 
				
	             <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
                 var cal1xx1 = new CalendarPopup("testdiv1");
                 cal1xx1.showNavigationDropdowns();
                 </SCRIPT>
	
                <INPUT TYPE="text" NAME="correctAnsDate" readonly="true" value="<%=(questionsgroupform.getCorrectAnsDate()==null || questionsgroupform.getCorrectAnsDate()=="null")?"":questionsgroupform.getCorrectAnsDate()%>" SIZE=25>
                <A HREF="#" onClick="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern14%>'); return false;" TITLE="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern14%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" TITLE="select date"></A>

                <DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			    </div>

           <%}else{%>


				 <div id="date" style="display:none;"> 
				
	             <SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
                 var cal1xx1 = new CalendarPopup("testdiv1");
                 cal1xx1.showNavigationDropdowns();
                 </SCRIPT>
	
                <INPUT TYPE="text" NAME="correctAnsDate" readonly="true" value="<%=(questionsgroupform.getCorrectAnsDate()==null || questionsgroupform.getCorrectAnsDate()=="null")?"":questionsgroupform.getCorrectAnsDate()%>" SIZE=25>
                <A HREF="#" onClick="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern14%>'); return false;" TITLE="cal1xx1.select(document.questionGroupForm.correctAnsDate,'anchor1xx1','<%=datepattern14%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" TITLE="select date"></A>

                <DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
			    </div>
				<%}%>
            </td>
	  </tr>
      <tr>
            


            <td>
			 <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("checkbox") || questionsgroupform.getTypeVal().equals("dropdown") || questionsgroupform.getTypeVal().equals("radio") || questionsgroupform.getTypeVal().equals("text") || questionsgroupform.getTypeVal().equals("numeric")){%>	
                 <div id="correctanswerlabel" style="display:block;">
				 <%=Constant.getResourceStringValue("admin.Questions.createpage.correctanswer",user14.getLocale())%><br>
				 <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
				 
				 <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user14.getLocale())%></span>
				 
				
				 
				 </div>
             <%}else{%>



			   <div id="correctanswerlabel" style="display:none;">
			   
			   <%=Constant.getResourceStringValue("admin.Questions.createpage.correctanswer",user14.getLocale())%><br>
			  <span class="textboxlabel" STYLE="font-size:smaller;font-style:italic";>
			  <%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestion.optionalleavetoblank",user14.getLocale())%></span>
			   </div>
			   <%}%>
			</td>
            <td>
 <% if (questionsgroupform.getTypeVal()!=null && questionsgroupform.getTypeVal().equals("checkbox") || questionsgroupform.getTypeVal().equals("dropdown") || questionsgroupform.getTypeVal().equals("radio") || questionsgroupform.getTypeVal().equals("text") || questionsgroupform.getTypeVal().equals("numeric")){%>
         	
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
	<font color = "red" ><html:errors /> </font>
	
       <tr>
			<td>	
			<%
     String updatequestion = (String)request.getAttribute("updatequestion");

	if(updatequestion != null && updatequestion.equals("yes")){%>
<input type="button" name="login" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.button.update",user14.getLocale())%>" onClick="updatedata()"  border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.button.updatequestion.alt",user14.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.createquestiongrouppage.button.updatequestion.alt",user14.getLocale())%>" class="button">
	<%}else{%>
			
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.add",user14.getLocale())%>" onClick="savedata()"  border="0" alt="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.add.alt",user14.getLocale())%>" title="<%=Constant.getResourceStringValue("admin.QuestionsGroup.addquestionpage.buttonvalue.add.alt",user14.getLocale())%>" class="button" >
			<%}%>
			

			<input type="button" name="login" border="0" value=<%=Constant.getResourceStringValue("hr.button.cancel",user14.getLocale())%> alt="<%=Constant.getResourceStringValue("hr.button.cancel",user14.getLocale())%>" title="<%=Constant.getResourceStringValue("hr.button.cancel",user14.getLocale())%>" onClick="backtoaddquestionpafe1()" class="button">
			
			
			</td>
			
		</tr>     
		

		<input type="hidden" name="questiongroupId" value="<%=questionsgroupform.getQuestiongroupId()%>"/>
   <input type="hidden" name="tempvaloption" value="<%=questionsgroupform.getOptionssetList()%>"/>
    <input type="hidden" name="tempvalcorrectans" value="<%=questionsgroupform.getCorrectAns()%>"/>
  
   <input type="hidden" name="tempvaltype" value="<%=questionsgroupform.getTypeVal()%>"/>
		 <textarea name="answerOption1" id="answerOption1" rows="2" cols="2" style="visibility:hidden"><%=questionsgroupform.getAnswerOption()%></textarea>
			

		
		</table>



</table>

</div>

</html:form>





