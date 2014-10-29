

<a class="closelink" href="#" onClick="manageCustomVariables()"><%=Constant.getResourceStringValue("manage.custom.variables",user1.getLocale())%></a> <br><br>
<%
List reqformVariablesList = BOFactory.getVariableBO()
					.getFormVariablesList(Common.FORM_VARIABLE_REQUISTION, 0,user1.getSuper_user_key());
%>
<fieldset><legend><b><%=Constant.getResourceStringValue("Custom.variables.system.defind",user1.getLocale())%></b>
<a href="#" class="titleHintBox"  title="<%=Constant.getResourceStringValue("Requisition.template.Custom.variables.system.defind.help",user1.getLocale())%>"><img src="jsp/images/questionMark.jpg" border="0" width="18" height="18"/></a>
</legend>
<% 
	if(reqformVariablesList != null && reqformVariablesList.size()>0){
	
%>
<table  width="100%">
     <% 
	 for(int i=0;i<reqformVariablesList.size();i++){
	FormVariablesMap fvaiablemap = (FormVariablesMap)reqformVariablesList.get(i);
	String mandatorytext="";
	if(!StringUtils.isNullOrEmpty(fvaiablemap.getIsMandatory())  && fvaiablemap.getIsMandatory().equalsIgnoreCase("Y")){
		mandatorytext ="<span1>*</span1>";
	}
	Variables variable = fvaiablemap.getVariable();
	if(variable != null){
     
     %>
    <tr>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_DATE)){
	   String calgentext = VariableDataCaptureUtil.generateJavaScriptDisabled(variable,request);
	   %>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>

<%=calgentext%>

    </td>
	 <%}%>

	 <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC))){%>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
   <input  type="text" disabled="disabled" size="50" name="<%=variable.getVariableCode()%>" value="<%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%>"/></td>
	 <%}%>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA)){%>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
   <textarea disabled="disabled" name="<%=variable.getVariableCode()%>" rows="3" cols="50"><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></textarea>
    </td>
	 <%}%>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
	Set lovset = variable.getListData();   
	%>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
 
	<SELECT disabled="disabled" NAME="<%=variable.getVariableCode()%>">
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
<%}%>
</fieldset>

<br>
<br>
<fieldset><legend><b><%=Constant.getResourceStringValue("Custom.variables",user1.getLocale())%></b></legend>
<% 
	if(formvariableList != null && formvariableList.size()>0){
	
%>
<table  width="100%">
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
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>

<%=calgentext%>

    </td>
	 <%}%>

	 <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && (variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXT) || variable.getVariableType().equals(Common.VARIABLE_TYPE_NUMERIC))){%>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
   <input type="text" size="50" name="<%=variable.getVariableCode()%>" value="<%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%>"/></td>
	 <%}%>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_TEXTREA)){%>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
   <textarea name="<%=variable.getVariableCode()%>" rows="3" cols="50"><%=(variable.getDefaultValue()==null)?"":variable.getDefaultValue()%></textarea>
    </td>
	 <%}%>

   <% if(!StringUtils.isNullOrEmpty(variable.getVariableType()) && variable.getVariableType().equals(Common.VARIABLE_TYPE_LIST)){
	Set lovset = variable.getListData();   
	%>
   <td><%=variable.getVariableName()%><%=mandatorytext%></td>
   <td>
 
	<SELECT NAME="<%=variable.getVariableCode()%>">
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
<%}%>
</fieldset>


