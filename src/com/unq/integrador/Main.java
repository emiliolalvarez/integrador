package com.unq.integrador;

import com.unq.integrador.publication.PropertyType;
import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.PublicationService;
import com.unq.integrador.search.Equals;
import com.unq.integrador.search.GreaterThan;
import com.unq.integrador.search.GroupFilter;
import com.unq.integrador.search.LessThan;
import com.unq.integrador.search.operator.Operator;
import com.unq.integrador.search.value.FilterValue;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String [] args) {

        Set<Publication> publications = new HashSet<>();
        User user = new User("John", "Doe", "john.doe@example.com", "+54 11 1234 5678");

        //Crear publicaciones
        Publication publication1 = new Publication(user);
        publication1.setCountry("Argentina");
        publication1.setCity("CABA");
        publication1.setCapacity(6);
        publication1.setType(new PropertyType("ph"));
        publication1.setAddress("Moldes 1435");

        Publication publication2 = new Publication(user);
        publication2.setCountry("Argentina");
        publication2.setCity("Bernal");
        publication2.setCapacity(4);
        publication2.setType(new PropertyType("apartment"));
        publication2.setAddress("Cramer 764");

        Publication publication3 = new Publication(user);
        publication3.setCountry("Argentina");
        publication3.setCity("Bernal");
        publication3.setCapacity(4);
        publication3.setType(new PropertyType("apartment"));
        publication3.setAddress("Pringles 264");

        //Agregar las publicaciones a la colección
        publications.add(publication1);
        publications.add(publication2);
        publications.add(publication3);


        //Crear una instancia de PublicationServices con la lista de publicaciones
        PublicationService service = new PublicationService(publications);

        //Configuramos un filtro de búsqueda
        //En este ejemplo bsuscamos: departamentos en Bernal de entre 4 y 6 personas de capacidad
        GroupFilter filter = new GroupFilter();
        filter.addFilter(new Equals("city", new FilterValue("Bernal")), Operator.or());

        GroupFilter capacityFilter = new GroupFilter();
        capacityFilter.addFilter(new GreaterThan("capacity", new FilterValue(3)), Operator.and())
                .addFilter(new LessThan("capacity", new FilterValue(7)), Operator.and());

        filter.addFilter(capacityFilter, Operator.and());


        //Ejecutamos la búsquda
        Set<Publication> results = service.search(filter);

        //Mostramos el query string generado por el filtro de búsquda
        System.out.println(filter.getConditionString());
        //Mostramos los resultados
        results.forEach((publication -> System.out.println(publication.getType() + " en " + publication.getCountry() + ", " + publication.getCity() + ": " + publication.getAddress())));

    }
}
