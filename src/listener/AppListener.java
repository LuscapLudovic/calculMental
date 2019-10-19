package listener;

import dal.DAOFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger( AppListener.class.getName() );


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.log(Level.INFO, "Initialisation du context applicatif... ");
        try{
            DAOFactory.init(sce.getServletContext());
        } catch ( ClassNotFoundException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
