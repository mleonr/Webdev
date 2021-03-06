package escom;

import java.io.*;
import java.util.*;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet 
{
   
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 50 * 1024*1024;
   private int maxMemSize = 4 * 1024;
   private File file ;

    
   
    public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
   
      // Check that we have a file upload request
      filePath = request.getRealPath("/"); 
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );

        HttpSession session = request.getSession();
        session.setAttribute("cont", "0");
        
        int it=0;
             
      
      if( !isMultipart ) {
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No se subio el archivo</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
  
      DiskFileItemFactory factory = new DiskFileItemFactory();
   
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
   
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File(filePath));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
   
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try { 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);
	
         // Process the uploaded file items
         Iterator i = fileItems.iterator();
           
        
        while ( i.hasNext () ) {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ) {
               // Get the uploaded file parameters
               String fieldName = fi.getFieldName();//Variable creada con el ciclo for
               String fileName = fi.getName(); //path completo con todo y nombre, depende de donde se ejecute
               String contentType = fi.getContentType(); //tipo de archivo
               String f_name=new String("");
               boolean isInMemory = fi.isInMemory();
               long sizeInBytes = fi.getSize();
            
                // Write the file
                if( fileName.lastIndexOf("\\") >= 0 ) {
                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                } else {
                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                }
                fi.write( file ) ;
                
                if (fileName.lastIndexOf("\\") >= 0) f_name = fileName.substring(fileName.lastIndexOf("\\")+1);
                else f_name = fileName ;
                
                session.setAttribute("img"+(it++),f_name);              
            }
         }
            session.setAttribute("maxn",Integer.toString(it));
            response.sendRedirect("Servlet_I");
            
        } catch(Exception ex) {
            System.out.println(ex);
        }
        
      }
      
      public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, java.io.IOException 
      {

         throw new ServletException("GET method used with " +
            getClass( ).getName( )+": POST method required.");
      }
   }
