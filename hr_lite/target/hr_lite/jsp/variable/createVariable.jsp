<%@ include file="../common/include.jsp" %>

<%
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<html>
 <HEAD>
<style>
.today {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold}
.days {COLOR: navy; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt; FONT-WEIGHT: bold; TEXT-ALIGN: center}
.dates {COLOR: black; FONT-FAMILY: sans-serif; FONT-SIZE: 10pt;}
</style>
<style>
span1{color:#ff0000;}
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

.datefieldsnone {
	float: right;
	display :none;
	width: 25em;
	position: absolute;
    top: 194px;
	left: 180px;
	z-index: 1;
}
.datefieldsblock {
	float: right;
	display :block;
	width: 25em;
	position: absolute;
    top: 194px;
	left: 180px;
	z-index: 1;
}
.defaultclassblock {
	float: right;
	display :block;
	width: 25em;
	position: absolute;
    top: 200px;
	left: 510px;
	z-index: 1;
}
.defaultclassnone {
	float: right;
	display :none;
	width: 25em;
	position: absolute;
    top: 200px;
	left: 510px;
	z-index: 1;
}
.removedefaultblock {
	float: right;
	display :block;
	width: 25em;
	position: absolute;
    top: 372px;
	left: 280px;
	z-index: 1;
}
.removedefaultnone {
	float: right;
	display :none;
	width: 25em;
	position: absolute;
    top: 372px;
	left: 280px;
	z-index: 1;
}
</STYLE>

 <bean:define id="vform" name="variableForm" type="com.form.VariablesForm" />
 <SCRIPT LANGUAGE="JavaScript">document.write(getCalendarStyles());</SCRIPT>
<script language="JavaScript" type="text/javascript">
	var ios = 0;
	var aos = 0;
function insertnewOption(theSel, newValue){
	
	var m=0;
	var check1="true";
	var listaddfieldsval=document.variableForm.listvalue.value;
	var listval1length=document.getElementById('listdata1').options.length;
	if(listval1length>0){
    for(m=0;m<listval1length;m++){
    if(listaddfieldsval==document.variableForm.listdata1.options[m].value){
    check1="false";
	break;
	}
	}
	}
	if(check1=="true"){
	var newselectdata=document.variableForm.listvalue.value;
	if(newselectdata==null || newselectdata==""){
	alert("<%=Constant.getResourceStringValue("admin.variable.createpage.addduplicatemessage",user1.getLocale())%>");
	}else{
	var newText=document.variableForm.listvalue.value;
	if (theSel.length == 0) {
    var newOpt1 = new Option(newText, newText);
    theSel.options[0] = newOpt1;
    theSel.selectedIndex = 0;
	} else if (theSel.selectedIndex != -1) {
    var selText = new Array();
    var selValues = new Array();
    var selIsSel = new Array();
    var newCount = -1;
    var newSelected = -1;
    var i;
    for(i=0; i<theSel.length; i++)
    {
	newCount++;
    if (newCount == theSel.selectedIndex) {
    selText[newCount] = newText;
    selValues[newCount] = newText;
    selIsSel[newCount] = false;
    newCount++;
    newSelected = newCount;
	}
    selText[newCount] = theSel.options[i].text;
    selValues[newCount] = theSel.options[i].value;
    selIsSel[newCount] = theSel.options[i].selected; 
    }
    for(i=0; i<=newCount; i++)
    {
    var newOpt = new Option(selText[i], selValues[i]);
    theSel.options[i] = newOpt;
    theSel.options[i].selected = selIsSel[i];	  
    }
    }else if (theSel.length > 0){
    var selText = new Array();
    var selValues = new Array();
    var selIsSel = new Array();
    var newCount = -1;
    var newSelected = -1;
    var i;
    for(i=0; i<theSel.length; i++)
    {
    newCount++;
    if (newCount == theSel.selectedIndex) {
    selText[newCount] = newText;
    selValues[newCount] = newText;
    selIsSel[newCount] = false;
    newCount++;
    newSelected = newCount;
	}
    selText[newCount] = theSel.options[i].text;
    selValues[newCount] = theSel.options[i].value;
    selIsSel[newCount] = theSel.options[i].selected; 
    }
    for(i=0; i<=newCount; i++)
    {
    var newOpt = new Option(selText[i], selValues[i]);
    theSel.options[i] = newOpt;
    theSel.options[i].selected = selIsSel[i];  
    }
    }
	document.variableForm.listvalue.value="";
	document.variableForm.listdata1.disabled=false;
	document.variableForm.default1.disabled=false;
	document.variableForm.defaultselect.disabled=false;
	
	document.getElementById("listdatadiv").style.display = 'block';
	document.getElementById("remobebutton").style.display = 'block';
	document.getElementById("updownbutton").style.display = 'block';
	document.getElementById("defaulselectdiv").style.display = 'block';
    document.getElementById("defaultbuttondiv").style.display = 'block';
	document.variableForm.defaultValueDate.value="";
	document.variableForm.defaultValue.value="";
	}
	}else{
alert("<%=Constant.getResourceStringValue("admin.variable.createpage.addduplicatemessage",user1.getLocale())%>");
	}
	}



function removeOption(theSel){
	var deleted="false";
	var selIndex = theSel.selectedIndex;
	if(document.getElementById('defaultselect').options.length>0){
	var defaultval=document.variableForm.defaultselect.options[0].value;
    }
	if (selIndex != -1) {
	    for(i=theSel.length-1; i>=0; i--)
	    {
		    if(theSel.options[i].selected)
		    {
		
		
			   if(document.getElementById('defaultselect').options.length>0){
				    if(theSel.options[i].value!=defaultval){
				    theSel.options[i] = null;
				    deleted="true";
				    }else{
				    alert("<%=Constant.getResourceStringValue("admin.variable.createpage.cannotdelete.defaultval.message",user1.getLocale())%>");
					}
			
			   }else{
			       theSel.options[i] = null;
			
			
			   }
		
		
		
		
		
			}
	    }
	    if (theSel.length > 0) {
	
	
	
			if(deleted=="true"){
			    theSel.selectedIndex = selIndex == 0 ? 0 : selIndex - 1;
			}
	    }
    }else{
		
    alert("<%=Constant.getResourceStringValue("admin.variable.delete1.notmessage",user1.getLocale())%>");

	}
	if(document.getElementById('listdata1').options.length==0){
	document.variableForm.default1.disabled=true;
	}else{
    document.variableForm.default1.disabled=false;
	}
}


function removeDefaultColumn(theSel){
	if(document.variableForm.defaultselect.options[0].selected){
	var selIndex = theSel.selectedIndex;
	if (selIndex != -1) {
    for(i=theSel.length-1; i>=0; i--)
    {
    if(theSel.options[i].selected)
    {
    theSel.options[i] = null;
    }
    }
    if (theSel.length > 0) {
    theSel.selectedIndex = selIndex == 0 ? 0 : selIndex - 1;
    }
	}
	}else{
    alert("<%=Constant.getResourceStringValue("admin.variable.delete1.notmessage",user1.getLocale())%>");
	}
}


function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){		 
	  parent.parent.GB_hide();
	  } 
}



