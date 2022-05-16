package com.model;

import com.vo.Vacancy;

import java.util.List;

//Он будет отвечать за получение данных с сайта.
public interface Strategy {
    List<Vacancy> getVacancies(String searchString);
}
