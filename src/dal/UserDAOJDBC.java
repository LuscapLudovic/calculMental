package dal;

import bo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJDBC extends DataAccessObjectJDBC<User> {

    private static final String AUTHENT_QUERY = "SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String FIND_TEN_BEST_QUERY = "SELECT * FROM user ORDER BY best_score desc LIMIT 10";
    private static final String UPDATE_QUERY = "UPDATE user set login = ?, password = ?, username = ?, best_score = ? WHERE id = ? ";

    public UserDAOJDBC(String dbUrl, String dbLogin, String dbPwd) {
        super(dbUrl, dbLogin, dbPwd);
    }

    @Override
    public void create(User objet) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    /**
     *
     * @param login
     * @param password
     * @return User
     * @throws SQLException
     * requete d'authentification il faudra mettre le cryptage avant l'appel vers la bdd (à test)
     */
    public User authenticate(String login, String password){
        User user = null;
        try (Connection connection = DriverManager.getConnection(dbUrl, dbLogin, dbPwd);
             PreparedStatement ps = connection.prepareStatement(AUTHENT_QUERY)){
            ps.setString(1, login);
            ps.setString(2,password);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> tenBestScore(){
        ArrayList<User> listUser = new ArrayList<>();
        User user = null;

        try (Connection connection = DriverManager.getConnection(dbUrl, dbLogin, dbPwd);
             PreparedStatement ps = connection.prepareStatement(FIND_TEN_BEST_QUERY)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getString("username"),
                            rs.getInt("best_score")
                    );

                    listUser.add(user);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;

    }

    public User Update(User user){
        try (Connection connection = DriverManager.getConnection(dbUrl, dbLogin, dbPwd);
             PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)){
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUsername());
            ps.setInt(4, user.getBest_score());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
