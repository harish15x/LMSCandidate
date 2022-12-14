package com.bridgelabz.lmscandidate.service;

import com.bridgelabz.lmscandidate.dto.CandidateDTO;
import com.bridgelabz.lmscandidate.exception.CandidateNotFoundException;
import com.bridgelabz.lmscandidate.model.CandidateModel;
import com.bridgelabz.lmscandidate.repository.CandidateRepository;
import com.bridgelabz.lmscandidate.util.ResponseClass;
import com.bridgelabz.lmscandidate.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService{

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    MailService mailService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseClass addCandidate(CandidateDTO candidateDTO, String token) {
        boolean isCandidate = restTemplate.getForObject("http://localhost:8083/admin/validate/" + token, Boolean.class );
        if(isCandidate){
                CandidateModel candidateModel = new CandidateModel(candidateDTO);
                candidateModel.setCreationTimeStamp(LocalDateTime.now());
                candidateRepository.save(candidateModel);
                String body = "Candidate added sucessfully " + candidateModel.getId();
                String subject = "Candidates registration completed";
                mailService.send(candidateModel.getEmail(),body, subject);
                return new ResponseClass("Successfull", 200, candidateModel);
       }
        throw new CandidateNotFoundException(400, "no Token found");
    }

    @Override
    public CandidateModel updadateCandidate(Long id, String token, CandidateDTO candidateDTO) {
        boolean isCandidate = restTemplate.getForObject("http://localhost:8083/admin/validate/" + token, Boolean.class);
        if(isCandidate){
            Long userId = tokenUtil.decodeToken(token);
            Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(userId);
            if (isCandidatePresent.isPresent()) {
            Optional<CandidateModel> isCandidateAvailable = candidateRepository.findById(id);
            if (isCandidateAvailable.isPresent()) {
                isCandidateAvailable.get().setFullName(candidateDTO.getFullName());
                isCandidateAvailable.get().setEmail(candidateDTO.getEmail());
                isCandidateAvailable.get().setHiredDate(candidateDTO.getHiredDate());
                isCandidateAvailable.get().setHiredDate(candidateDTO.getHiredDate());
                isCandidateAvailable.get().setDegree(candidateDTO.getDegree());
                isCandidateAvailable.get().setAggrPer(candidateDTO.getAggrPer());
                isCandidateAvailable.get().setCity(candidateDTO.getCity());
                isCandidateAvailable.get().setState(candidateDTO.getState());
                isCandidateAvailable.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
                isCandidateAvailable.get().setPassedOutYear(candidateDTO.getPassedOutYear());
                candidateRepository.save(isCandidateAvailable.get());
                String body = "candidate is added successfully with adminId " + isCandidateAvailable.get().getId();
                String subject = "Candidate registration successful";
                mailService.send(isCandidateAvailable.get().getEmail(), subject, body);
                return isCandidateAvailable.get();
            } else {
                throw new CandidateNotFoundException(400, "Candidate not availale");
            }
        }}
        throw  new CandidateNotFoundException(400, "token is wrong");
    }

    @Override
    public List<CandidateModel> getCandidates(String token) {
        boolean isCandidate = restTemplate.getForObject("http://localhost:8083/admin/validate/" + token, Boolean.class );
        if(isCandidate){
        Long adminId = tokenUtil.decodeToken(token);
        Optional<CandidateModel> isCandidateAvailable = candidateRepository.findById(adminId);
        if (isCandidateAvailable.isPresent()) {
            List<CandidateModel> isCandidatePresent = candidateRepository.findAll();
            if (isCandidatePresent.size() > 0) {
                return isCandidatePresent;
            } else {
                throw new CandidateNotFoundException(400, "No candidate is present ");
            }
        }}
        throw new CandidateNotFoundException(400, "Token is wrong ");
    }

    @Override
    public CandidateModel deleteCandidate(Long id, String token) {
        boolean isCandidate = restTemplate.getForObject("http://localhost:8083/admin/validate/" + token, Boolean.class );
        Long adminId = tokenUtil.decodeToken(token);
        Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(adminId);
        if (isCandidatePresent.isPresent()) {
            Optional<CandidateModel> isCandidateAvailable = candidateRepository.findById(id);
            if (isCandidateAvailable.isPresent()) {
                candidateRepository.delete(isCandidateAvailable.get());
                return isCandidateAvailable.get();
            } else {
                throw new CandidateNotFoundException(400, "Candidate not found");
            }
        }
        throw new CandidateNotFoundException(400, "Token is wrong");
    }

    @Override
    public CandidateModel getCandidate(Long id, String token) {
        boolean isCandidate = restTemplate.getForObject("http://localhost:8083/admin/validate/" + token, Boolean.class );
        Long adminId = tokenUtil.decodeToken(token);
        Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(adminId);
        if (isCandidatePresent.isPresent()) {
            Optional<CandidateModel> isCandidateAvailable = candidateRepository.findById(id);
            if (isCandidateAvailable.isPresent()) {
                return isCandidateAvailable.get();
            } else {
                throw new CandidateNotFoundException(400, "Candidate not found");
            }
        }
        throw new CandidateNotFoundException(400, "Token is wrong");
    }


}
