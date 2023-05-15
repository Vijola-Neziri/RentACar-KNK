package models;

import java.util.Date;

public class makina{
    private Integer makina_id;
    private String brand_makina;
    private String model_makina;
    private Double cmimi_makina;
    private String statusiMakina;
    private Date date;
    private String foto_makina;

    public makina(Integer makina_id, String brand_makina, String model_makina, Double cmimi_makina, String statusiMakina, String foto_makina, Date date) {
        this.makina_id = makina_id;
        this.brand_makina = brand_makina;
        this.model_makina = model_makina;
        this.cmimi_makina = cmimi_makina;
        this.statusiMakina = statusiMakina;
        this.date = date;
        this.foto_makina = foto_makina;
    }

    public Integer getmakina_id() {
        return makina_id;
    }

    public String getbrand_makina() {
        return brand_makina;
    }

    public String getmodel_makina() {
        return model_makina;
    }

    public Double getcmimi_makina() {
        return cmimi_makina;
    }

    public String getstatusiMakina() {
        return statusiMakina;
    }

    public Date getDate() {
        return date;
    }

    public String getfoto_makina() {
        return foto_makina;
    }
}