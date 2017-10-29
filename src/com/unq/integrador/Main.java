package com.unq.integrador;

import com.unq.integrador.publication.PricePeriod;
import com.unq.integrador.site.PropertyType;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.site.Service;
import com.unq.integrador.site.Site;
import com.unq.integrador.filter.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String [] args) {

        Site site = new Site();

        Set<Publication> publications = new HashSet<>();
        User user = new User("John", "Doe", "john.doe@example.com", "+54 11 1234 5678");

        PricePeriod pricePeriod = new PricePeriod(1, 1, 12, 30, 120);

        //Crear publicaciones
        Publication publication1 = new Publication(user);
        publication1.setCountry("Argentina");
        publication1.setCity("CABA");
        publication1.setCapacity(6);
        publication1.setType(new PropertyType("ph"));
        publication1.setAddress("Moldes 1435");
        publication1.addPricePeriod(pricePeriod);

        Publication publication2 = new Publication(user);
        publication2.setCountry("Argentina");
        publication2.setCity("Bernal");
        publication2.setCapacity(4);
        publication2.setType(new PropertyType("apartment"));
        publication2.setAddress("Cramer 764");
        publication2.addPricePeriod(pricePeriod);
        publication2.addService(new Service("conditioned air"));


        Publication publication3 = new Publication(user);
        publication3.setCountry("Argentina");
        publication3.setCity("Bernal");
        publication3.setCapacity(4);
        publication3.setType(new PropertyType("apartment"));
        publication3.setAddress("Pringles 264");
        publication3.addPricePeriod(pricePeriod);

        //Agregar las publicaciones a la colección
        publications.add(publication1);
        publications.add(publication2);
        publications.add(publication3);

        publications.forEach(publication -> site.registerPublication(publication));


        Filter filter = new AndFilter(
            new CityFilter("Bernal"),
            new AndFilter(
                    new CountryFilter("Argentina"),
                    new AndFilter(
                            new PriceLowerThanFilter(3000, LocalDate.now(), LocalDate.now().plusDays(13)),
                            new HasServiceFilter(new Service("conditioned air"))
                    )

            )
        );

        Set<Publication> results = site.search(filter);

        results.forEach(publication -> System.out.println(publication.getType().getName() + " => "
                + publication.getCountry() + ", " + publication.getCity() + ", " + publication.getAddress()));
   }
}
