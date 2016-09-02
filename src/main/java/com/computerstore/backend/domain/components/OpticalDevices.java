package com.computerstore.backend.domain.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Aidem
 */
@Entity
public class OpticalDevices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   // private String ProductNumber;
    private String Description;
    private int Stock;
    private double Price;

    public Long getId() {
        return id;
    }

//    public String getProductNumber() {
//        return ProductNumber;
//    }

    public String getDescription() {
        return Description;
    }

    public int getStock() {
        return Stock;
    }

    public double getPrice() {
        return Price;
    }

    private OpticalDevices(){
    }

    private OpticalDevices(Builder builder) {
        this.id = builder.id;
        //this.ProductNumber = builder.ProductNumber;
        this.Description = builder.Description;
        this.Stock = builder.Stock;
        this.Price = builder.Price;
    }

    public static class Builder{
        private Long id;
        //private String ProductNumber;
        private String Description;
        private int Stock;
        private double Price;

        public Builder id(Long id){
            this.id = id;
            return this;
        }


//        public Builder productNumber(String ProductNumber) {
//            this.ProductNumber = ProductNumber;
//            return this;
//        }



        public Builder description(String Description) {
            this.Description = Description;
            return this;
        }

        public Builder stock(int Stock) {
            this.Stock = Stock;
            return this;
        }


        public Builder price(double Price) {
            this.Price = Price;
            return this;
        }

        public Builder OpticalDevices(OpticalDevices cpu) {
            this.id = cpu.id;
            //this.ProductNumber = cpu.ProductNumber;
            this.Description = cpu.Description;
            this.Stock = cpu.Stock;
            this.Price = cpu.Price;
            return this;
        }

        public OpticalDevices build() {
            return new OpticalDevices(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpticalDevices opticalDevices = (OpticalDevices) o;

        return id == opticalDevices.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
