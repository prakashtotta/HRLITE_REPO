var months = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var days = new Array("S", "M", "T", "W", "T", "F", "S");

today = new getToday();	
var element_id;

function getDays(month, year) 
{
	// Test for leap year when February is selected.
	if (1 == month)
		return ((0 == year % 4) && (0 != (year % 100))) ||
			(0 == year % 400) ? 29 : 28;
	else
		return daysInMonth[month];
}

function getToday()
{
	// Generate today's date.
	this.now = new Date();
	this.year = this.now.getFullYear() ; // Returned year XXXX
	this.month = this.now.getMonth();
	this.day = this.now.getDate();
}

 
function newCalendar() 
{
	var parseYear = parseInt(document.getElementById('year')  [document.getElementById('year').selectedIndex].text);
 
	var newCal = new Date(parseYear , document.getElementById('month').selectedIndex, 1);
	var day = -1;
	var startDay = newCal.getDay();
	var daily = 0; 

	today = new getToday(); // 1st call
	if ((today.year == newCal.getFullYear() ) &&   (today.month == newCal.getMonth()))
	   day = today.day;
	// Cache the calendar table's tBody section, dayList.
	var tableCal = document.getElementById('calendar').tBodies.dayList;

	var intDaysInMonth =
	   getDays(newCal.getMonth(), newCal.getFullYear() );

	for (var intWeek = 0; intWeek < tableCal.rows.length;  intWeek++)
		   for (var intDay = 0;
			 intDay < tableCal.rows[intWeek].cells.length;
			 intDay++)
	 {
		  var cell = tableCal.rows[intWeek].cells[intDay];

		  // Start counting days.
		  if ((intDay == startDay) && (0 == daily))
			 daily = 1;

		  // Highlight the current day.
		  cell.style.color = (day == daily) ? "red" : "";
		  if(day == daily)
		  {
				document.getElementById('todayday').innerHTML= "Today: " +  day + "/" + 
					(newCal.getMonth()+1) + "/" + newCal.getFullYear() ;
		  }
		  // Output the day number into the cell.
		  if ((daily > 0) && (daily <= intDaysInMonth))
			 cell.innerHTML = daily++;
		  else
			 cell.innerHTML  = "";
	   }

}
	  
	 function getTodayDay()
	 {
			    document.getElementById(element_id).value = today.day + "/" + (today.month+1) + 
					"/" + today.year; 
		        //document.getElementById('calendar').style.visibility="hidden";
				document.getElementById('calendar').style.display="none";
				document.getElementById('year').selectedIndex =100;   
	            document.getElementById('month').selectedIndex = today.month; 
	 }
 
        function getDate(event) 
		 {
		 
            // This code executes when the user clicks on a day
            // in the calendar.
			
            var target = event.target ? event.target : event.srcElement;
			
            if ("TD" == target.tagName)
			// Test whether day is valid.
               if ("" != target.innerHTML)
			   { 
			   	
				 var mn = document.getElementById('month').selectedIndex+1;
    			 var Year = document.getElementById('year') [document.getElementById('year').selectedIndex].text;
				 document.getElementById(element_id).value= target.innerHTML+"/"+mn +"/"  +Year;
				
		         //document.getElementById('calendar').style.visibility="hidden";
				 document.getElementById('calendar').style.display="none";
			 }
		 }
 
function GetBodyOffsetX(el_name, shift)
{

	var x;
	var y;
	x = 0;
	y = 0;

	var elem = document.getElementById(el_name) ; 

	do 
	{
		x += elem.offsetLeft;
		y += elem.offsetTop;
		if (elem.tagName == "BODY")
			break;
		elem = elem.offsetParent; 
	} while  (1 > 0);

	shift[0] = x;
	shift[1] = y;
	return  x;
}	

function SetCalendarOnElement(el_name)
{
	if (el_name=="") 
	el_name = element_id;
	var shift = new Array(2);
	GetBodyOffsetX(el_name, shift);
	document.getElementById('calendar').style.pixelLeft  = shift[0]; //  - document.getElementById('calendar').offsetLeft;
	document.getElementById('calendar').style.pixelTop = shift[1] + 25 ;
}
	  
 	  
	           
function ShowCalendar(elem_name)
{
		if (elem_name=="")
		elem_name = element_id;

		element_id	= elem_name; // element_id is global variable
		newCalendar();
		SetCalendarOnElement(element_id);
		//document.getElementById('calendar').style.visibility = "visible";
		document.getElementById('calendar').style.display="inline";
}

function HideCalendar()
{
	//document.getElementById('calendar').style.visibility="hidden";
	document.getElementById('calendar').style.display="none";
}

function toggleCalendar(elem_name)
{
	//if (document.getElementById('calendar').style.visibility == "hidden")
	if(document.getElementById('calendar').style.display=="none")
		ShowCalendar(elem_name);
	else 
		HideCalendar();
}