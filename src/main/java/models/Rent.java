package models;

import java.time.LocalDate;

public class Rent {
    private int id;
    private int klient_id;
    private int makina_id;
    private LocalDate data_e_rezervimit;
    private LocalDate data_e_fillimit_te_qirase;
    private LocalDate data_e_mbarimit_te_qirase;
    private float cmimi_total;

    public Rent(int id, int klient_id, int makina_id, LocalDate data_e_rezervimit, LocalDate data_e_fillimit_te_qirase,
                LocalDate data_e_mbarimit_te_qirase, float cmimi_total) {
        this.id = id;
        this.klient_id = klient_id;
        this.makina_id = makina_id;
        this.data_e_rezervimit = data_e_rezervimit;
        this.data_e_fillimit_te_qirase = data_e_fillimit_te_qirase;
        this.data_e_mbarimit_te_qirase = data_e_mbarimit_te_qirase;
        this.cmimi_total = cmimi_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
    }

    public int getMakina_id() {
        return makina_id;
    }

    public void setMakina_id(int makina_id) {
        this.makina_id = makina_id;
    }

    public LocalDate getData_e_rezervimit() {
        return data_e_rezervimit;
    }

    public void setData_e_rezervimit(LocalDate data_e_rezervimit) {
        this.data_e_rezervimit = data_e_rezervimit;
    }

    public LocalDate getData_e_fillimit_te_qirase() {
        return data_e_fillimit_te_qirase;
    }

    public void setData_e_fillimit_te_qirase(LocalDate data_e_fillimit_te_qirase) {
        this.data_e_fillimit_te_qirase = data_e_fillimit_te_qirase;
    }

    public LocalDate getData_e_mbarimit_te_qirase() {
        return data_e_mbarimit_te_qirase;
    }

    public void setData_e_mbarimit_te_qirase(LocalDate data_e_mbarimit_te_qirase) {
        this.data_e_mbarimit_te_qirase = data_e_mbarimit_te_qirase;
    }

    public float getCmimi_total() {
        return cmimi_total;
    }

    public void setCmimi_total(float cmimi_total) {
        this.cmimi_total = cmimi_total;
    }
}
