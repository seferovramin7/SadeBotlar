package com.example.shushabot.service.impl;


import com.example.shushabot.configuration.HttpClientConfig;
import com.example.shushabot.configuration.RestTemplateConfig;
import com.example.shushabot.telegram.send.text.NotificationDTO;
import com.example.shushabot.util.ParseHTML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;

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


}
