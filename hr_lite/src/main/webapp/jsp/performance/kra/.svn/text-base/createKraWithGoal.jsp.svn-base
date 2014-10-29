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
<bean:define id="kRAForm" name="kRAForm" type="com.performance.form.KRAForm" />
<script language="javascript">

function hideunhide(){
	var tr = document.kRAForm.typekra[0].checked;
	document.kRAForm.action = "kra.do?method=createKraWithGoal&typekra="+tr;
   document.kRAForm.submit();
	
}

function discard(){
	  var doyou = confirm("<%=Constant.getResourceStringValue("hr.methods.discardmsg",user1.getLocale())%>");
	  if (doyou == true){
		 parent.parent.GB_hide(); 
	   }
}

function closewindow2(){
	 parent.parent.GB_hide();
 
	}
function addkra(){
		var tr = document.kRAForm.typekra[0].checked;
	document.kRAForm.action = "kra.do?method=saveKraWithGoal&typekra="+tr+"&goalId=<%=kRAForm.getGoalId()%>";
	document.kRAForm.submit();
}


function getKraData(){
	var tr = document.kRAForm.typekra[0].checked;
	var kraid = document.kRAForm.kraId.value;
	document.kRAForm.action = "kra.do?method=createKraWithGoal&typekra="+tr+"&kraid="+kraid+"&goalId=<%=kRAForm.getGoalId()%>";
    document.kRAForm.submit();
	
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
String isexisting = (String)request.getAttribute("isexisting");
String kraadded = (String)request.getAttribute("kraadded");

String isexistingchecked="";
String isnewchecked="";
if(isexisting != null && isexisting.equals("yes")){
isexistingchecked = "CHECKED";
}else{
	isnewchecked = "CHECKED";
}


%>


<%
if(kraadded != null && kraadded.equals("yes")){

%>

<div align="center">
	<table border="0" width="100%">
	
	
	<tr>
			<td><font color="green"><%=Constant.getResourceStringValue("kra.added.to.the.goal.successfully",user1.getLocale())%></font><!-- <a href="#" onClick="closewindow();return false;"><%=Constant.getResourceStringValue("hr.button.close_window",user1.getLocale())%></a> --></td>
			<td> </td>
		</tr>
		
	</table>
</div>


<%}else{%>

<html:form action="/kra.do?method=createKraWithGoal" >

<html:hidden property="goalId"/>




	<table border="0" width="100%">
	<font color = red ><html:errors /> </font>
	<tr>
			<td> </td>
			<td></td>
		</tr>
	  
		<tr>
			<td align="left" width="20%"><%=Constant.getResourceStringValue("add.kra",user1.getLocale())%></td>
			<td align="left" width="80%">
			<%=Constant.getResourceStringValue("kra.isexisting",user1.getLocale())%>
			<input type="radio" name="typekra" value="E" <%=isexistingchecked%> onClick="hideunhide()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%=Constant.getResourceStringValue("kra.isnew",user1.getLocale())%>
			<input type="radio" name="typekra" value="N" <%=isnewchecked%> onClick="hideunhide()"></td>
		</tr>
      <% if(isexisting != null && isexisting.equals("yes")){%>
        <tr>
		<td align="left" width="20%"><%=Constant.getResourceStringValue("performance.kra",user1.getLocale())%></td>

			<td align="left"  width="80%">
			<html:select  property="kraId" onchange="getKraData()">
			<option value=""></option>
			<bean:define name="kRAForm" property="kraList" id="kraList" />
            <html:options collection="kraList" property="kraId"  labelProperty="kraName"/>
			</html:select>
			</td>
         </tr>

    <% if(kRAForm.getKraId()>0){%>
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
			<bean:define name="kRAForm" property="kraList" id="kraList" />
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

		<tr>
			<td><%=Constant.getResourceStringValue("kra.modifiable",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:checkbox property="modifiable" />
			
			</td>

		</tr>

	<%
		List kpiList = kRAForm.getKpiList();
	
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
				KraKPI krakpi = (KraKPI)kpiList.get(i);
				k++;

	%>

		<tr>
		   <TD><INPUT type="checkbox" name="chk"/></TD>
		    <TD> <textarea name="kpi_<%=k%>"><%=krakpi.getKpiName()%></textarea> </TD>
			 <TD> <textarea name="kpimeasure_<%=k%>"><%=krakpi.getKraMeasure()%></textarea> </TD>
		   </tr>

   <%}}%>
		   </table>
			
			</td>



		</tr>

		<%}// kra id check%>
         <%}else{%>
		
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
			<bean:define name="kRAForm" property="kraList" id="kraList" />
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

		<tr>
			<td><%=Constant.getResourceStringValue("kra.modifiable",user1.getLocale())%><span1>*</span1></td>
			
			<td>
		<html:checkbox property="modifiable" />
			
			</td>

		</tr>

		<tr>
			<td ></td>
			
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
		   <tr>
		   <TD><INPUT type="checkbox" name="chk"/></TD>
		    <TD> <textarea name='kpi_1'></textarea> </TD>
			 <TD> <textarea name='kpimeasure_1'></textarea> </TD>
		   </tr>

		   </tr>
		   </table>
			
			</td>

		</tr>
       
		<tr>
		
		</tr>
		
	<%}%>
	    
       


		<tr><td align="left" width="20%">
		<input type="button" name="login" value="<%=Constant.getResourceStringValue("add.kra",user1.getLocale())%>" onClick="addkra()">
      <input type="button" name="login" value="<%=Constant.getResourceStringValue("hr.button.cancel",user1.getLocale())%>" onClick="discard()">
		
		</td>
      <td></td>

	  </tr>

	

      
	  </table>

</html:form>

<%}%>