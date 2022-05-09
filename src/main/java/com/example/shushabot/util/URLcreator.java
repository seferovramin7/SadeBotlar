package com.example.shushabot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class URLcreator {

    @Value("${shusha.search.url}")
    String url;


    public String createUrl(int i) {
        return url + "?page=" + i;
    }

}
