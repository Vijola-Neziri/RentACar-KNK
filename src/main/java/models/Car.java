package models;

import java.time.LocalDate;

public class Car {
    private int makina_id;
    private String brand_makina;
    private String model_makina;
    private double cmimi_makina;
    private String statusiMakina;
    private String foto_makina;
    private LocalDate date;

    public Car(int makina_id, String brand_makina, String model_makina, double cmimi_makina, String statusiMakina, String foto_makina, LocalDate date) {
        this.makina_id = makina_id;
        this.brand_makina = brand_makina;
        this.model_makina = model_makina;
        this.cmimi_makina = cmimi_makina;
        this.statusiMakina = statusiMakina;
        this.foto_makina = foto_makina;
        this.date = date;
    }

    public int getMakinaId() {
        return makina_id;
    }

    public void setMakinaId(int makina_id) {
        this.makina_id = makina_id;
    }

    public String getBrandMakina() {
        return brand_makina;
    }

    public void setBrandMakina(String brand_makina) {
        this.brand_makina = brand_makina;
    }

    public String getModelMakina() {
        return model_makina;
    }

    public void setModelMakina(String model_makina) {
        this.model_makina = model_makina;
    }

    public double getCmimiMakina() {
        return cmimi_makina;
    }

    public void setCmimiMakina(float cmimi_makina) {
        this.cmimi_makina = cmimi_makina;
    }

    public String getStatusiMakina() {
        return statusiMakina;
    }

    public void setStatusiMakina(String statusiMakina) {
        this.statusiMakina = statusiMakina;
    }

    public String getFotoMakina() {
        return foto_makina;
    }

    public void setFotoMakina(String foto_makina) {
        this.foto_makina = foto_makina;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
