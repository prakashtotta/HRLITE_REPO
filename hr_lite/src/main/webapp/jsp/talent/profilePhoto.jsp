<%@ page import = "com.bo.*" %>
<%@ page import = "java.io.*" %>


<%
 
  long iNumPhoto ;

  
  if ( request.getParameter("id") != null )
  {
   
    iNumPhoto = new Long(request.getParameter("id")).longValue() ;   
  
    try
    {  
       
       byte[] imgData = BOFactory.getApplicantBO().getApplicantProfilePhoto(iNumPhoto);  
       // display the image
       response.setContentType("image/gif");
       OutputStream o = response.getOutputStream();
       o.write(imgData);
       o.flush(); 
       o.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
     
  }
%>