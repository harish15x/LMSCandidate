package com.bridgelabz.lmscandidate.service;

import com.bridgelabz.lmscandidate.dto.CandidateDTO;
import com.bridgelabz.lmscandidate.model.CandidateModel;
import com.bridgelabz.lmscandidate.util.ResponseClass;

import java.util.List;

public interface ICandidateService {
    ResponseClass addCandidate(CandidateDTO candidateDTO, String token);

    CandidateModel updadateCandidate(Long id, String token, CandidateDTO candidateDTO);

    List<CandidateModel> getCandidates(String token);

    CandidateModel deleteCandidate(Long id, String token);

    CandidateModel getCandidate(Long id, String token);
}
