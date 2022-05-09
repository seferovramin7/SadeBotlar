package com.example.shushabot.service;


import com.example.shushabot.telegram.send.SendMessageResponseDTO;
import com.example.shushabot.telegram.send.text.SendMessageDTO;
import com.example.shushabot.telegram.update.TelegramUpdateDTO;

import java.io.IOException;
import java.text.ParseException;

public interface MessageReceiverService {

    SendMessageResponseDTO sendMessage(SendMessageDTO sendMessageDTO);
}
