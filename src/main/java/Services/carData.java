package Services;

import java.util.Date;

public class carData {
    private Integer makina_id;
    private String brand_makina;
    private String model_makina;
    private Double cmimi_makina;
    private String statusiMakina;
    private Date date;
    private String foto_makina;

    public carData(Integer carId, String brand, String model, Double price, String status, String image, Date date) {
        this.makina_id = carId;
        this.brand_makina = brand;
        this.model_makina = model;
        this.cmimi_makina = price;
        this.statusiMakina = status;
        this.date = date;
        this.foto_makina = image;
    }

    public Integer getCarId() {
        return makina_id;
    }

    public String getBrand() {
        return brand_makina;
    }

    public String getModel() {
        return model_makina;
    }

    public Double getPrice() {
        return cmimi_makina;
    }

    public String getStatus() {
        return statusiMakina;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return foto_makina;
    }
}
