package controller;


import bo.Expression;
import bo.Operator;
import bo.User;
import model.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

@WebServlet( name = "calculController", urlPatterns = "/calculs" )
public class CalculController  extends HttpServlet {

    private static final String PAGE_CALC_JSP = "/WEB-INF/jsp/calcul.jsp";
    private static final String PAGE_RESULT_JSP = "/WEB-INF/jsp/result.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Affichez le formulaire des calculs

        HttpSession session = req.getSession( true );

        List<Expression> dataSessionCalculs = null;//( List<Expression> ) session.getAttribute( "expressions" );

        if ( null == dataSessionCalculs ) {
            try {
                dataSessionCalculs =  Expression.generateCalcul(10);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            session.setAttribute( "expressions", dataSessionCalculs );
        }

        req.getRequestDispatcher( PAGE_CALC_JSP ).forward( req, resp );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("calcul"));
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
