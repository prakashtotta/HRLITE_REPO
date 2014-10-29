

<%

    List formvariableList = aform.getFormVariablesList();

    
	if(formvariableList != null && formvariableList.size()>0){
	
%>

<div style="background: #f3f3f3; text-align:left"><a href="#" rel="toggle[customvariable]" data-openimage="jsp/images/Collapsed.gif" data-closedimage="jsp/images/Expanded.gif"><img src="jsp/images/Expanded.gif" border="0" /></a>More details </div>
<div id="customvariable" style="width  100%;">
<table border="0" width="<%=width%>">

     <% 
	 for(int i=0;i<formvariableList.size();i++){
	FormVariablesMap fvaiablemap = (FormVariablesMap)formvariableList.get(i);
	String mandatorytext="";
	if(!StringUtils.isNullOrEmpty(fvaiablemap.getIsMandatory())  && fvaiablemap.getIsMandatory().equalsIgnoreCase("Y")){
		mandatorytext ="<span1>*</span1>";
	}
	Variables variable = fvaiablemap.getVariable();
	if(variable != null){
     
     %>
    <tr>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_DATE)){
	   String calgentext = VariableDataCaptureUtil.generateJavaScript(variable,request);
	   %>
   <td width="40%"><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>

<%=calgentext%>

    </td>
	 <%}%>

	 <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC))){%>
   <td width="40%"><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
   <input type="text" class="textdynamic" size="50" name="<%=variable.getVariableCode()%>" value="<%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%>"/></td>
	 <%}%>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA)){%>
   <td width="40%"><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
   <textarea name="<%=variable.getVariableCode()%>" rows="3" cols="50"><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></textarea>
    </td>
	 <%}%>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
	Set lovset = variable.getListData();   
	%>
   <td width="40%"><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
 
	<SELECT NAME="<%=variable.getVariableCode()%>" class="list">
	 <OPTION VALUE=""></OPTION>
	<% if(lovset != null && lovset.size()>0){
		Iterator itr = lovset.iterator();
					while(itr.hasNext()){
			VariableListData vdata = (VariableListData)itr.next();
			
			String selectedtext = "";
			if(!StringUtils.isNullOrEmpty(vdata.getIsDefault()) && vdata.getIsDefault().equalsIgnoreCase("Y")){
				selectedtext = "SELECTED";
			}
			if(!StringUtils.isNullOrEmpty(variable.getDefaultValue()) && variable.getDefaultValue().equalsIgnoreCase(vdata.getVariableValue())){
				selectedtext = "SELECTED";
			}
     %>
	<OPTION VALUE="<%=vdata.getVariableValue()%>" <%=selectedtext%>><%=vdata.getVariableValue()%></OPTION>

    <%
		} // itr loop end
	%>
    </td>
	 <%
		}//lovset null check
   }// if type is list
	%>


    </tr>
     <%
		}// if variable null check
		 } // for loop end
		 %>  

</table>
</div>

<%}%>