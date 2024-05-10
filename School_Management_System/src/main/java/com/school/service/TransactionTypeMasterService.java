package com.school.service;

import java.util.List;

import com.school.beans.TransactionTypeMaster;
import com.school.request.TransactionTypeRequest;

public interface TransactionTypeMasterService {

	TransactionTypeMaster saveTransactionType(TransactionTypeRequest transactiontype, String Institute_id);

	List<TransactionTypeMaster> allTransactionType(String Institute_id);

	List<TransactionTypeMaster> allActiveTransactionType(String Institute_id);

	List<TransactionTypeMaster> allDeactiveTransactionType(String Institute_id);

	TransactionTypeMaster getById(int type_id, String Institute_id);

}
