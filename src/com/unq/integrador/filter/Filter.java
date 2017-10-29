package com.unq.integrador.filter;


import com.unq.integrador.publication.Publication;

public interface Filter {

    Boolean eval(Publication publication) ;
}