function savedata(){ 
	var j;
	var selectedtype="";
	var selectedtype1=document.variableForm.variableType.value;
	
	var iftypeselec="no";
	var alertstr = "";
	var showalert=false;	
	var name = document.variableForm.variableName.value.trim();
	//var code=document.variableForm.variableCode.value.trim();
	var listdata=document.getElementById('listdata1').options.length;
	var selection = document.variableForm.variableType;
	var defaultvalnumeric=document.variableForm.neumeric.value;
    for(j=0;j<selection.length;j++){
    if (selection[j].checked == true){
    iftypeselec="yes";
	selectedtype=selection[j].value;
    }
	}


		if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.mandatory.name",user1.getLocale())%><br>";
		showalert = true;
		}/*else{
			if(name.indexOf(' ') > 0){
				alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.without_space_msg",user1.getLocale())%><br>";
				showalert = true;
			}
		
		}*/
		
		if(iftypeselec == "no"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.mandatory.type",user1.getLocale())%><br>";
		showalert = true;
		}
		
		/*
       
		if(selectedtype1 == "list"){
        if(listdata==0){
        alertstr = alertstr + "no data in list<br>";
		showalert = true;
		}
		}

		*/
		
		if(selectedtype!=null && selectedtype =="numeric"){
         if(defaultvalnumeric!=null || defaultvalnumeric!=""){
			if(isNaN(defaultvalnumeric)){
         alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.numeric.shouldbe_numeric",user1.getLocale())%><br>";
		showalert = true;

			}

		}
		}
		if (showalert){
     	alert(alertstr);
        return false;
        }
	var selectedDefaultdata="";
	if(document.getElementById('defaultselect').options.length>0){
	selectedDefaultdata = document.getElementById('defaultselect').options[0].value;
	}
	var txtSelectedValuesObj = document.getElementById('txtSelectedValues');
	var selectedArray = new Array();
	var selObj = document.getElementById('listdata1');
	var i;
	var count = 0;
	for (i=0; i<selObj.options.length; i++) {  
    selectedArray[count] = selObj.options[i].value;
    count++;  
	}	
	document.variableForm.action = "variable.do?method=saveVariable&selectedlist="+selectedArray+"&defaultselectedlist="+selectedDefaultdata;
	document.variableForm.submit();
	
}
function hasWhiteSpace(s) {
	  return s.indexOf(' ') >= 0;
	}

function updatedata(){
	var j;
	var selectedtype="";
	var iftypeselec="no";
	var alertstr = "";
	var showalert=false;	
	var name = document.variableForm.variableName.value.trim();
//	var code=document.variableForm.variableCode.value.trim();
	var listdata=document.getElementById('listdata1').options.length;
	var selection = document.variableForm.variableType;
	var defaultvalnumeric=document.variableForm.neumeric.value;
    for(j=0;j<selection.length;j++){
    if (selection[j].checked == true){
    iftypeselec="yes";
	selectedtype=selection[j].value;
    }
	}
	if(name == "" || name == null){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.mandatory.name",user1.getLocale())%><br>";
		showalert = true;
		}/*else{
			if(name.indexOf(' ') > 0){
				alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.without_space_msg",user1.getLocale())%><br>";
				showalert = true;
			}
		
		}*/
		
		if(iftypeselec == "no"){
     	alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.mandatory.type",user1.getLocale())%><br>";
		showalert = true;
		}    
		/*if(selectedtype != "" || selectedtype == "list"){
        if(listdata==0){
        alertstr = alertstr + "no data in list<br>";
		showalert = true;
		}
		}
		*/

		if(selectedtype!=null && selectedtype =="numeric"){
         if(defaultvalnumeric!=null || defaultvalnumeric!=""){
			if(isNaN(defaultvalnumeric)){
        alertstr = alertstr + "<%=Constant.getResourceStringValue("admin.variable.numeric.shouldbe_numeric",user1.getLocale())%><br>";
		showalert = true;

			}

		}
		}
		if (showalert){
     	alert(alertstr);
        return false;
        }
	var selectedDefaultdata="";
	if(document.getElementById('defaultselect').options.length>0){
	selectedDefaultdata = document.getElementById('defaultselect').options[0].value;
	}
	var txtSelectedValuesObj = document.getElementById('txtSelectedValues');
	var selectedArray = new Array();
	var selObj = document.getElementById('listdata1');
	var i;
	var count = 0;
	for (i=0; i<selObj.options.length; i++) {  
    selectedArray[count] = selObj.options[i].value;
	count++;  
	}	




	document.variableForm.action = "variable.do?method=updatevariable&selectedlist="+selectedArray+"&defaultselectedlist="+selectedDefaultdata+"&variableid="+'<bean:write name="variableForm" property="variableId"/>';
	document.variableForm.submit();
 
}


