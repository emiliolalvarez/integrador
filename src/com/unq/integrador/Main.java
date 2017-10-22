package com.unq.integrador;

import com.unq.integrador.publication.Publication;
import com.unq.integrador.publication.PublicationService;
import com.unq.integrador.search.filter.Equals;
import com.unq.integrador.search.filter.GroupFilter;
import com.unq.integrador.search.filter.operator.AndOperator;
import com.unq.integrador.search.filter.operator.OrOperator;
import com.unq.integrador.search.filter.value.FilterIntegerValue;
import com.unq.integrador.search.filter.value.FilterStringValue;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String [] args) {

        Set<Publication> publications = new HashSet<>();

        Publication publication1 = new Publication();
        publication1.setCountry("Argentina");
        publication1.setCity("Ciudad Autónoma de Buenos Aires");
        publication1.setCapacity(10);
        Publication publication2 = new Publication();
        publication2.setCountry("Argentina");
        publication2.setCity("Quilmes");
        //publication2.setCapacity(4);

        publications.add(publication1);
        publications.add(publication2);

        PublicationService service = new PublicationService(publications);

        GroupFilter groupFilter = new GroupFilter();
        groupFilter.addFilter(new Equals("country", new FilterStringValue("Argentina")), new AndOperator())
                .addFilter(new Equals("country", new FilterStringValue("Brasil")), new OrOperator())
                .addFilter(new Equals("capacity", new FilterIntegerValue(10)), new AndOperator());

        GroupFilter groupFilter2 = new GroupFilter();
        groupFilter2.addFilter(new Equals("capacity", new FilterIntegerValue(11)), new AndOperator())
                .addFilter(groupFilter2, new OrOperator());

        System.out.println(groupFilter.getConditionString());

        Set<Publication> results = service.search(groupFilter);
        results.forEach((publication -> System.out.println(publication)));
    }
}
