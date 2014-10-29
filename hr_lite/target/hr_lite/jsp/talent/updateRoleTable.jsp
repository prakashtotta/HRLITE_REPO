
<%

String newValue = (String)request.getAttribute("newValue");
System.out.println(newValue);
%>




{
    "replyCode":201, "replyText":"Data Follows", "data":"<%=newValue%>"
}
