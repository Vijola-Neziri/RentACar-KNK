package models;

public class Admin {
    private int admin_ID;
    private String admin_emri;
    private String telefoni_admin;
    private String adresa_admin;
    private String admin_username;
    private String admin_password;

    public Admin(int admin_ID, String admin_emri, String telefoni_admin, String adresa_admin,
                 String admin_username, String admin_password) {
        this.admin_ID = admin_ID;
        this.admin_emri = admin_emri;
        this.telefoni_admin = telefoni_admin;
        this.adresa_admin = adresa_admin;
        this.admin_username = admin_username;
        this.admin_password = admin_password;
    }

    public int getAdmin_ID() {
        return admin_ID;
    }

    public void setAdmin_ID(int admin_ID) {
        this.admin_ID = admin_ID;
    }

    public String getAdmin_emri() {
        return admin_emri;
    }

    public void setAdmin_emri(String admin_emri) {
        this.admin_emri = admin_emri;
    }

    public String getTelefoni_admin() {
        return telefoni_admin;
    }

    public void setTelefoni_admin(String telefoni_admin) {
        this.telefoni_admin = telefoni_admin;
    }

    public String getAdresa_admin() {
        return adresa_admin;
    }

    public void setAdresa_admin(String adresa_admin) {
        this.adresa_admin = adresa_admin;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }
}
