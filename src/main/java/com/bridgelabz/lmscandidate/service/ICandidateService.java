package com.bridgelabz.lmscandidate.service;

import com.bridgelabz.lmscandidate.dto.CandidateDTO;
import com.bridgelabz.lmscandidate.model.CandidateModel;

import java.util.List;

public interface ICandidateService {
    CandidateModel addCandidate(CandidateDTO candidateDTO);

    CandidateModel updadateCandidate(Long id, String token, CandidateDTO candidateDTO);

    List<CandidateModel> getCandidates(String token);

    CandidateModel deleteCandidate(Long id, String token);

    CandidateModel getCandidate(Long id, String token);
}
