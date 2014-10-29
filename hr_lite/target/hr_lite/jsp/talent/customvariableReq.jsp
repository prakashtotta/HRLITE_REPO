

<!--<a href="#" onClick="manageCustomVariables()"><%=Constant.getResourceStringValue("manage.custom.variables",user1.getLocale())%></a> <br>-->


<% 
	if(formvariableList != null && formvariableList.size()>0){
	
%>

<fieldset><legend><%=Constant.getResourceStringValue("Custom.variables",user1.getLocale())%></legend>
<div class="container">
<table  width="100%" valign="left">
     <% 
	 for(int i=0;i<formvariableList.size();i++){
	FormVariablesMap fvaiablemap = (FormVariablesMap)formvariableList.get(i);
	
	String mandatorytext="";
	if(!StringUtils.isNullOrEmpty(fvaiablemap.getIsMandatory())  && fvaiablemap.getIsMandatory().equalsIgnoreCase("Y")){
		mandatorytext ="<font color='red'>*</font>";
	}
	Variables variable = fvaiablemap.getVariable();
	if(variable != null){
     
     %>
     
    <tr>

   	 <% 
   		if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_DATE)){
	   	String calgentext = VariableDataCaptureUtil.generateJavaScript(variable,request);
	 %>

		   <td width="30%">
		   <%=variable.getVariableName()%><%=mandatorytext%></td>
		   <td width="70%"><%=calgentext%></td>
		   
	 <%}%>

	 <% 
	 if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC))){%>

   			<td width="30%">
   			<%=variable.getVariableName()%><%=mandatorytext%></td>
   			<td width="70%"><input type="text" class="text titleHintBox" title="<%=(variable.getVariableDesc()==null)?"":variable.getVariableDesc()%>" size="50" name="<%=variable.getVariableCode()%>" value="<%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%>"/></td>
	 <%}%>

   	<% 
   	 if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA)){%>

		   <td width="30%">
		   
		   <%=variable.getVariableName()%><%=mandatorytext%></td>
		   <td width="70%">
		   <textarea name="<%=variable.getVariableCode()%>" rows="3" cols="50"><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></textarea>
		   </td>
	 <%}%>

    <% 
     if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
		Set lovset = variable.getListData();   
	%>

		   <td width="30%">
		   <%=variable.getVariableName()%><%=mandatorytext%></td>
		   <td width="70%">
	
		   <SELECT  NAME="<%=variable.getVariableCode()%>" class="list titleHintBox" title="<%=(variable.getVariableDesc()==null)?"":variable.getVariableDesc()%>">
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
</fieldset>

<%}%>
