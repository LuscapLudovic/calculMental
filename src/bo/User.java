package bo;

public class User {

    //region Properties
    private int id;
    private String login;
    private String password;
    private String username;
    private int best_score;
    //endregion

    public User(){ }

    public User(int id, String login, String password, String username, int best_score) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.username = username;
        this.best_score = best_score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBest_score() {
        return best_score;
    }

    public void setBest_score(int best_score) {
        this.best_score = best_score;
    }
}
