package com.scube.invoicing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.CheckCreditNoteMailStatusEntity;

@Repository
public interface CheckCreditNoteMailStatusRepository extends JpaRepository<CheckCreditNoteMailStatusEntity, String> {
	
	@Query(value = "SELECT * from check_credit_note_mail_status where mail_status = 'N';", nativeQuery = true)
	List<CheckCreditNoteMailStatusEntity> getCreditNoteMailStatusEntityByMailStatus();

}
