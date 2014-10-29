<%@ include file="../common/include.jsp" %>
<html>

<%
String path = (String)request.getAttribute("filePath");
RefferalEmployee user1 = (RefferalEmployee)request.getSession().getAttribute(Common.EMPLOYEE_REFFERAL_DATA);
String datepattern = DateUtil.getDatePatternFormat(user1.getLocale());
%>
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
/* Clear calendar's float, using dialog inbuilt form element */
    #container .bd form {
        clear:left;
    }

    /* Have calendar squeeze upto bd bounding box */
    #container .bd {
        padding:0;
    }

    #container .hd {
        text-align:left;
    }

    /* Center buttons in the footer */
    #container .ft .button-group {
        text-align:center;  
    }

    /* Prevent border-collapse:collapse from bleeding through in IE6, IE7 */
    #container_c.yui-overlay-hidden table {
        *display:none;
    }

    /* Remove calendar's border and set padding in ems instead of px, so we can specify an width in ems for the container */
    #cal {
        border:none;
        padding:1em;
    }
legend {
	padding: 1px 4px;
	border-color: #999 #ccc #ccc #999;
	border-style: solid;
	border-width: 1px;
	background: #eee;
}
    /* Datefield look/feel */
    .datefield {
        position:relative;
        top:10px;
        left:10px;
        white-space:nowrap;
        border:1px solid black;
        background-color:#eee;
        width:25em;
        padding:5px;
    }

    .datefield input,
    .datefield button,
    .datefield label  {
        vertical-align:middle;
    }

    .datefield label  {
        font-weight:bold;
    }

    .datefield input  {
        width:15em;
    }

    .datefield button  {
        padding:0 5px 0 5px;
        margin-left:2px;
    }

    .datefield button img {
        padding:0;
        margin:0;
        vertical-align:middle;
    }

    /* Example box */
    .box {
        position:relative;
        height:30em;
    }
	</style>
<script language="javascript">


</script>
<bean:define id="locationform" name="locationForm" type="com.form.LocationForm" />



<body class="yui-skin-sam" onload="init()">
<html:form action="/location.do?method=saveLocation">


<div align="center" class="div">

<br>
	 <fieldset><legend><%=Constant.getResourceStringValue("admin.Location.locationDetails",user1.getLocale())%></legend>
	 <table border="0" width="100%">
	<tr>
			<td></td>
			<td></td>
		</tr>	
	
	
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.locationcode",user1.getLocale())%></td>
			<td><%=(locationform.getLocationCode()==null)?"":locationform.getLocationCode()%></td>
		</tr>
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.locationname",user1.getLocale())%></td>
			<td><%=(locationform.getLocationName()==null)?"":locationform.getLocationName()%></td>
		</tr>

		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.address",user1.getLocale())%> </td>
			<td><%=(locationform.getAddress()==null)?"":locationform.getAddress()%></td>
		</tr>
		 <tr>
				<td><%=Constant.getResourceStringValue("admin.Location.zip",user1.getLocale())%> </td>
                <td><%=(locationform.getZip()==null)?"":locationform.getZip()%></td>
	  </tr>	

	  <tr>
				<td><%=Constant.getResourceStringValue("admin.Location.phone",user1.getLocale())%> </td>
	            <td><%=(locationform.getPhone()==null)?"":locationform.getPhone()%></td>
	  
	  </tr>	

	  <tr>
			   <td><%=Constant.getResourceStringValue("admin.Location.fax",user1.getLocale())%> </td>
			   <td><%=(locationform.getFax()==null)?"":locationform.getFax()%></td>
		</tr>
		
				
		
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.region",user1.getLocale())%> </td>
			<td>
			<%=(locationform.getRegionName()==null)?"":locationform.getRegionName()%>
			
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.country",user1.getLocale())%> </td>
			<td>
			<%=(locationform.getCountryName()==null)?"":locationform.getCountryName()%>
			
			</td>
		</tr>
		 <tr>
			<td id="state" ><%=Constant.getResourceStringValue("admin.Location.state",user1.getLocale())%> </td>
			<td>
			<%=(locationform.getStateName()==null)?"":locationform.getStateName()%>
			
			</td>
		</tr>
		<tr>
			<td><%=Constant.getResourceStringValue("admin.Location.city",user1.getLocale())%> </td>
			<td><%=(locationform.getCity()==null)?"":locationform.getCity()%></td>
		</tr>
		</table>
		</fieldset>
		

</div>

</html:form>


</body>

