package models;

import java.time.LocalDate;

public class Klientet {
        private int klient_id;
        private String emri_klient;
        private String mbimeri_klient;
        private String gjinia;
        private int makina_id;
        private String brand_makina;
        private String model_makina;
        private int total;
        private LocalDate date_rented;
        private LocalDate date_returned;

        public Klientet(int klient_id, String emri_klient, String mbimeri_klient, String gjinia, int makina_id, String brand_makina, String model_makina, int total, LocalDate date_rented, LocalDate date_returned) {

                this.klient_id = klient_id;
                this.emri_klient = emri_klient;
                this.mbimeri_klient = mbimeri_klient;
                this.gjinia = gjinia;
                this.makina_id = makina_id;
                this.brand_makina = brand_makina;
                this.model_makina = model_makina;
                this.total = total;
                this.date_rented = date_rented;
                this.date_returned = date_returned;
        }

        public int getKlient_id() {
                return klient_id;
        }

        public void setKlient_id(int klient_id) {
                this.klient_id = klient_id;
        }

        public String getEmri_klient() {
                return emri_klient;
        }

        public void setEmri_klient(String emri_klient) {
                this.emri_klient = emri_klient;
        }

        public String getMbimeri_klient() {
                return mbimeri_klient;
        }

        public void setMbimeri_klient(String mbimeri_klient) {
                this.mbimeri_klient = mbimeri_klient;
        }

        public String getGjinia() {
                return gjinia;
        }

        public void setGjinia(String gjinia) {
                this.gjinia = gjinia;
        }

        public int getMakina_id() {
                return makina_id;
        }

        public void setMakina_id(int makina_id) {
                this.makina_id = makina_id;
        }

        public String getBrand_makina() {
                return brand_makina;
        }

        public void setBrand_makina(String brand_makina) {
                this.brand_makina = brand_makina;
        }

        public String getModel_makina() {
                return model_makina;
        }

        public void setModel_makina(String model_makina) {
                this.model_makina = model_makina;
        }

        public int getTotal() {
                return total;
        }

        public void setTotal(int total) {
                this.total = total;
        }

        public LocalDate getDate_rented() {
                return date_rented;
        }

        public void setDate_rented(LocalDate date_rented) {
                this.date_rented = date_rented;
        }

        public LocalDate getDate_returned() {
                return date_returned;
        }

        public void setDate_returned(LocalDate date_returned) {
                this.date_returned = date_returned;
        }
}
