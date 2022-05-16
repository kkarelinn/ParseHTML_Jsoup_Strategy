package com.model;

import com.view.View;
import com.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/*
Добавь два приватных поля - 1) вью, 2) массив провайдеров (аргумент переменной длинны - varargs).

Создай конструктор с двумя параметрами - 1) вью, 2) массив провайдеров.
На неправильные данные кинь IllegalArgumentException.
 */
public class Model {
    private View view;
    private Provider[] providers;

    public Model(View view, Provider... providers) {
        if (view == null || providers==null || providers.length < 1)
            throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }


    public void selectCity(String city){
        List<Vacancy> vacancies = new ArrayList<>();
        for (Provider prov: providers) {
           vacancies.addAll(prov.getJavaVacancies(city)) ;
        }
        view.update(vacancies);
    }
}
