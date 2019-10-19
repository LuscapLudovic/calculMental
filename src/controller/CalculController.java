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

        HttpSession session = req.getSession( true );
        System.out.println(req.getParameter("calcul_0"));

        if (req.getParameter("calcul_0") != null){

            int result = 0;

            ArrayList<Expression> listExp = (ArrayList<Expression>) session.getAttribute("expressions");

            for (int i = 0; i < listExp.size(); i++ ){
                System.out.println(listExp.get(i).getResult() +  " == " + Integer.parseInt(req.getParameter("calcul_" + i)));
                result += (listExp.get(i).getResult() == Integer.parseInt(req.getParameter("calcul_" + i))) ? 1 : 0;
            }

            //TODO Afficher le resultat et le classement
            session.setAttribute( "resultCalcul", result );

            req.getRequestDispatcher( PAGE_RESULT_JSP ).forward( req, resp );
        }else{
            doGet(req, resp);
        }
    }
}
