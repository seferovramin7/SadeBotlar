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

    @Value("${garabag.oyunu.search}")
    String garabagUrl;

    public RequestCreationService(MessageReceiverServiceImpl messageReceiverServiceImpl) {
        this.messageReceiverServiceImpl = messageReceiverServiceImpl;
    }

    @Scheduled(fixedRateString = "${task.update-telegram-update.rate}")
    public void createRequest() throws IOException, ParseException {
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String url = urLcreator.createUrl(i);
            Boolean aBoolean = restService.generalRestService(url);
            if (aBoolean) {
                messageReceiverServiceImpl.sendMessage(messageReceiverServiceImpl
                        .getNewProductMessage(-700721976L, "Şuşaya Gedirik !"));
            }
        }
    }

   // @Scheduled(fixedRateString = "${task.update-telegram-update.rate}")
    public void iTicket() throws IOException, ParseException {
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
            Boolean aBoolean = restService.garabagRestService(garabagUrl);
            if (aBoolean) {
                messageReceiverServiceImpl.sendMessage(messageReceiverServiceImpl
                        .getNewProductMessage(-700721976L, "Qarabağ oyununa biletler satışa çıxdı"));
            }
    }
}
