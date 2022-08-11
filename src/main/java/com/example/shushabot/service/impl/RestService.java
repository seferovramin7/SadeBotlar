package com.example.shushabot.service.impl;


import com.example.shushabot.configuration.HttpClientConfig;
import com.example.shushabot.configuration.RestTemplateConfig;
import com.example.shushabot.dto.Root;
import com.example.shushabot.util.ParseHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@Service
@ContextConfiguration(classes = {RestTemplateConfig.class, HttpClientConfig.class})
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ParseHTML parseHTML;

    public Boolean generalRestService(String url) throws IOException, ParseException {
        String result = restTemplate.getForObject(url, String.class);
        try {
            Boolean aBoolean = parseHTML.parseHtml(result);
            return aBoolean;
        } catch (ParseException e) {
            return null;
        }
    }

    public Boolean garabagRestService(String url) throws IOException, ParseException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity entity = new HttpEntity(headers);


        Root result = restTemplate.getForObject(url, Root.class);

        ArrayList<Object> data = result.getResponse().getEvents().getData();

        System.out.println(" My Result " + data);

        if (data.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
