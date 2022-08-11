package com.example.shushabot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Events {
    public int current_page;
    public ArrayList<Object> data;
    public String first_page_url;
    public Object from;
    public Object next_page_url;
    public String path;
    public int per_page;
    public Object prev_page_url;
    @JsonProperty("to")
    public Object myto;
}
