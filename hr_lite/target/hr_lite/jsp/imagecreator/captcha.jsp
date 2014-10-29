<%@ page import="java.io.*" %>
<%@ page import="java.awt.*"
%><%@ page import="java.awt.geom.*"
%><%@ page import="java.awt.image.*"
%><%@ page import="java.awt.font.*"
%><%@ page import="java.io.*"
%><%@ page import="org.joshy.*"
%><%@ page import="javax.swing.*"
%><%@ page import="javax.imageio.*"
%>



<%
long ran = 100 + (long)(Math.random() * ((10000000 - 100) + 1));
session.setAttribute("randomcaptcha",String.valueOf(ran));

    try {
    
        // configure all of the parameters
        String text = String.valueOf(ran);
        if(request.getParameter("text") != null) {
            text = request.getParameter("text");
        }
        String font_file = "jsp/imagecreator/dungeon.ttf";
        if(request.getParameter("font-file") != null) {
            font_file = request.getParameter("font-file");
        }
        font_file = request.getRealPath(font_file);
        float size = 20.0f;
        if(request.getParameter("size") != null) {
            size = Float.parseFloat(request.getParameter("size"));
        }

        Color background = Color.white;
        if(request.getParameter("background") != null) {
            background = new Color(Integer.parseInt(request.getParameter("background"),16));
        }
        Color color = Color.black;
        if(request.getParameter("color") != null) {
            color = new Color(Integer.parseInt(request.getParameter("color"),16));
        }
        
        Font font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(font_file));
        font = font.deriveFont(size);
        BufferedImage buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        FontRenderContext fc = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text,fc);
        
        // calculate the size of the text
        int width = (int) bounds.getWidth();
        int height = (int) bounds.getHeight();
        
        // prepare some output
        buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        g2 = buffer.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);
        
        // actually do the drawing
        g2.setColor(background);
        g2.fillRect(0,0,width,height);
        g2.setColor(color);
        g2.drawString(text,0,(int)-bounds.getY());
        
        // set the content type and get the output stream
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();

        // output the image as png
        ImageIO.write(buffer, "png", os);
        os.close();
    } catch (Exception ex) {
        System.out.println(ex);
    }
%>