function deletedata(){
	 var deletedata = confirm("<%=Constant.getResourceStringValue("hr.methods.deletemsg",user1.getLocale())%>");
	  if (deletedata == true){
			document.variableForm.action = "variable.do?method=deleteVariable&variableid="+'<bean:write name="variableForm" property="variableId"/>';
		    document.variableForm.submit();
	  }
	
}

function closewindow(){
	parent.parent.GB_hide();

}


function listType(){
	
	
	if(document.getElementById('listdata23').options.length>0){
	var lengths=document.getElementById('listdata23').options.length-1;
    var selectedArray = new Array();
	var i;
	var count = 0;
	var k;
	var selObj=document.getElementById('listdata23');
	for (i=0; i<selObj.options.length; i++) {
    selectedArray[count] = selObj.options[i].value;
	count++;
	}
	
	if(selectedArray.length>0){	  
	if(document.getElementById('listdata1').options.length==0){
	for(k=0;k<selectedArray.length;k++){
    var newOpt = new Option(selectedArray[k], selectedArray[k]);
	if(k==lengths){
     newOpt.selected=true;
	}
    document.getElementById('listdata1').options[k] = newOpt;
	}
	}
	}
	}

	if(document.getElementById('ismandatory').options.length>0){
    var selectedArray1 = new Array();
	var j;
	var count1 = 0;
	var m;
	var selObj1=document.getElementById('ismandatory');
	for (j=0; j<selObj1.options.length; j++) {
    selectedArray1[count] = selObj1.options[j].value;
	count1++;
	}
	
	if(selectedArray1.length>0){	  
	if(document.getElementById('defaultselect').options.length==0){
	for(m=0;m<selectedArray1.length;m++){
    var newOpt1 = new Option(selectedArray1[m], selectedArray[m]);
    newOpt1.selected=true;
    document.getElementById('defaultselect').options[0] = newOpt1;
	}
	}
	}
	}

	document.variableForm.defaultValuearea.value="";
    document.variableForm.neumeric.value="";
	document.variableForm.defaultValue.value="";
	document.variableForm.defaultValueDate.value="";
    document.variableForm.listvalue.value="";


	document.getElementById("listdatadiv").style.display = 'block';
	document.getElementById("defaultbuttondiv").style.display = 'block';
	document.getElementById("defaulselectdiv").style.display = 'block';
	


	document.getElementById("defaultvalueString").style.display = 'none';
	document.getElementById("defaultvalueDatevalue").style.display = 'none';
	document.getElementById("defaultvaluelistdata").style.display = 'block';
	document.getElementById("textarea1").style.display = 'none';
	document.getElementById("defaulttex1").style.display = 'none';	
	document.getElementById("numaric1").style.display = 'none';
	document.getElementById("mydiv").style.display = 'none';
	document.getElementById("addlist").style.display = 'block';
	document.getElementById("remobebutton").style.display = 'none';
	document.getElementById("updownbutton").style.display = 'none';
    document.getElementById("remobebuttondefault").style.display = 'none';
    if(document.variableForm.listdata1.options.length>0){
	 document.variableForm.default1.disabled=false;
     document.variableForm.listdata1.disabled=false;
	 document.getElementById("remobebutton").style.display = 'block';
	 document.getElementById("updownbutton").style.display = 'block';
	 }else{
	 document.variableForm.default1.disabled=true;
	 document.getElementById("remobebutton").style.display = 'none';
	 document.getElementById("updownbutton").style.display = 'none';
	 }

	 if(document.variableForm.defaultselect.options.length>0){
     document.variableForm.defaultselect.disabled=false;
     document.getElementById("remobebuttondefault").style.display = 'block';

	 }else{
	document.variableForm.defaultselect.disabled=true;
    document.getElementById("remobebuttondefault").style.display = 'none';

	 }
	
}   

