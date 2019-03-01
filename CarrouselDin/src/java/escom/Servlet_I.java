package escom;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet_I extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int cont = Integer.parseInt((String)session.getAttribute("cont"));
        
        if(cont!=0){
            cont--; 
        }           
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet_I</title>");                                                                 
            out.println("</head>");
            out.println("<body>"); 
            out.println("<img src ='"+(String)session.getAttribute("img"+Integer.toString(cont))+"' witdth='300' height='300'/></br>");
            
            session.setAttribute("cont",Integer.toString(cont));
            
            out.println("<a href='Servlet_I'>Anterior</a>");
            out.println("<a href='Servlet_D'>Siguiente</a>");
            out.println("</body>");
            out.println("</html>");
           
    }

}
