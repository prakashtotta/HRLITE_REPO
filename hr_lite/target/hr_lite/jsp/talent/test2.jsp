<script type="text/javascript" src="../autocomplete/jquery.min.js"></script>
<script language="javascript">
var urrr = null;
function get_short_url(long_url, login, api_key, func) 
{ 
    $.getJSON( 
        "http://api.bitly.com/v3/shorten?callback=?",  
        {  
            "format": "json", 
            "apiKey": api_key, 
            "login": login, 
            "longUrl": long_url 
        }, 
        function(response) 
        { 
            func(response.data.url); 
        } 
    ); 
} 

var login = "satyadas2000"; 
var api_key = "R_2c0b54223fbe6f4723c57446140b790f"; 
var long_url = "http://www.kozlenko.info"; 
 
function test(){
	get_short_url(long_url, login, api_key, function(short_url) { 
    urrr = short_url;
}); 
return urrr;
}

function test1(){
	alert(urrr);
}
</script>

<body onload="test()">



<a href="#" onClick="test1()">ddd</a>