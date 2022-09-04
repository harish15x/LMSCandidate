package com.bridgelabz.lmscandidate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class Response {
    private String message;
    private int errorCode;
    private Object token;

    public Response() {

    }
}
