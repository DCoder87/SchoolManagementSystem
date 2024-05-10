package com.school.service;

import java.util.List;
import java.util.Optional;

import com.school.beans.Fees;
import com.school.request.FeesRequest;

public interface FeesService {

    public boolean saveFees(FeesRequest feesRequest, String institute_id);

    boolean updateFees(FeesRequest feesRequest, String institute_id, int id);

    List<Fees> getallFees(String institute_id);

    Optional<Fees> findFeesById(int id, String institute_id);

    int deleteFeesByid(int id, String institute_id);

    int activeFeesByid(int id, String institute_id);

    List<Fees> getdeletedFees(String institute_id);

    String findFeesByFeesId(String fees_id, String institute_id);
}
