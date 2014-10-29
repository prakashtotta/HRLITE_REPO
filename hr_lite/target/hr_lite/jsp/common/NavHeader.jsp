<%@ page import="java.util.*"%>
<%@ page import="com.bean.User"%>
<%@ page import="com.common.Common"%>
<%
User user = (User)session.getAttribute(Common.USER_DATA);
   
%>

<link rel="stylesheet" type="text/css" href="jsp/yahoo/build/reset-fonts-grids/reset-fonts-grids.css">
 

        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="jsp/yahoo/build/menu/assets/skins/sam/menu.css"> 
 

        <!-- Page-specific styles -->

        <style type="text/css">

            div.yui-b p {
            
                margin: 0 0 .5em 0;
                color: #999;
            
            }
            
            div.yui-b p strong {
            
                font-weight: bold;
                color: #000;
            
            }
            
            div.yui-b p em {

                color: #000;
            
            }            
            
            h1 {

                font-weight: bold;
                margin: 0 0 1em 0;
                padding: .25em .5em;
                background-color: #ccc;

            }

            #productsandservices {
            
                margin: 0 0 10px 0;
            
            }

        </style>


        <!-- Dependency source files -->

        <script type="text/javascript" src="jsp/yahoo/build/yahoo-dom-event/yahoo-dom-event.js"></script>
        <script type="text/javascript" src="jsp/yahoo/build/container/container_core.js"></script>


        <!-- Menu source file -->

        <script type="text/javascript" src="jsp/yahoo/build/menu/menu.js"></script>


        <!-- Page-specific script -->

        <script type="text/javascript">

            /*
                 Initialize and render the MenuBar when its elements are ready 
                 to be scripted.
            */

            YAHOO.util.Event.onContentReady("productsandservices", function () {

                /*
					Instantiate a MenuBar:  The first argument passed to the constructor
					is the id for the Menu element to be created, the second is an 
					object literal of configuration properties.
                */

                var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", { 
                                                            autosubmenudisplay: true, 
                                                            hidedelay: 750, 
                                                            lazyload: true });

                /*
                     Call the "render" method with no arguments since the 
                     markup for this MenuBar instance is already exists in 
                     the page.
                */

                oMenuBar.render();

            });

        </script>

  <body class="yui-skin-sam" id="yahoo-com">  


<%if(user != null){%>
<table cellpadding="0" cellspacing="0" width="100%">
<tr valign="top" bgcolor="#eeeeee">
      <td width="20"><img valign="top" align="left" src="jsp/images/spacer.gif" border="0"></td><td>Welcome &nbsp;<a href="/user.do?method=mydetails"> <%=user.getFirstName()+" "+user.getLastName()%> </a>&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/login.do?method=logout">logout</a>
	  </td>
	  <td>





<div id="doc" class="yui-t1">
           
             <div id="hd">
                <!-- start: your content here -->
                
                  
        
                <!-- end: your content here -->
            </div>

            <div id="bd">

                <!-- start: primary column from outer template -->
                <div id="yui-main">
                    <div class="yui-b">
                        <!-- start: stack grids here -->

                       <div id="productsandservices" class="yuimenubar yuimenubarnav">
                            <div class="bd">
                                <ul class="first-of-type">
                                    <li class="yuimenubaritem first-of-type"><a class="yuimenubaritemlabel" href="#communication">Employee</a>
                
                                        <div id="communication" class="yuimenu">
                                            <div class="bd">
                                                <ul>
                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">employee</a>
													<div id="pim" class="yuimenu">
                                                            <div class="bd">
                                                                <ul class="first-of-type">
                                                                    
                                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/user.do?method=userListwithPag">Employee List</a></li>
                                                                    
                                                                </ul>            
                                                            </div>
                                                        </div>   
													
													</li>
                                                    
                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="http://promo.yahoo.com/broadband/">Admin</a></li>
                                                   
                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/lov.do?method=lovlist">lov list</a></li>
                                                    
                                                </ul>
                                            </div>
                                        </div>      
                                    
                                    </li>
                                    <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="http://shopping.yahoo.com">Organization</a>
                
                                        <div id="shopping" class="yuimenu">
                                            <div class="bd">                    
                                                <ul>
                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/org.do?method=orglist&orgId=1">Organization </a></li>
													
                                                   
                                                </ul>
                                            </div>
                                        </div>                    
                                    
                                    </li>
                                    <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#">Requistion</a>
                
                                        <div id="entertainment" class="yuimenu">
                                            <div class="bd">                    
                                                <ul>
                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/jobtemplate.do?method=templateList">Requistion templates</a></li>
													<li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/jobreq.do?method=jobreqList">Job requistions</a></li>
													<li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/jobreq.do?method=myapprovaljobreqlist">My approval tasks</a></li>
													<li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/jobreq.do?method=jobreqtreelistbyorg">own organization applicant tree</a></li>
                                                    
                                                </ul>                    
                                            </div>
                                        </div>                                        
                                    
                                    </li>
                                    <li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="#information">Recruitment</a>
                
                                        <div id="information" class="yuimenu">
                                            <div class="bd">                                        
                                                <ul>
                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/applicant.do?method=applicantList">applicants</a></li>
													<li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/applicant.do?method=myapplicantList">my applicants</a></li>
													<li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/applicant.do?method=searchownapplicantinit">search my applicants</a></li>
													 <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/jobreq.do?method=jobreqtreelist">applicant tree</a></li>

                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="#">lov</a>
                                                    
                                                        <div id="pim" class="yuimenu">
                                                            <div class="bd">
                                                                <ul class="first-of-type">
                                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/char.do?method=charlist">Competency</a></li>
                                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/groupchar.do?method=groupCharlist">Competency group</a></li>
                                                                    <li class="yuimenuitem"><a class="yuimenuitemlabel" href="<%=request.getContextPath()%>/evtmpl.do?method=evtmpllist">Evaluation Templates</a></li>
                                                                   
                                                                </ul>            
                                                            </div>
                                                        </div>                    
                                                    
                                                    </li>
                                                   
                                                </ul>
                                            </div>
                                        </div>                                        
                                    
                                    </li>


								<li class="yuimenubaritem"><a class="yuimenubaritemlabel" href="<%=request.getContextPath()%>/reports.do?method=firstpage">Reports</a>
                
                                        <div id="information" class="yuimenu">
                                            <div class="bd">                                        
                                                <ul>
                                                   
                                                </ul>
                                            </div>
                                        </div>                                        
                                    
                                    </li>
                                    

                                </ul>            
                            </div>
                        </div>
						     </div>
                        </div>
						     </div>
                        </div>
						

</td>
</tr>

</table>
<%}%>


