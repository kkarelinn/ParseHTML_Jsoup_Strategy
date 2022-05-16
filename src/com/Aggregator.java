package com;

import com.model.HHStrategy;
import com.model.HabrCareerStrategy;
import com.model.Model;
import com.model.Provider;
import com.view.HtmlView;
import com.view.View;
import com.vo.Vacancy;

import java.util.List;

public class Aggregator {
    public static void main(String[] args) {

        View view = new HtmlView();
        Model model = new Model(view, new Provider(new HHStrategy()));
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();

    }
}
