package com.example.shushabot.service.impl;

import com.example.shushabot.service.HttpRequestService;
import com.example.shushabot.service.MessageProvider;
import com.example.shushabot.service.MessageReceiverService;
import com.example.shushabot.telegram.send.KeyboardButtonDTO;
import com.example.shushabot.telegram.send.ReplyKeyboardMarkupDTO;
import com.example.shushabot.telegram.send.ReplyKeyboardRemoveDTO;
import com.example.shushabot.telegram.send.SendMessageResponseDTO;
import com.example.shushabot.telegram.send.text.SendMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
public class MessageReceiverServiceImpl implements MessageReceiverService {

    private final HttpRequestService httpRequestService;
    private final MessageProvider messageProvider;

    @Value("${telegram.api.base-url}")
    private String telegramApiBaseUrl;
    @Value("${telegram.api.token}")
    private String botToken;
    @Value("${telegram.bot.name}")
    private String botName;

    private Long offset = null;
    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public MessageReceiverServiceImpl(HttpRequestService httpRequestService, MessageProvider messageProvider) {
        this.httpRequestService = httpRequestService;
        this.messageProvider = messageProvider;
    }

    @Override
    public SendMessageResponseDTO sendMessage(SendMessageDTO sendMessageDTO) {
        String url = telegramApiBaseUrl + "/bot" + botToken + "/sendMessage";
        SendMessageResponseDTO sendMessageResponseDTO = httpRequestService.sendPostRequest(url, sendMessageDTO, SendMessageResponseDTO.class);
        return sendMessageResponseDTO;
    }

    private SendMessageDTO getSearchTextMessage(Long chatId) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        sendMessageDTO.setText(messageProvider.getMessage("question_search_text"));
        return sendMessageDTO;
    }

    private SendMessageDTO getKeywordsTextMessage(Long chatId) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        sendMessageDTO.setText(messageProvider.getMessage("question_search_keywords"));
        return sendMessageDTO;
    }

    private SendMessageDTO getExceededLimitsMessage(Long chatId) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        sendMessageDTO.setText(messageProvider.getMessage("exceeded_limits_message"));
        return sendMessageDTO;
    }

    private SendMessageDTO getPriceQuestionMessage(Long chatId, boolean isLowPriceQuestion) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        if (isLowPriceQuestion)
            sendMessageDTO.setText(messageProvider.getMessage("question_min_price"));
        else
            sendMessageDTO.setText(messageProvider.getMessage("question_max_price"));
        return sendMessageDTO;
    }

    private SendMessageDTO getInvalidNumberErrorMessage(Long chatId) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setText(messageProvider.getMessage("invalid_number_info"));
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        return sendMessageDTO;
    }

    public SendMessageDTO getNewProductMessage(Long chatId, String text) {
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setText(text);
        sendMessageDTO.setDisableWebPagePreview(true);
        sendMessageDTO.setReplyKeyboard(new ReplyKeyboardRemoveDTO(true));
        return sendMessageDTO;
    }

    private SendMessageDTO getSendMessageDTO(Long chatId, KeyboardButtonDTO[][] buttons) {
        ReplyKeyboardMarkupDTO replyKeyboardMarkupDTO = new ReplyKeyboardMarkupDTO();
        replyKeyboardMarkupDTO.setKeyboardButtonArray(buttons);
        replyKeyboardMarkupDTO.setOneTimeKeyboard(true);
        SendMessageDTO sendMessageDTO = new SendMessageDTO();
        sendMessageDTO.setChatId(chatId);
        sendMessageDTO.setText(messageProvider.getMessage("question_delete_search"));
        sendMessageDTO.setReplyKeyboard(replyKeyboardMarkupDTO);
        return sendMessageDTO;
    }


    public int stringCounter(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            count++;
        }
        return count;
    }


}
