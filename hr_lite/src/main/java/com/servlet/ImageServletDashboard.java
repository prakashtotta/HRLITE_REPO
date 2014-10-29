package com.servlet;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRImageRenderer;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JRRenderable;
import net.sf.jasperreports.engine.JRWrappingSvgRenderer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.util.JRTypeSniffer;
import org.apache.log4j.Logger;

public class ImageServletDashboard
  extends BaseHttpServlet
{
  protected static final Logger logger = Logger.getLogger(ImageServlet.class);
  public static final String IMAGE_NAME_REQUEST_PARAMETER = "image";
  
  public void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    byte[] imageData = null;
    String imageMimeType = null;
    
    String imageName = request.getParameter("image");
    String dashboard = request.getParameter("dashboard");
    if ("px".equals(imageName))
    {
      try
      {
        JRRenderable pxRenderer = JRImageRenderer.getInstance("net/sf/jasperreports/engine/images/pixel.GIF", (byte)1);
        



        imageData = pxRenderer.getImageData();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
    }
    else
    {
      List jasperPrintList = new ArrayList();
      if ((dashboard != null) && (dashboard.equals("declinedoffer")))
      {
        JasperPrint jasperPrint = (JasperPrint)request.getSession().getAttribute("declinedoffer");
        jasperPrintList.add(jasperPrint);
      }
      else if ((dashboard != null) && (dashboard.equals("reqopeningstat")))
      {
        JasperPrint jasperPrint = (JasperPrint)request.getSession().getAttribute("reqopeningstat");
        jasperPrintList.add(jasperPrint);
      }
      else if ((dashboard != null) && (dashboard.equals("applicantofferratio")))
      {
        JasperPrint jasperPrint = (JasperPrint)request.getSession().getAttribute("applicantofferratio");
        jasperPrintList.add(jasperPrint);
      }
      if (jasperPrintList == null) {
        throw new ServletException("No JasperPrint documents found on the HTTP session.");
      }
      JRPrintImage image = JRHtmlExporter.getImage(jasperPrintList, imageName);
      

      JRRenderable renderer = image.getRenderer();
      logger.info("renderer.getType()" + renderer.getType());
      if (renderer.getType() == 1) {
        renderer = new JRWrappingSvgRenderer(renderer, new Dimension(image.getWidth(), image.getHeight()), image.getBackcolor());
      }
      imageMimeType = JRTypeSniffer.getImageMimeType(renderer.getImageType());
      try
      {
        imageData = renderer.getImageData();
      }
      catch (JRException e)
      {
        throw new ServletException(e);
      }
    }
    if ((imageData != null) && (imageData.length > 0))
    {
      if (imageMimeType != null) {
        response.setHeader("Content-Type", imageMimeType);
      }
      response.setContentLength(imageData.length);
      ServletOutputStream ouputStream = response.getOutputStream();
      ouputStream.write(imageData, 0, imageData.length);
      ouputStream.flush();
      ouputStream.close();
    }
  }
}