function textType(type){  
	if(type=='txtarea'){
	document.variableForm.defaultValuearea.value=document.variableForm.storetextarea.value;
	document.variableForm.neumeric.value="";
	document.variableForm.defaultValue.value="";
	document.variableForm.defaultValueDate.value="";
	document.getElementById('listdata1').options.length=0;
    document.variableForm.listvalue.value="";
	document.getElementById('defaultselect').options.length=0;
	document.variableForm.defaultselect.disabled=true;
	document.variableForm.listdata1.disabled=true;
	document.variableForm.default1.disabled=true;
	document.getElementById("listdatadiv").style.display = 'none';
	document.getElementById("defaultbuttondiv").style.display = 'none';
	document.getElementById("defaulselectdiv").style.display = 'none';
	document.getElementById("defaultvalueString").style.display = 'block';
	document.getElementById("defaultvalueDatevalue").style.display = 'none';
	document.getElementById("defaultvaluelistdata").style.display = 'none';
	document.getElementById("textarea1").style.display = 'block';
	document.getElementById("defaulttex1").style.display = 'none';	
	document.getElementById("numaric1").style.display = 'none';
	document.getElementById("defaultvalueDatevalue").style.display = 'none';
	document.getElementById("defaultvaluelistdata").style.display = 'none';
	document.getElementById("mydiv").style.display = 'none';
	document.getElementById("addlist").style.display = 'none';
	document.getElementById("remobebutton").style.display = 'none';
	document.getElementById("updownbutton").style.display = 'none';
    document.getElementById("remobebuttondefault").style.display = 'none';
	
	

	}else if(type=='numeric'){
    document.variableForm.neumeric.value=document.variableForm.stornumeric.value;
	document.variableForm.defaultValue.value="";
	document.variableForm.defaultValueDate.value="";
	document.variableForm.defaultValuearea.value="";
	document.getElementById('listdata1').options.length=0;
    document.variableForm.listvalue.value="";
	document.getElementById('defaultselect').options.length=0;
	document.variableForm.defaultselect.disabled=true;
	document.variableForm.listdata1.disabled=true;
	document.variableForm.default1.disabled=true;
	document.getElementById("listdatadiv").style.display = 'none';
	document.getElementById("defaultbuttondiv").style.display = 'none';
	document.getElementById("defaulselectdiv").style.display = 'none';
	document.getElementById("defaultvalueString").style.display = 'block';
	document.getElementById("defaultvalueDatevalue").style.display = 'none';
	document.getElementById("defaultvaluelistdata").style.display = 'none';
	document.getElementById("textarea1").style.display = 'none';
	document.getElementById("defaulttex1").style.display = 'none';	
	document.getElementById("numaric1").style.display = 'block';
	document.getElementById("mydiv").style.display = 'none';
	document.getElementById("addlist").style.display = 'none';
	document.getElementById("remobebutton").style.display = 'none';
	document.getElementById("updownbutton").style.display = 'none';
    document.getElementById("remobebuttondefault").style.display = 'none';
	
	}else{
	document.variableForm.defaultValue.value=document.variableForm.storDefaultValue.value;
	document.variableForm.defaultValueDate.value="";
	document.variableForm.defaultValuearea.value="";
	document.variableForm.neumeric.value="";
	document.getElementById('listdata1').options.length=0;
    document.variableForm.listvalue.value="";
	document.getElementById('defaultselect').options.length=0;
	document.variableForm.defaultselect.disabled=true;
	document.variableForm.listdata1.disabled=true;
	document.variableForm.default1.disabled=true;

	document.getElementById("listdatadiv").style.display = 'none';
	document.getElementById("defaultbuttondiv").style.display = 'none';
	document.getElementById("defaulselectdiv").style.display = 'none';


	document.getElementById("defaultvalueString").style.display = 'block';
	document.getElementById("defaultvalueDatevalue").style.display = 'none';
	document.getElementById("defaultvaluelistdata").style.display = 'none';
	document.getElementById("textarea1").style.display = 'none';
	document.getElementById("defaulttex1").style.display = 'block';	
	document.getElementById("numaric1").style.display = 'none';
	document.getElementById("mydiv").style.display = 'none';
	document.getElementById("addlist").style.display = 'none';
	document.getElementById("remobebutton").style.display = 'none';
	document.getElementById("updownbutton").style.display = 'none';
    document.getElementById("remobebuttondefault").style.display = 'none';
	}
	
	
	
}


function defaultfieldsDate(){
   
   document.variableForm.defaultValueDate.value=document.variableForm.storedate.value;
	document.variableForm.defaultValue.value="";
	document.variableForm.defaultValuearea.value="";
	document.variableForm.neumeric.value="";
	document.getElementById('listdata1').options.length=0;
    document.variableForm.listvalue.value="";
	document.getElementById('defaultselect').options.length=0;
	document.variableForm.defaultselect.disabled=true;
	document.variableForm.listdata1.disabled=true;
	document.variableForm.default1.disabled=true;
	document.getElementById("listdatadiv").style.display = 'none';
	document.getElementById("defaultbuttondiv").style.display = 'none';
	document.getElementById("defaulselectdiv").style.display = 'none';
	document.getElementById("defaultvalueDatevalue").style.display = 'block';
	document.getElementById("defaultvalueString").style.display = 'none';
	document.getElementById("defaultvaluelistdata").style.display = 'none';
	document.getElementById("textarea1").style.display = 'none';
	document.getElementById("defaulttex1").style.display = 'none';	
	document.getElementById("numaric1").style.display = 'none';
	document.getElementById("mydiv").style.display = 'block';
	document.getElementById("addlist").style.display = 'none';
	document.getElementById("remobebutton").style.display = 'none';
	document.getElementById("updownbutton").style.display = 'none';
    document.getElementById("remobebuttondefault").style.display = 'none';

}


function setdefault(){
	var count=0;
	var m;
    var selecteddef=document.getElementById('listdata1').options.length;
	for(m=0;m<selecteddef;m++){
	if(document.getElementById('listdata1').options[m].selected){
    count++;
	}
	}
	if(count>1){
	alert("<%=Constant.getResourceStringValue("admin.variable.select.message",user1.getLocale())%>");
	}else{
    document.variableForm.decrementlistdata.value=document.variableForm.listdata1.value;
	var selectedArray = new Array();
	var selObj = document.getElementById('listdata1');
	var i;
	var count = 0;
	var k;
	for (i=0; i<selObj.options.length; i++) {
    if (selObj.options[i].selected) {
    selectedArray[count] = selObj.options[i].value;
	count++;
    }	 
	}
	if(selectedArray.length>0){	  
	if(document.getElementById('defaultselect').options.length==0){
	for(k=0;k<selectedArray.length;k++){
    var newOpt = new Option(selectedArray[k], selectedArray[k]);
    newOpt.selected=true;
    document.getElementById('defaultselect').options[0] = newOpt;
	}
	}else if(document.getElementById('defaultselect').options.length>0){
	var lengthval=document.getElementById('defaultselect').options.length;
    for(k=0;k<selectedArray.length;k++){
    var newOpt = new Option(selectedArray[k], selectedArray[k]);
	newOpt.selected=true;
    document.getElementById('defaultselect').options[0] = newOpt;
    lengthval++;
	}
	}
	}
	document.variableForm.defaultselect.disabled=false;
	document.getElementById("remobebuttondefault").style.display = 'block';

	}
    
}


