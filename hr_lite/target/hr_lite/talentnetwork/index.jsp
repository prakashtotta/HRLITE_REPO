<%@ page import="com.common.*"%>
<%@ page import="com.resources.*"%>
<!doctype html>

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="description" content="A brief statement describing your company or product." />
	<meta name="keywords" content="keywords, related to, your company, or your product" />

	<script type="text/javascript" src="scripts/date.format.js"></script>
		 <script src="http://connect.facebook.net/en_US/all.js"></script>


	<title>TalentNetwork</title>

</head>

<script language="javascript">


function gotoApp() {
     var key="";
	FB.init({ appId: '<%=Constant.getValue("app.key")%>', status: true, cookie: true,oauth : true,
        xfbml: true
    });


    FB.getLoginStatus(function(response) {

	if(response.authResponse == null){
		 window.location = "indexdata.jsp";
	}else{
 
  key = response.authResponse.accessToken;
   var urlvalue="../networkhome.do?method=home"+"&fid="+response.authResponse.userID+"&name="+response.name+"&key="+key+"&ddd="+(new Date).getTime();	  
	window.location = urlvalue;
	}

 });
     
        
    }



</script>

<body onLoad="gotoApp()">

<span id='progressbartable1'>
						Loading ...<img src="images/indicator.gif" height="20"  width="40"/> Please wait !!!
</span>

</body>

</html>