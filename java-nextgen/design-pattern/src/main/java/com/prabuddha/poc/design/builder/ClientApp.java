package com.prabuddha.poc.design.builder;

public class ClientApp {
    public static void main(String[] args) {
        FootballerBuilder footballerBuilder =new FootballerBuilder.Builder("Roberto","Baggio",48).position("FWD").build();
        footballerBuilder.printFootballerDetails();
    }
}
