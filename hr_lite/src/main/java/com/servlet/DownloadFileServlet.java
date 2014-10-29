package com.servlet;

import com.resources.Constant;
import com.util.StringUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFileServlet
  extends HttpServlet
{
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String isfromjsp = request.getParameter("isfromjsp");
    if (!StringUtils.isNullOrEmpty(isfromjsp))
    {
      processRequestjsp(request, response);
    }
    else
    {
      String filename = request.getParameter("filename");
      String filepath = Constant.getValue("ATTACHMENT_PATH") + File.separator + filename;
      File f = new File(filepath);
      int length = 0;
      ServletOutputStream op = response.getOutputStream();
      ServletContext context = getServletConfig().getServletContext();
      String mimetype = context.getMimeType(filepath);
      




      response.setContentType(mimetype != null ? mimetype : "application/octet-stream");
      response.setContentLength((int)f.length());
      response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
      




      byte[] bbuf = new byte[filepath.length()];
      DataInputStream in = new DataInputStream(new FileInputStream(f));
      while ((in != null) && ((length = in.read(bbuf)) != -1)) {
        op.write(bbuf, 0, length);
      }
      in.close();
      op.flush();
      op.close();
    }
  }
  
  protected void processRequestjsp(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String filename = request.getParameter("filename");
    String filepath = getServletContext().getRealPath("/") + "jsp" + File.separator + "files" + File.separator + filename;
    System.out.println(filepath);
    File f = new File(filepath);
    int length = 0;
    ServletOutputStream op = response.getOutputStream();
    ServletContext context = getServletConfig().getServletContext();
    String mimetype = context.getMimeType(filepath);
    




    response.setContentType(mimetype != null ? mimetype : "application/octet-stream");
    response.setContentLength((int)f.length());
    response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
    




    byte[] bbuf = new byte[filepath.length()];
    DataInputStream in = new DataInputStream(new FileInputStream(f));
    while ((in != null) && ((length = in.read(bbuf)) != -1)) {
      op.write(bbuf, 0, length);
    }
    in.close();
    op.flush();
    op.close();
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    processRequest(request, response);
  }
  
  public String getServletInfo()
  {
    return "Short description";
  }
}
