package com.view;

import com.Controller;
import com.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class HtmlView implements View{
    
     private Controller controller;
     private final String filePath  = new File(this.getClass().getResource("vacancies.html").getPath()).toString();// "vacancies.html").toString();// + "\\" + this.getClass().getPackage().getName().replaceAll("\\.", "\\\\") + "\\vacancies.html";//this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(vacancies.size());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Kyiv");
    }
    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath));
    }
    private String getUpdatedFileContent(List<Vacancy> vacancies) {

        try {
            Document doc = getDocument();
            Elements classList = doc.getElementsByClass("template");
            Element template = classList.clone().removeAttr("style").removeClass("template").get(0);


            Elements prevVacancies = doc.getElementsByClass("vacancy");
            for (Element redundant : prevVacancies) {
                if (!redundant.hasClass("template")) {
                    redundant.remove();
                }
            }
            //add new vacancies
            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = template.clone();
                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getTitle());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Elements salaryEls = vacancyElement.getElementsByClass("salary");
                Element salary = salaryEls.get(0);
                salary.appendText(vacancy.getSalary());

                classList.before(vacancyElement.outerHtml());
            }
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";

    }
    private void updateFile(String content) {
        if (!Objects.requireNonNull(content).isEmpty()){
            try (BufferedWriter bos = new BufferedWriter(new FileWriter(filePath))){
                bos.write(content);
                bos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
