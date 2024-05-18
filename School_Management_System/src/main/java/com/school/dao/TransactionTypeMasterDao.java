package com.school.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Institute;
import com.school.beans.TransactionTypeMaster;
import com.school.repository.TransactionTypeMasterRepository;
import com.school.request.TransactionTypeRequest;
import com.school.service.TransactionTypeMasterService;
@Service
public class TransactionTypeMasterDao implements TransactionTypeMasterService {
	
	@Autowired
	TransactionTypeMasterRepository transactiontypemasterRepository;
	
	LocalDateTime today = LocalDateTime.now();

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public TransactionTypeMaster saveTransactionType(TransactionTypeRequest transactiontype, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		System.out.println(transactiontype.getType_description()+"\t"+transactiontype.getType_name());
		int i_id = Integer.parseInt(institute_id1);
		Institute institute=new Institute();
		institute.setInstitute_id(i_id);
		
			 
			 TransactionTypeMaster  transaction=new TransactionTypeMaster();
			 transaction.setType_name(transactiontype.getType_name());
			 transaction.setType_description(transactiontype.getType_description());
			 transaction.setStatus(1);
			 
			 transaction.setInstitute(institute);
			 System.out.println(transaction);
			 TransactionTypeMaster t1=transactiontypemasterRepository.save(transaction);
			 System.out.println(t1);
			 return transactiontypemasterRepository.save(transaction);
		
	}

	@Override
	public List<TransactionTypeMaster> allTransactionType(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return transactiontypemasterRepository.getAllTransactions(institute_id1);
	}

	@Override
	public List<TransactionTypeMaster> allActiveTransactionType(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return transactiontypemasterRepository.getAllActiveTransactions(institute_id1);
	}

	@Override
	public List<TransactionTypeMaster> allDeactiveTransactionType(String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return transactiontypemasterRepository.getAllDeactiveTransactions(institute_id1);
	}

	@Override
	public TransactionTypeMaster getById(int type_id, String Institute_id) {
		String institute_id1 = decrypt.Decryption(Institute_id);
		return transactiontypemasterRepository.findByType_id(type_id, institute_id1);
	}
	
	

	

	

}
