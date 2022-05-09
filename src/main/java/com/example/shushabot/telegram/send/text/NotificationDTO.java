package com.example.shushabot.telegram.send.text;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationDTO {


    public String text;

    public NotificationDTO() {
    }

    public NotificationDTO(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text + "\n";
    }
}
