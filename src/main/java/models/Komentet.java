package models;

public class Komentet {
    private String emri;
    private String email;
    private String mesazhi;

    public Komentet(String emri, String email, String mesazhi) {
        this.emri = emri;
        this.email = email;
        this.mesazhi = mesazhi;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMesazhi() {
        return mesazhi;
    }

    public void setMesazhi(String mesazhi) {
        this.mesazhi = mesazhi;
    }
}
