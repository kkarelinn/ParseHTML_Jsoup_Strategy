package com.model;

import com.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Objects;

//Этот класс будет обобщать способ получения данных о вакансиях.
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    //метод будет возвращать все java вакансии с выбранного сайта
    public List<Vacancy> getJavaVacancies(String searchString){
        return strategy.getVacancies(searchString);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

}
