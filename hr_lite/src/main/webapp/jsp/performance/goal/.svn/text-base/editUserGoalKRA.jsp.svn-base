<%@ include file="../../common/include.jsp" %>
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
<style>
span1{color:#ff0000;}
</style>
<html>
<bean:define id="userGoalsForm" name="userGoalsForm" type="com.performance.form.UserGoalsForm" />
<script language="javascript">



function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 parent.parent.GB_hide(); 
	   }
}

function closewindow2(){
	 parent.parent.GB_hide();
 
	}
function modifykra(){
	document.userGoalsForm.action = "usergoal.do?method=modifykra";
	document.userGoalsForm.submit();
}






        function addRow(tableID) {
 
            var table = document.getElementById(tableID);
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            cell1.appendChild(element1);
 
          //  var cell2 = row.insertCell(1);
          //  cell2.innerHTML = rowCount + 1;
 
            var cell2 = row.insertCell(1);
			var count =rowCount+1;
 
			cell2.innerHTML = "<textarea name='kpi_"+count+"'></textarea>";

			var cell3 = row.insertCell(2);
				cell3.innerHTML = "<textarea name='kpimeasure_"+count+"'></textarea>";
 
        }
 
        function deleteRow(tableID) {
            try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
            }
            }catch(e) {
                alert(e);
            }
        }
 



function closewindow(){
	  parent.parent.GB_hide();
}
</script>


<body class="yui-skin-sam">
<%
String usergoalmodified = (String)request.getAttribute("usergoalmodified");

String isexistingchecked="";
String isnewchecked="";



%>


<%
if(usergoalmodified != null && usergoalmodified.equals("yes")){

%>

<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("kra.modified.successfully",user1.getLocale())%></font><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
			<td> </td>
		</tr>
		
	</table>
</div>


<%}else{%>

<html:form action="/usergoal.do?method=createKraWithGoal" >

<html:hidden property="userGoalId"/>




	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td> </td>
			<td></td>
		</tr>
	  
		
		<tr>
			<td><%=Constant.getResourceStringValue("kra.name",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:text property="kraName" size="50" maxlength="200"/>
			
			</td>

			</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("kra.description",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:textarea property="kradesc" cols="60" rows="5"/>
			
			</td>

		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("kra.parentKraName",user1.getLocale())%><span1>*</span1></td>
			
			<td>
			<html:select  property="parentKraId">
			<option value=""></option>
			<bean:define name="userGoalsForm" property="kraList" id="kraList" />
            <html:options collection="kraList" property="kraId"  labelProperty="kraName"/>
			</html:select>
			
			</td>

		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("kra.weight",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:text property="kraWeight" size="5" maxlength="5"/>
			
			</td>

		</tr>

		

		
	<%
		List kpiList = userGoalsForm.getKpiList();
	
	%>

		<tr>
			<td></td>
			
			<td>
			<br>
			<a href="#" onclick="addRow('dataTable');return false;"><img src="jsp/images/add.gif"></a> 
			<a href="#" onclick="deleteRow('dataTable');return false;"><img src="jsp/images/delete.gif"></a>
      
		   <table border="1" id="dataTable">
		   <tr>
		   <td><%=Constant.getResourceStringValue("kpi.delete",user1.getLocale())%></td>
		   <td><%=Constant.getResourceStringValue("kra.tasks",user1.getLocale())%></td>
		   <td><%=Constant.getResourceStringValue("kra.measures",user1.getLocale())%></td>
		   </tr>
		   
	<%
		int k=0;
		if(kpiList != null && kpiList.size()>0){
			for(int i=0;i<kpiList.size();i++){
				UserGoalsKpi ugkpi = (UserGoalsKpi)kpiList.get(i);
				k++;

	%>

		<tr>
		   <TD><INPUT type="checkbox" name="chk"/></TD>
		    <TD> <textarea name="kpi_<%=k%>"><%=ugkpi.getKpiName()%></textarea> </TD>
			 <TD> <textarea name="kpimeasure_<%=k%>"><%=ugkpi.getKraMeasure()%></textarea> </TD>
		   </tr>

   <%}}%>
		   </table>
			
			</td>



		</tr>
       
	
		<tr><td align="left" width="20%">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("kra.submit",user1.getLocale())%>" onClick="modifykra()">
      <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
		
		</td>
      <td></td>

	  </tr>

	

      
	  </table>

</html:form>

<%}%>