package com.example.shushabot.service.impl;


import com.example.shushabot.configuration.HttpClientConfig;
import com.example.shushabot.configuration.RestTemplateConfig;
import com.example.shushabot.util.ParseHTML;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@Service
@ContextConfiguration(classes = {RestTemplateConfig.class, HttpClientConfig.class})
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ParseHTML parseHTML;

    public Boolean generalRestService() throws IOException, ParseException {
        return parseHTML.parseHtml();
    }
}
