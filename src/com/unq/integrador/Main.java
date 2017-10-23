package com.unq.integrador;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.PublicationService;
import com.unq.integrador.publication.Service;
import com.unq.integrador.search.filter.Equals;
import com.unq.integrador.search.filter.GreaterThan;
import com.unq.integrador.search.filter.GroupFilter;
import com.unq.integrador.search.filter.LessThan;
import com.unq.integrador.search.filter.operator.AndOperator;
import com.unq.integrador.search.filter.operator.OrOperator;
import com.unq.integrador.search.filter.value.FilterValue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String [] args) {

        Set<Publication> publications = new HashSet<>();

        //Crear publicaciones
        Publication publication1 = new Publication();
        publication1.setCountry("Argentina");
        publication1.setCity("CABA");
        publication1.setCapacity(6);
        publication1.setType("ph");
        publication1.setAddress("Moldes 1435");

        Publication publication2 = new Publication();
        publication2.setCountry("Argentina");
        publication2.setCity("Bernal");
        publication2.setCapacity(4);
        publication2.setType("apartment");
        publication2.setAddress("Cramer 764");

        Publication publication3 = new Publication();
        publication3.setCountry("Argentina");
        publication3.setCity("Bernal");
        publication3.setCapacity(4);
        publication3.setType("apartment");
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
        filter.addFilter(new Equals("city", new FilterValue("Bernal")), new OrOperator());

        GroupFilter capacityFilter = new GroupFilter();
        capacityFilter.addFilter(new GreaterThan("capacity", new FilterValue(3)), new AndOperator())
                .addFilter(new LessThan("capacity", new FilterValue(7)), new AndOperator());

        filter.addFilter(capacityFilter, new AndOperator());


        //Ejecutamos la búsquda
        Set<Publication> results = service.search(filter);

        //Mostramos el query string generado por el filtro de búsquda
        System.out.println(filter.getConditionString());
        //Mostramos los resultados
        results.forEach((publication -> System.out.println(publication.getType() + " en " + publication.getCountry() + ", " + publication.getCity() + ": " + publication.getAddress())));

    }
}
