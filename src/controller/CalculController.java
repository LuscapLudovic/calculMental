package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "calculController", urlPatterns = "/calculs" )
public class CalculController  extends HttpServlet {

    private static final String PAGE_CALC_JSP = "/WEB-INF/jsp/calcul.jsp";
    private static final String PAGE_RESULT_JSP = "/WEB-INF/jsp/result.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Affichez le formulaire des calculs

        req.getRequestDispatcher( PAGE_CALC_JSP ).forward( req, resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //TODO Si le formulaire est correct
        if (true){
            //TODO Afficher le resultat et le classement

            req.getRequestDispatcher( PAGE_RESULT_JSP ).forward( req, resp );
        }else{
            doGet(req, resp);
            //TODO Reafficher le formulaire avec les erreurs
        }
    }
}
