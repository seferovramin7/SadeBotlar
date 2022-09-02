package com.example.shushabot.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ParseHTML {

    public String parseHtml(String rawHTML) throws ParseException {
        Document doc = Jsoup.parse(rawHTML);

        Elements ifExists = doc.select("#content > section.container > div.row.mb-4 > div > div > div > h2");

        if (!ifExists.html().equals("Seçilmiş tarix üzrə bütün biletlər satılmışdır")) {
            for (int i = 1; i <= 5; i++) {

                Elements travelLine = doc.select("#content > section.container > div.row.mb-4 > div > div > div.bus-list > div:nth-child(1)" + i);
                Elements time = doc.select("#content > section.container > div.row.mb-4 > div > div > div.bus-list > div:nth-child(" + i + ") > div > div.col.col-sm-2.text-center.date-info > span");

                System.out.println(time.html());

                return time.html();
            }
        }
        return null;
    }
        public Boolean parseIticketHtml (String rawHTML) throws ParseException {
            Document doc = Jsoup.parse(rawHTML);
            Elements productDate = doc.selectXpath("//*[@id=\"__layout\"]/div/div[2]/div[3]/div");
            String directionString = productDate.html();
            System.out.println("directionString : " + productDate);
            if (directionString.equals("No events found according to your request.")) {
                System.out.println("Bilet yoxdur");
                return true;
            }
            System.out.println("Bilet var");
            return false;
        }

    }
