<html>
<head>
<title>Dynamic Drive- Preload Image Page</title>
<%
String reurl = (String)request.getParameter("reurl");
System.out.println("reurlsatyaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+reurl);
reurl =  request.getContextPath()+"/"+reurl;
%>
<script language="JavaScript1.1">


// You may modify the following:
	var url = "<%=reurl%>" // URL of the page after preload finishes

     
   //convert the url to a string
   // document.getElementById("loading").style.visibility = "visible";

	
		
  	//Do the AJAX call
  	if (window.XMLHttpRequest) { 
	    req = new XMLHttpRequest();    	// Non-IE browsers
    	req.onreadystatechange = processStateChange;

	    try {
    		req.open("GET", url, true);
			
	    } catch (e) {}
	    req.send(null);
  	} else if (window.ActiveXObject) {
       	req = new ActiveXObject("Microsoft.XMLHTTP");// IE
    	if (req) {
	    	req.onreadystatechange=processStateChange;
	        req.open("GET", url, true);
		    req.send();
			
    	}
  	}

	 
function processStateChange() {
	
	if (req.readyState == 4) { // Complete
	    if (req.status == 200) { // OK response
	       	//document.getElementById("states").innerHTML = req.responseText;
    	    //Split the text response into Span elements
    		//spanElements = splitTextIntoSpan(req.responseText);
            //alert(req.responseText);
    		//Use these span elements to update the page
		    replaceExistingWithNewHtml(req.responseText);
		    //onOtherStateSel();
    	} 
    	//document.getElementById("loading").style.visibility = "hidden";	
  	}
}

function replaceExistingWithNewHtml(newTextElements){
	
				  
		    	  
   					document.getElementById("treedatavalue1").innerHTML = newTextElements;
			     			
   	
}

function splitTextIntoSpan(textToSplit){
	//Split the document
  	returnElements=textToSplit.split("</span>")
        
  	//Process each of the elements        
  	for(var i=returnElements.length-1;i>=0;--i){  //Remove everything before the 1st span
	    spanPos = returnElements[i].indexOf("<span");               
                
    	//if we find a match, take out everything before the span
    	if(spanPos>0){
        	  subString=returnElements[i].substring(spanPos);
	          returnElements[i]=subString;
    	} 
  	}
  	return returnElements;
}
	
</script>

<body>

<span class="textboxlabel" id="loading" STYLE="font-size: smaller;Visibility:hidden";>
						Loading ......</span>

<span id="treedatavalue1">
</span>

</body>
</html>
