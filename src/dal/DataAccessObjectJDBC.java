package dal;

public abstract class DataAccessObjectJDBC<E> implements IDAO<E> {

    protected String dbUrl = "";
    protected String dbLogin = "";
    protected String dbPwd = "";

    /**
     *
     * @param dbUrl
     * @param dbLogin
     * @param dbPwd
     *
     */
    public DataAccessObjectJDBC( String dbUrl, String dbLogin, String dbPwd ) {
        this.dbUrl = dbUrl;
        this.dbLogin = dbLogin;
        this.dbPwd = dbPwd;
    }
}