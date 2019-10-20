package controller;


import bo.Expression;
import bo.Operator;
import bo.User;
import dal.DAOFactory;
import dal.UserDAOJDBC;
import model.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

@WebServlet( name = "calculController", urlPatterns = "/calculs" )
public class CalculController  extends HttpServlet {

    private static final String PAGE_CALC_JSP = "/WEB-INF/jsp/calcul.jsp";
    private static final String PAGE_RESULT_JSP = "/WEB-INF/jsp/result.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession( true );

        List<Expression> dataSessionCalculs =( List<Expression> ) session.getAttribute( "expressions" );

        // Si le formulaire n'a pas déjà été créé
        if ( null == dataSessionCalculs ) {
            try {
                //Génération de 10 calculs
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

        //Vérification que le formulaire envoyé est le bon
        if (req.getParameter("calcul_0") != null){

            int result = 0;

            ArrayList<Expression> listExp = (ArrayList<Expression>) session.getAttribute("expressions");

            //Calcul du score de l'utilisateur connecté en fonction des réponses données
            for (int i = 0; i < listExp.size(); i++ ){
                result += (listExp.get(i).getResult() == Integer.parseInt(req.getParameter("calcul_" + i))) ? 1 : 0;
            }

            session.setAttribute( "resultCalcul", result );

            // Actualisation du meilleur score de l'utilisateur connecté
            User loginUser = (User)session.getAttribute(LoginBean.ATT_AUTH_SESSION);

            if (loginUser.getBest_score() < result)
                loginUser.setBest_score(result);

            ((UserDAOJDBC) DAOFactory.getUserDAO()).Update(loginUser);

            // Récupération des 10 meilleurs score;
            List<User> bestScoreUser = ((UserDAOJDBC) DAOFactory.getUserDAO()).tenBestScore();

            session.setAttribute("ten_best_user", bestScoreUser);

            // Affichage de la page de résultat
            req.getRequestDispatcher( PAGE_RESULT_JSP ).forward( req, resp );
        }else{
            doGet(req, resp);
        }
    }
}
