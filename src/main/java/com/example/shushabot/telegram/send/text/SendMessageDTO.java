package com.example.shushabot.telegram.send.text;

import com.example.shushabot.telegram.send.ReplyKeyboard;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SendMessageDTO {

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("reply_markup")
    private ReplyKeyboard replyKeyboard;

    @JsonProperty("disable_web_page_preview")
    private Boolean disableWebPagePreview;

}
