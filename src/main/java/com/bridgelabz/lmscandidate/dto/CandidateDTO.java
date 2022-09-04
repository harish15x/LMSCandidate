package com.bridgelabz.lmscandidate.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateDTO {


    private String fullName;
    private String email;
    private String mobileNumber;
    private String hiredDate;
    private String degree;
    private Double aggrPer;
    private String city;
    private String state;
    private String preferredJobLocation;
    private String passedOutYear;

}
