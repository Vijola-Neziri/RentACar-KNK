package models;

import Utils.PasswordHasher;

public class AdminUser extends User {

    private String Admin_emri;
    private String telefoni_admin;
    private String adresa_admin;

    public AdminUser(int User_ID, String user_username, String emri_user,
                     String telefoni_user,
                     String adresa_user,
                     String Salted_Password,
                     String Salt,
                     String gjinia,
                     String Admin_emri,
                     String telefoni_admin,
                     String adresa_admin) {
        super(User_ID, user_username, emri_user, telefoni_user, adresa_user, Salted_Password, Salt, gjinia);
        this.Admin_emri = Admin_emri;
        this.telefoni_admin = telefoni_admin;
        this.adresa_admin = adresa_admin;
    }

    public String getAdmin_emri() {
        return Admin_emri;
    }

    public void setAdmin_emri(String Admin_emri) {
        this.Admin_emri = Admin_emri;
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
}
