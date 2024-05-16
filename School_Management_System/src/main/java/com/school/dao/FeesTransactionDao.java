package com.school.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.school.beans.Fees;
import com.school.beans.FeesTransaction;
import com.school.beans.Institute;
import com.school.beans.Student;
import com.school.beans.TransactionTypeMaster;
import com.school.repository.FeesTransactionRepository;
import com.school.repository.InstituteRepository;
import com.school.request.FeesTransactionRequest;
import com.school.service.FeesTransactionService;

@Service
public class FeesTransactionDao implements FeesTransactionService {

	@Autowired
	FeesTransactionRepository feesTransactionRepository;

	@Autowired
	InstituteRepository institutionRepository;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date today = new Date();

	EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

	@Override
	public List<FeesTransaction> getallFeesTransactions(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return feesTransactionRepository.ListOfAllFeesTransactions(institute_id1);
	}

	@Override
	public FeesTransaction saveFeesTransaction(FeesTransactionRequest feesTransactionRequest, String institute_id) {

		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Fees fees = new Fees();
			fees.setFee_id(feesTransactionRequest.getFee_id());

			Student student = new Student();
			student.setStudent_id(feesTransactionRequest.getStudent_id());

			TransactionTypeMaster transactiontypemaster = new TransactionTypeMaster();
			transactiontypemaster.setType_id(feesTransactionRequest.getType_id());

			FeesTransaction feesTransaction = new FeesTransaction();
			feesTransaction.setAmount_type(feesTransactionRequest.getAmount_type());
			feesTransaction.setBill_status(feesTransactionRequest.isBill_status());
			feesTransaction.setFees(fees);
			feesTransaction.setNext_due_date(feesTransactionRequest.getNext_due_date());
			feesTransaction.setPaid_amount(feesTransactionRequest.getPaid_amount());
			feesTransaction.setStudent(student);
			feesTransaction.setTransaction_date(today);
			feesTransaction.setTypemaster(transactiontypemaster);
			feesTransaction.setStatus(i_id);
			feesTransaction.setInstitute(institute);

			return feesTransactionRepository.save(feesTransaction);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public FeesTransaction updateFeesTransaction(FeesTransactionRequest feesTransactionRequest, String institute_id,
			int id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int i_id = Integer.parseInt(institute_id1);

		try {
			Institute institute = new Institute();
			institute.setInstitute_id(i_id);

			Fees fees = new Fees();
			fees.setFee_id(feesTransactionRequest.getFee_id());

			Student student = new Student();
			student.setStudent_id(feesTransactionRequest.getStudent_id());

			TransactionTypeMaster transactiontypemaster = new TransactionTypeMaster();
			transactiontypemaster.setType_id(feesTransactionRequest.getType_id());

			FeesTransaction feesTransaction = new FeesTransaction();
			feesTransaction.setAmount_type(feesTransactionRequest.getAmount_type());
			feesTransaction.setBill_status(feesTransactionRequest.isBill_status());
			feesTransaction.setFees(fees);
			feesTransaction.setNext_due_date(feesTransactionRequest.getNext_due_date());
			feesTransaction.setPaid_amount(feesTransactionRequest.getPaid_amount());
			feesTransaction.setStudent(student);
			feesTransaction.setTypemaster(transactiontypemaster);
			feesTransaction.setStatus(i_id);
			feesTransaction.setTransaction_id(id);
			feesTransaction.setInstitute(institute);

			return feesTransactionRepository.save(feesTransaction);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Optional<FeesTransaction> findFeesTransactionById(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return feesTransactionRepository.findFeesTransactionById(id, institute_id1);
	}

	@Override
	public int deleteFeesTransactionByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int deleted = feesTransactionRepository.deleteByFeeId(id, institute_id1);
		if (deleted == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int activeFeesTransactionByid(int id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		int activated = feesTransactionRepository.activeByFeeId(id, institute_id1);
		if (activated == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<FeesTransaction> getdeletedFeesTransactions(String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		return feesTransactionRepository.ListOfdeletedFeesTransactions(institute_id1);
	}

	@Override
	public String getFeesTransactionsByFeesTransactionId(String transaction_id, String institute_id) {
		String institute_id1 = decrypt.Decryption(institute_id);
		String feestransaction_id = feesTransactionRepository.getFeesTransactionsByFeesTransactionId(transaction_id,
				institute_id1);
		return feestransaction_id;
	}

}
