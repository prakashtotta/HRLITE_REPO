<script>    
var secondsBeforeExpire = <%= request.getSession().getMaxInactiveInterval()%>  
var timeToDecide = 300; // Give client 15 seconds to choose.   
setTimeout(function() {      
  redirectloginpage()
	}, (secondsBeforeExpire - timeToDecide) * 1000);


function redirectloginpage(){
	var timeToDecide = 5;
	 var doyou = confirm("Your session is about to timeout in " + timeToDecide + " minutes! want to continue? (OK = Yes   Cancel = No)");
	  if (doyou == true){
		 childWindow=window.open("jsp/common/expiring.jsp", "sessiontest","width=300,height=300,left=150,top=100,scrollbar=no,resize=no");
	     //childWindow.close();
	   } 
	}


	
	
</script> 