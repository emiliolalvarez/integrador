package com.unq.integrador.publication;

import com.unq.integrador.site.PropertyType;
import com.unq.integrador.site.Service;

import java.util.HashSet;
import java.util.Set;

public class Property {
    PropertyType type;
    Integer surface;
    String country;
    String city;
    String address;
    Integer capacity;
    Set<Service> services;
    Set<Picture> pictures;

    public Property() {
        services = new HashSet<>();
        pictures = new HashSet<>();
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

    public void removePicture(Picture picture) {
        pictures.remove(picture);
    }
}
