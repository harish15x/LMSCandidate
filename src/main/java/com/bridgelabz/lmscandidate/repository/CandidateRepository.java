package com.bridgelabz.lmscandidate.repository;

import com.bridgelabz.lmscandidate.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateModel, Long> {
}
