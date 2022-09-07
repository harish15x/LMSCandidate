package com.bridgelabz.lmscandidate.controller;

import com.bridgelabz.lmscandidate.dto.CandidateDTO;
import com.bridgelabz.lmscandidate.model.CandidateModel;
import com.bridgelabz.lmscandidate.service.ICandidateService;
import com.bridgelabz.lmscandidate.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    ICandidateService candidateService;

    @PostMapping("/addcandidate")
    public ResponseEntity<ResponseClass> addCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @RequestHeader String token){
        ResponseClass responseClass = candidateService.addCandidate(candidateDTO, token);
        return new ResponseEntity<>(responseClass, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public CandidateModel updateCandidate(@RequestHeader String token, @Valid @RequestBody CandidateDTO candidateDTO, @PathVariable Long id){
        return candidateService.updadateCandidate(id, token, candidateDTO);
    }

    @GetMapping("/getcandidates")
    public List<CandidateModel> getCandidates(@RequestHeader String token) {
        return candidateService.getCandidates(token);
    }

    @DeleteMapping("deletecandidate/{id}")
    public CandidateModel deleteCandidate(@PathVariable Long id, @RequestHeader String token) {
        return candidateService.deleteCandidate(id, token);
    }

    @GetMapping("getcandidate/{id}")
    public CandidateModel getCandidate(@PathVariable Long id, @RequestHeader String token) {
        return candidateService.getCandidate(id, token);
    }

}