function adddata(){	
	document.variableForm.decrementlistdata.value=document.variableForm.listdata1.value;
	var listdataselected= document.variableForm.listdata1.value;
	var newselectdata=document.variableForm.listvalue.value;
	if(newselectdata==null || newselectdata==""){
	alert("please select list data then click add button");
	}else{
	if(listdataselected.length<1){		
	document.variableForm.listdata1.value=document.variableForm.listvalue.value;
	document.variableForm.listvalue.value="";
	}else{
	document.variableForm.listdata1.value=document.variableForm.listdata1.value+'\n'+document.variableForm.listvalue.value;
	document.variableForm.listvalue.value="";
	}
	document.getElementById("remobebutton").style.display = 'block';
	document.getElementById("updownbutton").style.display = 'block';
	document.variableForm.defaultValueDate.value="";
	document.variableForm.defaultValue.value="";
	}

}


function removedata(){	
	if (document.variableForm.placement[1].checked) {
    document.variableForm.listdata1.value="";
	}else{	
	document.variableForm.listdata1.value=document.variableForm.decrementlistdata.value;
	}
}

function listbox_move(listID, direction) {

	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;

	if(-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}

	var increment = -1;
	if(direction == 'up')
		increment = -1;
	else
		increment = 1;

	if((selIndex + increment) < 0 ||
		(selIndex + increment) > (listbox.options.length-1)) {
		return;
	}

	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text

	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;

	listbox.selectedIndex = selIndex + increment;
}


</script>

<html:form action="/variable.do?method=createVariable">


<div align="center" class="div">


<%

