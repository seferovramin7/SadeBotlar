package com.example.shushabot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ParseHTML {

    public Boolean parseHtml() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("https://yolumuzqarabaga.az/race", String.class);

        Document doc = Jsoup.parse(result);

        Elements elementsByClass = doc.getElementsByClass("bus-item");


        for (Element e : elementsByClass) {
            String dateInfo = e.getElementsByClass("date-info").text();
            String dateString = dateInfo.split(" ")[0];
            String route = dateInfo.substring(dateInfo.indexOf(' ')).trim();


            Calendar c1 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            c1.setTime(sdf.parse(dateString));

            if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) ||
                    (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                if (route.equals("Bakı → Şuşa → Bakı")) {
                    Elements timeInfo = e.getElementsByClass("duration");
                    int numberOfSeats = Integer.parseInt(timeInfo.text().split("/")[0]);
                    if (numberOfSeats <= 0) {
                        System.out.println("-----");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
