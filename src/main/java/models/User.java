package models;
public class User {
    private int User_ID;
    private String user_username;
    private String emri_user;
    private String telefoni_user;
    private String adresa_user;
    private String Salted_Password;
    private String Salt;
    private String gjinia;

    public User(int User_ID, String user_username, String emri_user, String telefoni_user, String adresa_user, String Salted_Password, String Salt,String  gjinia) {
        this.User_ID = User_ID;
        this.user_username = user_username;
        this.emri_user = emri_user;
        this.telefoni_user = telefoni_user;
        this.adresa_user = adresa_user;
        this.Salted_Password = Salted_Password;
        this.Salt = Salt;
        this.gjinia = gjinia;
    }

    public String getuser_username() {
        return user_username;
    }

    public void setuser_username(String User_username) {
        this.user_username = User_username;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public String getemri_user() {
        return emri_user;
    }

    public void setemri_user(String emri_user) {
        this.emri_user = emri_user;
    }

    public String getSalted_Password() {
        return Salted_Password;
    }

    public void setSalted_Password(String Salted_Password) {
        this.Salted_Password = Salted_Password;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    public String getTelefoni_user() {
        return telefoni_user;
    }

    public void setTelefoni_user(String telefoni_user) {
        this.telefoni_user = telefoni_user;
    }

    public String getAdresa_user() {
        return adresa_user;
    }

    public void setAdresa_user(String adresa_user) {
        this.adresa_user = adresa_user;
    }

    public String getGjinia() {
        return gjinia;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
    }
}
