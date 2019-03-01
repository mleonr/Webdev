package escom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        
        int n = Integer.parseInt(request.getParameter("n"));
        
        
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet1</title>");                                                                 
            out.println("</head>");
            out.println("<body>");
            out.println("<form action = 'UploadServlet' method = 'post' enctype = 'multipart/form-data'>");
            for(int i=0;i<n;i++){       
                out.println("<input type = 'file' name = 'file"+i+"' size = '45' />");     
                out.println("</br>");
            }
            out.println("<input type = 'submit' value = 'Upload File' />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
       
    }

}
