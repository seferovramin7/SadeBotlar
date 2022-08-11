package com.example.shushabot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Root{
    public int status;
    public String result;
    public Response response;
}


