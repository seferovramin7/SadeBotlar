package com.example.shushabot.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ParseHTML {

    public Boolean parseHtml(String rawHTML) throws ParseException {
        Document doc = Jsoup.parse(rawHTML);
        for (int i = 1; i <= 10; i++) {
            Elements productDate = doc.select("#content > div > div.col-md-12.mt-4.mt-md-0.d-none.d-md-block > div > div.table-responsive > div.bus-list > div:nth-child( " + i + " ) > div > div.col.col-sm-2.text-center.date-info > span");
            String directionString = productDate.html();

            if (directionString.equals("Bakı → Şuşa → Bakı")) {
                Elements isActiveElement = doc.select("#content > div > div.col-md-12.mt-4.mt-md-0.d-none.d-md-block > div > div.table-responsive > div.bus-list > div:nth-child(" + i + ") > div > div:nth-child(8) > div");
                String isActiveElementString = isActiveElement.html();
                if (!isActiveElementString.equals("Biletlər satılmışdır")) {
                    System.out.println(isActiveElementString);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean parseIticketHtml(String rawHTML) throws ParseException {
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
