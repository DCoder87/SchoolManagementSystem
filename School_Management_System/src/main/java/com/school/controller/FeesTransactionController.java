package com.school.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.beans.FeesTransaction;
import com.school.commom.responses.JsonResponses;
import com.school.request.FeesTransactionRequest;
import com.school.service.FeesTransactionService;

@RestController
@RequestMapping("/feestransaction")
public class FeesTransactionController {

	@Autowired
	FeesTransactionService feesTransactionService;

	@GetMapping("/{institute_id}")
	public Map<String, Object> getFeesTransactions(@PathVariable String institute_id) {

		List<FeesTransaction> allFeesTransactions = feesTransactionService.getallFeesTransactions(institute_id);

		if (allFeesTransactions.isEmpty()) {
			return JsonResponses.generateResponse1(false, allFeesTransactions,
					"Institution Id is Invalid Or List is Empty");
		} else {
			return JsonResponses.generateResponse1(true, allFeesTransactions,
					"Fees Transactions Details Get Successfully");
		}
	}

	@PostMapping("/{institute_id}")
	public Map<String, Object> addFeesTransaction(@PathVariable String institute_id,
			@RequestBody FeesTransactionRequest feesTransactionRequest) {
		FeesTransaction savedFeesTransaction = feesTransactionService.saveFeesTransaction(feesTransactionRequest,
				institute_id);

		if (savedFeesTransaction != null) {
			return JsonResponses.generateResponse1(true, savedFeesTransaction, "Fees Transaction Added Successfully");

		} else {
			return JsonResponses.generateResponse1(false, feesTransactionRequest, "Some Data is Null or Invalid");
		}
	}

	@PutMapping("/update/{institute_id}/{transaction_id}")
	public Map<String, Object> updateFeesTransactionById(@PathVariable String institute_id, @PathVariable int transaction_id,
			@RequestBody FeesTransactionRequest feesTransactionRequest) {

		FeesTransaction updatedFeesTransaction = feesTransactionService.updateFeesTransaction(feesTransactionRequest,
				institute_id, transaction_id);

		if (updatedFeesTransaction != null) {
			return JsonResponses.generateResponse1(true, feesTransactionRequest,
					"Fees Transaction Data Updated Successfully");
		} else {
			return JsonResponses.generateResponse1(false, transaction_id, "Fees Transaction Not Found for this ID: " + transaction_id);
		}
	}

	@Transactional
	@DeleteMapping("/delete/{institute_id}/{transaction_id}")
	public Map<String, Object> deleteFeesTransactionById(@PathVariable String institute_id, @PathVariable int transaction_id)
			throws Exception {

		int deleted = feesTransactionService.deleteFeesTransactionByid(transaction_id, institute_id);

		if (deleted == 1) {
			return JsonResponses.generateResponse2(true, "Fees Transaction Deleted Successfully");
		} else {
			return JsonResponses.generateResponse2(false, "No Fees Transaction Found For this ID " + transaction_id);
		}

	}

	@GetMapping("/edit/{institute_id}/{transaction_id}")
	public Map<String, Object> findFeesTransactionById(@PathVariable String institute_id, @PathVariable int transaction_id) {
		Optional<FeesTransaction> feesTransaction = feesTransactionService.findFeesTransactionById(transaction_id,
				institute_id);
		if (feesTransaction.isPresent()) {
			return JsonResponses.generateResponse1(true, feesTransaction, "Fees Transaction Data Fetched Successfully");
		} else {
			return JsonResponses.generateResponse1(false, transaction_id, "Fees Transaction Not Found for Id " + transaction_id);
		}
	}

}
