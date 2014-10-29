<%@ include file="../common/include.jsp" %>
<%@ page import="com.bo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.util.*"%>
<%
String path = (String)request.getAttribute("filePath");
User user1 = (User)request.getSession().getAttribute(Common.USER_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>

<%
//response.setHeader("Cache-Control", "no-cache");
		//response.setHeader("Pragma", "no-cache");
		//response.setIntHeader("Expires", 0);
%>
<%

List roundlist = BOFactory.getLovBO().getAllRoundList();
String[] fields = {"roundName"};
String datastring1 = com.util.StringUtils.createYahooStringArray(roundlist,fields);
System.out.println(datastring1);
%>
<br>


<style type="text/css">
#myAutoComplete {
    width:25em; /* set width here or else widget will expand to fit its container */
    padding-bottom:2em;
}
</style>

<script language="javascript">

function deleteRole(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.button.deletemsg",user1.getLocale())%>");
	  if (doyou == true){
		 
	 document.selectionForm.action = "<%=request.getContextPath()%>/selection.do?method=roledelete&roleid="+
			'<bean:write name="selectionForm" property="selId"/>';
   document.forms[0].submit();
 
	   } 
	}

function addRounds(){
	 
		 
	 window.location = "selection.do?method=addselround&selId="+'<bean:write name="selectionForm" property="selId"/>'+"&"+"roundname="+document.selectionForm.myInput.value;
// document.selectionForm.submit();
 
	}

function test(){
   
   parentVar = "set by parent";
vRv = window.showModalDialog("roundSelectionSearch.jsp",window.self, 'status:yes;dialogWidth:350px;dialogHeight:350px;dialogHide:true;help:no;scroll:auto;center:yes;resizable:no');
alert(vRv.ChildName);
}

function PassValuesBack() 
{
    var obj = new Object();
    obj.ChildName = "child";
    window.returnValue = obj;
    window.close();
}

</script>

<body class="yui-skin-sam">
<form name ="selectionForm" action="">

	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			
			<td class="bodytext"><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.details",user1.getLocale())%></td>
		</tr>
	 
		<tr>
			
			<td class="bodytext"><B><bean:write name="selectionForm" property="selName"/></B></td>
		</tr>
		<tr>
			
			<td class="bodytext"><bean:write name="selectionForm" property="selDesc"/></td>
		</tr>
		
		<tr>
			<td></td>
			<td>
			    <logic:present name="selectionForm" property="rounds" scope="request">
             	<logic:iterate id="round" name="selectionForm" property="rounds" type="com.bean.Round" scope="request">
<bean:write name="round" property="roundName"/> 
<br>
</logic:iterate>
</logic:present>

			</td>
		</tr>

		
		<!--<a class='submodal-600-500' href="roundSelectionSearch.jsp">add rounds</a>-->

	
	
		<tr>
			<td>

		<label for="myInput"></label>
<div id="myAutoComplete">
	<input id="myInput" type="text">
	
	<div id="myContainer"></div>
</div>

	<td>
			<td>
			<a href="#" onClick="javascript:addRounds()" ><%=Constant.getResourceStringValue("aquisition.applicant.selectioncycle.add_rounds",user1.getLocale())%></a>
			</td>
		</tr>
		
		<tr>
			<td>
		<!--	<a href="#" onclick="deleteRole()" >delete role</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
			<a href="<%=request.getContextPath()%>/selection.do?method=roledelete&roleid=
			<bean:write name="selectionForm" property="selId"/>"  ><%=Constant.getResourceStringValue("hr.button.delete",user1.getLocale())%></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="submodal-400-250" href="<%=request.getContextPath()%>/selection.do?method=editrole&roleid=
			<bean:write name="selectionForm" property="selId"/>"  ><%=Constant.getResourceStringValue("hr.button.edit",user1.getLocale())%></a>
			
			 <td>
			<td>
			
			</td>
		</tr>

	</table>

</form>


<script type="text/javascript">
YAHOO.example.BasicLocal = function() {
    // Use a LocalDataSource
    var oDS = new YAHOO.util.LocalDataSource(<%=datastring1%>);
    // Optional to define fields for single-dimensional array
    oDS.responseSchema = {fields : ["state"]};

    // Instantiate the AutoComplete
    var oAC = new YAHOO.widget.AutoComplete("myInput", "myContainer", oDS);
    oAC.prehighlightClassName = "yui-ac-prehighlight";
    oAC.useShadow = true;
    
    return {
        oDS: oDS,
        oAC: oAC
    };
}();
</script>

</body>