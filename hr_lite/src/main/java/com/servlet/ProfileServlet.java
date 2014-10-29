package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class ProfileServlet
  extends HttpServlet
{
  protected static final Logger logger = Logger.getLogger(ProfileServlet.class);
  
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    logger.info("inside ProfileServlet");
    String url = request.getRequestURI();
    String username = url.substring(url.indexOf("p/") + 2);
    request.setAttribute("username", username);
    String destination = "/talentnetwork/home/profile.jsp";
    
    RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
    rd.forward(request, response);
  }
}
