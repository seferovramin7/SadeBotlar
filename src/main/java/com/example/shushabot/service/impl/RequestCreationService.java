package com.example.shushabot.service.impl;


import com.example.shushabot.telegram.send.text.NotificationDTO;
import com.example.shushabot.util.URLcreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestCreationService {


    private final MessageReceiverServiceImpl messageReceiverServiceImpl;
    @Autowired
    URLcreator urLcreator;
    @Autowired
    RestService restService;

    @Value("${shusha.advanced1.url}")
    String url1;

    @Value("${shusha.advanced2.url}")
    String url2;

    public RequestCreationService(MessageReceiverServiceImpl messageReceiverServiceImpl) {
        this.messageReceiverServiceImpl = messageReceiverServiceImpl;
    }

    @Scheduled(fixedRateString = "3000")
    public void createRequest() throws IOException, ParseException {
            Boolean aBoolean = restService.generalRestService();
            if (aBoolean) {
                messageReceiverServiceImpl.sendMessage(messageReceiverServiceImpl
                        .getNewProductMessage(508914176L, "Şuşaya Gedirik !"));
                System.out.println("++++++++++++");
        }
    }
}
