package com.bridgelabz.lmscandidate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClass {

    private String statusMessage;
    private int statusCode;
    private Object object;

}
