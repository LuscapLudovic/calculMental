package dal;

import bo.User;

import javax.servlet.ServletContext;

public class DAOFactory {
    private static String mode;
    private static String dbUrl;
    private static String dbLogin;
    private static String dbPwd;

    /**
     *
     * @param context
     * @throws ClassNotFoundException
     * pris sur dao-factory de salary
     */
    public static void init(ServletContext context) throws ClassNotFoundException{
        mode = context.getInitParameter("DS_MODE");
        switch ( mode ){
            case "JDBC" :
                // TODO trouver mettre les informations de db.properties dans les variables
                Class.forName(context.getInitParameter("DB_DRIVER"));
                dbUrl = context.getInitParameter( "DB_URL" );
                dbLogin = context.getInitParameter( "DB_LOGIN" );
                dbPwd = context.getInitParameter( "DB_PWD" );
                break;
            case "JPA" :
            default:
                //TODO
        }
    }
    public static IDAO<User> getUserDAO(){
        IDAO<User> dao;
        switch ( mode ) {
            case "JDBC" :
                dao = new UserDAOJDBC(dbUrl, dbLogin, dbPwd);
                break;
            case "JPA":
            default:
                dao = null;
        }
        return dao;
    }
}
