package com.dockerizado.apiCompose.entity;


public class ProductDTO {
    public ProductDTO(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public ProductDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    private Integer id;
    private String name;
    private Integer price;
}
