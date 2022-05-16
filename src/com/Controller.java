package com;

import com.model.HHStrategy;
import com.model.Model;
import com.model.Provider;
import com.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//в нем будет содержаться логика работы программы
public class Controller {
     private Model model;

    public Controller(Model model) {
        if (model==null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName){
        model.selectCity(cityName);
    }
}
