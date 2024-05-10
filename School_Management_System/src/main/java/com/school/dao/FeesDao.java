package com.school.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.beans.Fees;
import com.school.beans.Institute;
import com.school.beans.StandardMaster;
import com.school.beans.Student;
import com.school.repository.FeesRepository;
import com.school.repository.InstituteRepository;
import com.school.request.FeesRequest;
import com.school.service.FeesService;

@Service
public class FeesDao implements FeesService {

    @Autowired
    FeesRepository feesRepository;

    @Autowired
    InstituteRepository institutionRepository;

    EncryptionAndDecryption decrypt = new EncryptionAndDecryption();

    @Override
    public List<Fees> getallFees(String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        return feesRepository.ListOfAllFees(institute_id1);
    }

    @Override
    public boolean saveFees(FeesRequest feesRequest, String institute_id) {

        String institute_id1 = decrypt.Decryption(institute_id);
        int i_id = Integer.parseInt(institute_id1);

        try {
            Institute institute = new Institute();
            institute.setInstitute_id(i_id);
            
            Student student = new Student();
            student.setStudent_id(feesRequest.getStudent_id());
            
            StandardMaster standard = new StandardMaster();
            standard.setStandard_id(feesRequest.getStandard_id());

            Fees fees = new Fees();
            fees.setAmount_type(feesRequest.getAmount_type());
            fees.setGrand_total(feesRequest.getGrand_total());
            fees.setLate_fee(feesRequest.getLate_fee());
            fees.setStudent(student);
            fees.setStandardmaster(standard);
            fees.setTotal_amount(feesRequest.getTotal_amount());
            fees.setStatus(1);
            fees.setInstitute(institute);

            feesRepository.save(fees);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateFees(FeesRequest feesRequest, String institute_id, int id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        int i_id = Integer.parseInt(institute_id1);

        try {
            Institute institute = new Institute();
            institute.setInstitute_id(i_id);
            
            Student student = new Student();
            student.setStudent_id(feesRequest.getStudent_id());
            
            StandardMaster standard = new StandardMaster();
            standard.setStandard_id(feesRequest.getStandard_id());


            Fees fees = new Fees();
            fees.setAmount_type(feesRequest.getAmount_type());
            fees.setGrand_total(feesRequest.getGrand_total());
            fees.setLate_fee(feesRequest.getLate_fee());
            fees.setStudent(student);
            fees.setStandardmaster(standard);
            fees.setTotal_amount(feesRequest.getTotal_amount());
            fees.setStatus(1);
            fees.setFee_id(id);
            fees.setInstitute(institute);

            feesRepository.save(fees);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Fees> findFeesById(int id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        return feesRepository.findFeesById(id, institute_id1);
    }

    @Override
    public int deleteFeesByid(int id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        int deleted = feesRepository.deleteByFeesId(id, institute_id1);
        if (deleted == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int activeFeesByid(int id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        int activated = feesRepository.activeByFeesId(id, institute_id1);
        if (activated == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<Fees> getdeletedFees(String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        return feesRepository.ListOfdeletedFees(institute_id1);
    }

    @Override
    public String findFeesByFeesId(String fees_id, String institute_id) {
        String institute_id1 = decrypt.Decryption(institute_id);
        String f_id = feesRepository.findFeesByFeesId(fees_id, institute_id1);
        return f_id;
    }

}
