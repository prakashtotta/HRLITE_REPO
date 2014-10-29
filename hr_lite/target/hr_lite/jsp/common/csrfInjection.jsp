<%@ page import="com.security.CSRFTokenGenerator" %>
<%
//if(CSRFTokenGenerator.isSecurityEnabled()){

%>
<script type="text/javascript" src="jsp/autocomplete/jquery.min.js"></script>

    <script language="JavaScript">

     $(document).unbind('keydown').bind('keydown', function (event) { 
    var doPrevent = false; 
    if (event.keyCode === 8) { 
        var d = event.srcElement || event.target; 
        if ((d.tagName.toUpperCase() === 'INPUT' && (d.type.toUpperCase() === 'TEXT' || d.type.toUpperCase() === 'PASSWORD'))  || d.tagName.toUpperCase() === 'TEXTAREA') { 
            doPrevent = d.readOnly || d.disabled; 
        } 
        else { 
            doPrevent = true; 
        } 
    } 
 
    if (doPrevent) { 
        event.preventDefault(); 
    } 
}); 


    	$.noConflict();
    	jQuery(document).ready(function ()
        {
    		jQuery("form").each(function()
            {
    			jQuery(this).append('<input type="hidden" name="csrfcode" value="<%=CSRFTokenGenerator.getToken(request)%>" />');
            });
        });
    </script>
<%
//}
%>




