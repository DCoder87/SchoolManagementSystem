package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.FeesTransaction;
import com.school.request.FeesTransactionRequest;

public interface FeesTransactionService {

    boolean saveFeesTransaction(FeesTransactionRequest feesTransactionRequest, String institute_id);

    boolean updateFeesTransaction(FeesTransactionRequest feesTransactionRequest, String institute_id, int id);

    List<FeesTransaction> getallFeesTransactions(String institute_id);

    Optional<FeesTransaction> findFeesTransactionById(int id, String institute_id);

    int deleteFeesTransactionByid(int id, String institute_id);

    int activeFeesTransactionByid(int id, String institute_id);

    List<FeesTransaction> getdeletedFeesTransactions(String institute_id);

    String getFeesTransactionsByFeesTransactionId(String transaction_id, String institute_id);

}
