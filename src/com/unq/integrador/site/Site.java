package com.unq.integrador.site;

import com.unq.integrador.user.User;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.score.category.OccupantScoreCategory;
import com.unq.integrador.score.category.OwnerScoreCategory;
import com.unq.integrador.score.category.PropertyScoreCategory;
import com.unq.integrador.filter.Filter;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Site {

    private Set<PropertyType> propertyTypes;
    private Set<Service>  services;
    private Set<PropertyScoreCategory> propertyScoreCategories;
    private Set<OwnerScoreCategory> ownerScoreCategories;
    private Set<OccupantScoreCategory> occupantScoreCategories;
    private Set<User> users;

    public Site() {
        propertyTypes = new HashSet<>();
        services = new HashSet<>();
        propertyScoreCategories = new HashSet<>();
        ownerScoreCategories = new HashSet<>();
        occupantScoreCategories = new HashSet<>();
        users = new HashSet<>();
    }


    public void addPropertyType(PropertyType type) {
        propertyTypes.add(type);
    }

    public void removePropertyType(PropertyType type) {
        propertyTypes.remove(type);
    }

    public Set<PropertyType> getPropertyTypes() {
        return propertyTypes;
    }


    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public Set<Service> getServices() {
        return services;
    }

    public Set<PropertyScoreCategory> getPropertyScoreCategories() {
        return propertyScoreCategories;
    }

    public void addPropertyScoreCategory(PropertyScoreCategory category) {
        propertyScoreCategories.add(category);
    }

    public void removePropertyScoreCategory(PropertyScoreCategory category) {
        propertyScoreCategories.remove(category);
    }

    public Set<OwnerScoreCategory> getOwnerScoreCategories() {
        return ownerScoreCategories;
    }

    public void addOwnerScoreCategory(OwnerScoreCategory category) {
        ownerScoreCategories.add(category);
    }

    public void removeOwnerScoreCategory(OwnerScoreCategory category) {
        ownerScoreCategories.remove(category);
    }

    public Set<OccupantScoreCategory> getOccupantScoreCategories() {
        return occupantScoreCategories;
    }

    public void addOccupantScoreCategory(OccupantScoreCategory category) {
        occupantScoreCategories.add(category);
    }

    public void removeOccupantScoreCategory(OccupantScoreCategory category) {
        occupantScoreCategories.remove(category);
    }

    public Set<Publication> search(Filter filter) {
        return getPublications().stream().filter(publication -> filter.eval(publication)).collect(Collectors.toSet());
    }

    public Set<Publication> getPublications() {
        Set<Publication> publications = new HashSet<>();
        users.forEach(user -> publications.addAll(user.getPublications()));
        return publications;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void registerUser(User user) {
        users.add(user);
    }


}
