package paquete;

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
        String nlineas = request.getParameter("numero");
        HttpSession session = request.getSession();
        session.setAttribute("c1", nlineas);
        int lineas =  Integer.parseInt(nlineas);
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");                                                                 
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='Servlet2' method='get'>");
            for(int i=0;i<lineas;i++){
                out.println("Linea "+i+" Xi <input type='text' name='xi_"+i+"'/><br/>");
                out.println("Linea "+i+" Yi <input type='text' name='yi_"+i+"'/><br/>");
                out.println("Linea "+i+" Xf <input type='text' name='xf_"+i+"'/><br/>");
                out.println("Linea "+i+" Yf <input type='text' name='yf_"+i+"'/><br/>");
            }
            out.println("<input type='submit'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
       
    }

}
