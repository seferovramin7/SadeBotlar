package com.example.shushabot.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Response {
    public ArrayList<Object> venues;
    public Prices prices;
    public Events events;
    public Category category;
}