if (vform.getReadPreview() != null && vform.getReadPreview().equals("2")){


%>
<br><legend><%=Constant.getResourceStringValue("admin.variable.selector.viewdetails",user1.getLocale())%></legend>
<table border="0" width="100%">
         <tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_name",user1.getLocale())%><font color="red">*</font></td>
			<td ><%=(vform.getVariableName()==null)?"":vform.getVariableName()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_description",user1.getLocale())%></td>
			<td><%=(vform.getVariableDesc()==null)?"":vform.getVariableDesc()%></td>
			
		</tr>	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_type",user1.getLocale())%></td>
			<td><%=(vform.getVariableType()==null)?"":vform.getVariableType()%></td>
</tr>

<% if (vform.getVariableType()!=null && vform.getVariableType().equals("list")){%>
<tr>
<td><%=Constant.getResourceStringValue("admin.variable.selector.viewdetails.listvalue",user1.getLocale())%></td>
<td> <select name="listdata1" id="listdata1" size="8"  multiple="multiple" disabled="true">
	   <% String[] dataval=null;
          dataval=vform.getSelectedData();
		  int j=0;
	   for(int i=0;i<dataval.length;i++){
			 
	    %>
	   <option value="<%=dataval[i]%>"><%=dataval[i]%></option>
	   <%}%>
       </select></td>

	   
</tr>
<tr>
<td><%=Constant.getResourceStringValue("admin.variable.selector.viewdetails.defaultlistvalue",user1.getLocale())%></td>
<td><%=(vform.getIsMandatory()==null)?"":vform.getIsMandatory()%></td>

</tr>

<%}else if(vform.getVariableType()!=null && vform.getVariableType().equals("date")){%>

<tr>
<td><%=Constant.getResourceStringValue("admin.variable.default_date",user1.getLocale())%></td>
<td><%=(vform.getDefaultValueDate()==null)?"":vform.getDefaultValueDate()%></td>
</tr>

<%}else if(vform.getVariableType()!=null && vform.getVariableType().equals("textarea")){%>

<tr>
<td><%=Constant.getResourceStringValue("admin.variable.default_value",user1.getLocale())%></td>
<td><%=(vform.getDefaultValuearea()==null)?"":vform.getDefaultValuearea()%></td>
</tr>
<%}else if(vform.getVariableType()!=null && vform.getVariableType().equals("numeric")){%>
<tr>
<td><%=Constant.getResourceStringValue("admin.variable.default_value",user1.getLocale())%></td>
<td><%=(vform.getNeumeric()==null)?"":vform.getNeumeric()%></td>
</tr>


<%}else{%>
<tr>
<td><%=Constant.getResourceStringValue("admin.variable.default_value",user1.getLocale())%></td>
<td><%=(vform.getDefaultValue()==null)?"":vform.getDefaultValue()%></td>
</tr>

<%}%>
</table>
<%}else{%>



<%
String saveVariable = (String)request.getAttribute("saveVariable");
String updateVariable = (String)request.getAttribute("updateVariable");
String deleteVariable = (String)request.getAttribute("deleteVariable");
String checkVariableCode = (String)request.getAttribute("checkVariableCode");

%>

<% if(saveVariable != null && saveVariable.equals("yes")){%>
<div class="msg" align="left">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.variable.message.saved",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>
		
<%}else if(updateVariable != null && updateVariable.equals("yes")){%>
<div class="msg" align="left">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.variable.message.updated",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>

<%}else if(checkVariableCode != null && checkVariableCode.equals("yes")){%>
<div class="msg" align="left">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.variable.message.alreadyexist.code",user1.getLocale())%></font><!--  <a href="#" onClick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></font></a> --></div>



<%}else{%>

<%}%>

<% if(deleteVariable != null && deleteVariable.equals("yes")){%>
<div class="msg"  align="left">
	<img src='jsp/images/whitetick.png' border='0' width='20' height='20'/>&nbsp;&nbsp;&nbsp;
	<font color="white"><%=Constant.getResourceStringValue("admin.variable.message.deleted",user1.getLocale())%></font><!--  <a href="#" onclick="closewindow()"><font color="white"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a>--></div>
	<br><br>
<%}else{%>


<table border="0" width="100%">
	<font color = red ><html:errors /> </font>




  <tr>
			<td width="20%"><%=Constant.getResourceStringValue("admin.variable.variable_name",user1.getLocale())%><font color="red">*</font></td>
			<td >
			<html:text property="variableName" maxlength="200" size="42"/>
			</td>
			


		</tr>

		<tr>
			<!-- <td><%=Constant.getResourceStringValue("admin.variable.variable_code",user1.getLocale())%><font color="red">*</font></td> -->
			<td><html:hidden property="variableCode"/></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_description",user1.getLocale())%></td>
			<td><html:textarea property="variableDesc" cols="44" rows="5"/></td>
			
		</tr>	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.variable.variable_type",user1.getLocale())%><font color="red">*</font></td>
			<td>
			    <% if(vform.getVariableType()!=null && vform.getVariableType().equals("text")){%>
			    <input type="radio" name="variableType" id="variableType" value="text"  onclick="textType('text')" checked/><%=Constant.getResourceStringValue("admin.Questions.text",user1.getLocale())%>
				<% }else{%>
                <input type="radio" name="variableType" id="variableType" value="text"  onclick="textType('text')" /><%=Constant.getResourceStringValue("admin.Questions.text",user1.getLocale())%>
				<%}%>

				<% if(vform.getVariableType()!=null && vform.getVariableType().equals("numeric")){%>
			    <input type="radio" name="variableType" id="variableType" value="numeric"  onclick="textType('numeric')" checked/><%=Constant.getResourceStringValue("admin.variable.variable_type.numaric",user1.getLocale())%> 
				<%}else{%>
                <input type="radio" name="variableType" id="variableType" value="numeric"  onclick="textType('numeric')"/><%=Constant.getResourceStringValue("admin.variable.variable_type.numaric",user1.getLocale())%>
				<%}%>
              
			    <% if(vform.getVariableType()!=null && vform.getVariableType().equals("textarea")){%>
				<input type="radio" name="variableType" id="variableType" value="textarea"  onclick="textType('txtarea')" checked/><%=Constant.getResourceStringValue("admin.variable.variable_type.textarea",user1.getLocale())%> 
				<%}else{%>
                <input type="radio" name="variableType" id="variableType" value="textarea"  onclick="textType('txtarea')"/><%=Constant.getResourceStringValue("admin.variable.variable_type.textarea",user1.getLocale())%>
				<%}%>

                <% if(vform.getVariableType()!=null && vform.getVariableType().equals("date")){%>
				<input type="radio" name="variableType" id="variableType" value="date" onclick="defaultfieldsDate()" checked/><%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%> 
				<%}else{%>
                <input type="radio" name="variableType" id="variableType" value="date" onclick="defaultfieldsDate()"/><%=Constant.getResourceStringValue("hr.dates.date",user1.getLocale())%>
				<%}%>
               
			    <% if(vform.getVariableType()!=null && vform.getVariableType().equals("list")){%>
                <input type="radio" name="variableType" id="variableType" value="list" onclick="listType()" checked/><%=Constant.getResourceStringValue("admin.variable.variable_type.list",user1.getLocale())%> 
				<%}else{%>
                 <input type="radio" name="variableType" id="variableType" value="list" onclick="listType()"/><%=Constant.getResourceStringValue("admin.variable.variable_type.list",user1.getLocale())%>
				<%}%>
                

			</td>
		</tr>
		

		<tr>
			<td> 
			<div id="defaultvalueString" style="display:block;"> 
			<%=Constant.getResourceStringValue("admin.variable.default_value",user1.getLocale())%>
			</div>

            <div id="defaultvalueDatevalue" style="display:none;">
			<%=Constant.getResourceStringValue("admin.variable.default_date",user1.getLocale())%>
			</div>

			<div id="defaultvaluelistdata" style="display:none;"> 
			<%=Constant.getResourceStringValue("admin.variable.add_List_Value",user1.getLocale())%>
			</div>

			</td>
			<td>
			<% if((vform.getVariableType()!=null && vform.getVariableType().equals("date")) || (vform.getVariableType()!=null && vform.getVariableType().equals("list"))){%>
           <div id="defaulttex1" style="display:none;"> 
		   <html:text property="defaultValue" size="42"/>
		   </div>

		   <div id="textarea1" style="display:none;"> 
		   <html:textarea property="defaultValuearea" rows="4" cols="45"/>
		   </div>

		   <div id="numaric1" style="display:none;"> 
		   <html:text property="neumeric" size="42"/>
		   </div>

         


















		   <%}else if(vform.getVariableType()!=null && vform.getVariableType().equals("text")){%>
            <div id="defaulttex1" style="display:block;"> 
		   <html:text property="defaultValue" size="42"/>
		   </div>

		   <div id="textarea1" style="display:none;"> 
		   <html:textarea property="defaultValuearea" rows="4" cols="45"/>
		   </div>

		    <div id="numaric1" style="display:none;"> 
		   <html:text property="neumeric" size="42"/>
		   </div>

		   <%}else if(vform.getVariableType()!=null && vform.getVariableType().equals("textarea")){%>
            <div id="textarea1" style="display:block;"> 
		   <html:textarea property="defaultValuearea" rows="4" cols="45"/>
		   </div>

            <div id="defaulttex1" style="display:none;"> 
		   <html:text property="defaultValue" size="42"/>
		   </div>

		    <div id="numaric1" style="display:none;"> 
		   <html:text property="neumeric" size="42"/>
		   </div>

           <%}else if(vform.getVariableType()!=null && vform.getVariableType().equals("numeric")){%>

		    <div id="numaric1" style="display:block;"> 
		   <html:text property="neumeric" size="42"/>
		   </div>

		   <div id="defaulttex1" style="display:none;"> 
		   <html:text property="defaultValue" size="42"/>
		   </div>

		   <div id="textarea1" style="display:none;"> 
		   <html:textarea property="defaultValuearea" rows="4" cols="45"/>
		   </div>


		   <%}else{%>

            <div id="defaulttex1" style="display:block;"> 
		   <html:text property="defaultValue" size="42"/>
		   </div>
		 
		   <div id="textarea1" style="display:none;"> 
		   <html:textarea property="defaultValuearea" rows="4" cols="45"/>
		   </div>

		   <div id="numaric1" style="display:none;"> 
		   <html:text property="neumeric" size="42"/>
		   </div>
		   <%}%>

	<SCRIPT LANGUAGE="JavaScript" ID="jscal1xx1">
var cal1xx1 = new CalendarPopup("testdiv1");
cal1xx1.showNavigationDropdowns();

</SCRIPT>
	<% if(vform.getVariableType()!=null && vform.getVariableType().equals("date") || vform.getDefaultValueDate()!=null && vform.getDefaultValueDate().length()>0) {%>
	
<div id="mydiv" class="datefieldsblock">


<INPUT TYPE="text" NAME="defaultValueDate" readonly="true" value="<%=vform.getDefaultValueDate()%>" size="37">
<A HREF="#" onClick="cal1xx1.select(document.variableForm.defaultValueDate,'anchor1xx1','<%=datepattern%>'); return false;" TITLE="cal1xx1.select(document.variableForm.defaultValueDate1,'anchor1xx1','<%=datepattern%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
	</div>		

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:block; top: 179px;left: 508px;z-index: 1;"></DIV>	

<%}else{%>
<div id="mydiv" class="datefieldsnone">


<INPUT TYPE="text" NAME="defaultValueDate" readonly="true" value="<%=vform.getDefaultValueDate()%>" size="37">
<A HREF="#" onClick="cal1xx1.select(document.variableForm.defaultValueDate,'anchor1xx1','<%=datepattern%>'); return false;" TITLE="cal1xx1.select(document.variableForm.defaultValueDate,'anchor1xx1','<%=datepattern%>'); return false;" NAME="anchor1xx1" ID="anchor1xx1"><img src="jsp/images/calbtn.gif" width="18" height="18" alt="Calendar" ></A>
	</div>		

<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:block; top: 179px;left: 508px;z-index: 1;"></DIV>	

<%}%>

			
			
		
		
		
		<% if(vform.getVariableType()!=null && vform.getVariableType().equals("list")){%>
<div id="addlist" style="display:block;">
<input type="text" name="listvalue" value="" size="36"/><input type="button" name="addlistvalue" value="add"  onclick="ios++; insertnewOption(this.form.listdata1,+ios);"/>

</div>

<%}else{%>
<div id="addlist" style="display:none;">
<input type="text" name="listvalue" value="" size="36"/>
<input type="button" value="add" onclick="ios++; insertnewOption(this.form.listdata1,+ios);" class="button" />


</div>

<%}%>
		</td>
		</tr>
        <tr></tr>
		</table>
		<table border="0" width="100%">
		<tr></tr>
		<tr>
		<td></td>
		<td width="170"></td>
		<td valign="top" width="40" >  
		
		<% if(vform.getSelectedData()!=null && vform.getSelectedData().length>0){%>
	<div id="listdatadiv" style="display:block;">
       <select name="listdata1" id="listdata1" size="8"  multiple="multiple">
	   <% String[] dataval=null;
          dataval=vform.getSelectedData();
		  int j=0;
	   for(int i=0;i<dataval.length;i++){
			 String selectedtext = "";
				 j++;
				 if(j==dataval.length){
				 selectedtext = "selected='selected'";
				 }   
	    %>
	   <option value="<%=dataval[i]%>" <%=selectedtext%>><%=dataval[i]%></option>
	   <%}%>
       </select>
	   </div>

		<%}else{%>
   <div id="listdatadiv" style="display:none;">
	   <select name="listdata1" id="listdata1" size="8"  multiple="multiple" disabled="true"> 
       
      </select>
  </div>

	  <%}%>
	
		</td>
		<td valign="top">
		
		
		<% if(vform.getSelectedData()!=null && vform.getSelectedData().length>0){%>
			<div id="updownbutton" style="display:bolck;">
			<a class="button" href="#" onclick="listbox_move('listdata1', 'up')"><IMG SRC="jsp/images/up.gif" ALT="up" WIDTH=20 height=20/></a>
			<br><br>
			<a class="button" href="#" onclick="listbox_move('listdata1', 'down')"><IMG SRC="jsp/images/down.gif" ALT="down" WIDTH=20 height=20/></a>
			</div>
			<br>

<div id="remobebutton" style="display:bolck;"> 
<a href="#" onclick="removeOption(variableForm.listdata1);"><img src="jsp/images/delete.gif" border="0" alt="delete list data" title="delete list data" height="20"  width="19"/></a>
   
	</div>
		<%}else{%>
		
			<div id="updownbutton" style="display:none;">
			<a class="button" href="#" onclick="listbox_move('listdata1', 'up')"><IMG SRC="jsp/images/up.gif" ALT="up" WIDTH=20 height=20/></a>
			<a class="button" href="#" onclick="listbox_move('listdata1', 'down')"><IMG SRC="jsp/images/down.gif" ALT="down" WIDTH=20 height=20/></a>
			</div>
<br>
		<div id="remobebutton" style="display:none;"> 
<a href="#" onclick="removeOption(variableForm.listdata1);"><img src="jsp/images/delete.gif" border="0" alt="delete list data" title="delete list data" height="20"  width="19"/></a>

	</div>
	<%}%>

		 <% if(vform.getSelectedData()!=null && vform.getSelectedData().length>0){%>
		 <div id="defaultbuttondiv" style="display:block;">
		<input type="button" name="default1" value="default>>>" onclick="setdefault()" class="button"/>
		</div>

		<%}else{%>
		<div id="defaultbuttondiv" style="display:none;">
        <input type="button" name="default1" value="default>>>" onclick="setdefault()" disabled="true" class="button"/>
       </div>

		<%}%>
		</td>
		<td valign="top">
		<% if(vform.getSelectedData()!=null && vform.getSelectedData().length>0){%>
		<div id="defaulselectdiv" style="display:block;">
		<select name="defaultselect" id="defaultselect" size="8" multiple="multiple">		
		<% String defaulecolumn=null;
          defaulecolumn=vform.getIsMandatory();
	   %>
       <option value="<%=(defaulecolumn==null)?"":defaulecolumn%>" selected><%=(defaulecolumn==null)?"":defaulecolumn%></option>
	   
	  </select>
	  </div>

	  <%}else{%>
	  <div id="defaulselectdiv" style="display:none;">
	 <select name="defaultselect" id="defaultselect" size="8"  multiple="multiple" disabled="true"> 
     </select>
	 </div>

	 <%}%>
	 </td>
	
	<td valign="top" width="400">

	<% if(vform.getIsMandatory()!=null && vform.getIsMandatory()!=""){%>
	<div id="remobebuttondefault"  style="display:block;"> 
	<a href="#" onclick="removeDefaultColumn(variableForm.defaultselect);"><img src="jsp/images/delete.gif" border="0" alt="delete default data" title="delete default data" height="20"  width="19"/></a>    
	</div>

	<%}else{%>
	<div id="remobebuttondefault"  style="display:none;"> 
	 <a href="#" onclick="removeDefaultColumn(variableForm.defaultselect);"><img src="jsp/images/delete.gif" border="0" alt="delete default data" title="delete default data" height="20"  width="19"/></a>
	</div>

	<%}%>
</td>


		</tr>
		<tr><td>
<%if (vform.getIsMandatory()!=null && vform.getIsMandatory()!=""){%>
		
     <select name="ismandatory" id="ismandatory" size="2"  multiple="multiple" style="visibility:hidden">
    <% 
		 String ismandatory=null;
	     ismandatory=vform.getIsMandatory();
	%>
	
	<option value="<%=ismandatory%>"><%=ismandatory%></option>
	
	  </select>
		<%}else{%>

      <select name="ismandatory" id="ismandatory" size="2"  multiple="multiple" style="visibility:hidden"> 
	  </select>
		<%}%>
		</td>
		<td>
		<%if (vform.getSelectedData()!=null && vform.getSelectedData().length>0){%>
		
     <select name="listdata23" id="listdata23" size="2"  multiple="multiple" style="visibility:hidden">
    <% 
		 String[] stArray=null;
	     stArray=vform.getSelectedData();
	%>
	<% for(int i=0;i<stArray.length;i++){%>
	<option value="<%=stArray[i]%>"><%=stArray[i]%></option>
	<%}%>
	  </select>
		<%}else{%>

      <select name="listdata23" id="listdata23" size="2"  multiple="multiple" style="visibility:hidden"> 
	  </select>
		<%}%>
	</td>
	</tr>
		
		
</table>
<div align="left">
        <input type="hidden" name="stornumeric" value="<%=(vform.getNeumeric()==null)?"":vform.getNeumeric()%>">
        <input type="hidden" name="storetextarea" value="<%=(vform.getDefaultValuearea()==null)?"":vform.getDefaultValuearea()%>" />
		<input type="hidden" name="decrementlistdata" value="<%=(vform.getVariablename()==null)?"":vform.getVariablename()%>"/>
		<input type="hidden" name="storelistdata" value="<%=(vform.getVariablename()==null)?"":vform.getVariablename()%>" />
        <input type="hidden" name="storDefaultValue" value="<%=(vform.getDefaultValue()==null)?"":vform.getDefaultValue()%>"/>
		<input type="hidden" name="storedate" value="<%=vform.getDefaultValueDate()%>" />
<% if(vform.getVariableId()!=0){%>
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.update",user1.getLocale())%>" onClick="updatedata()" class="button">
			<input type="button" name="delete" value="<%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%>" onClick="deletedata()" class="button">
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
			
			<%}else{%>
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.save",user1.getLocale())%>" onClick="savedata(this.form.listdata1)" class="button">
			
			<input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()" class="button">
	
		<%}%>
			
</div>

<%}%>
<%}%>

</html:form>

</body>

