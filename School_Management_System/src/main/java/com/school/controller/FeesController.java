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

import com.school.beans.Fees;
import com.school.commom.responses.JsonResponses;
import com.school.request.FeesRequest;
import com.school.service.FeesService;

@RestController
@RequestMapping("/fees")
public class FeesController {

    @Autowired
    FeesService feesService;

    @GetMapping("/{institution_id}")
    public Map<String, Object> getFees(@PathVariable String institution_id) {

        List<Fees> allFees = feesService.getallFees(institution_id);

        if (allFees.isEmpty()) {
            return JsonResponses.generateResponse1(false, allFees, "Institution Id is Invalid Or List is Empty");
        } else {
            return JsonResponses.generateResponse1(true, allFees, "Fees Details Get Successfully");
        }
    }

    @PostMapping("/{institute_id}")
    public Map<String, Object> addFees(@PathVariable String institute_id, @RequestBody FeesRequest feesRequest) {
        boolean savedFees = feesService.saveFees(feesRequest, institute_id);

        if (savedFees) {
            return JsonResponses.generateResponse1(true, savedFees, "Fees Added Successfully");

        } else {
            return JsonResponses.generateResponse1(false, feesRequest, "Some Data is Null or Invalid");
        }
    }

    @PutMapping("/update/{institute_id}/{fees_id}")
    public Map<String, Object> updateFeesById(@PathVariable String institute_id, @PathVariable int fees_id,
            @RequestBody FeesRequest feesRequest) {

        boolean updatedFees = feesService.updateFees(feesRequest, institute_id, fees_id);

        if (updatedFees) {
            return JsonResponses.generateResponse1(true, feesRequest, "Fees Data Updated Successfully");
        } else {
            return JsonResponses.generateResponse1(false, fees_id, "Fees Not Found for this ID: " + fees_id);
        }
    }

    @Transactional
    @DeleteMapping("/delete/{institute_id}/{fees_id}")
    public Map<String, Object> deleteFeesById(@PathVariable String institute_id, @PathVariable int fees_id)
            throws Exception {

        int deleted = feesService.deleteFeesByid(fees_id, institute_id);

        if (deleted == 1) {
            return JsonResponses.generateResponse2(true, "Fees Deleted Successfully");
        } else {
            return JsonResponses.generateResponse2(false, "No Fees Found for this ID: " + fees_id);
        }

    }

    @GetMapping("/edit/{institute_id}/{fees_id}")
    public Map<String, Object> findFeesById(@PathVariable String institute_id, @PathVariable int fees_id) {
        Optional<Fees> fees = feesService.findFeesById(fees_id, institute_id);
        if (fees.isPresent()) {
            return JsonResponses.generateResponse1(true, fees, "Fees Data Fetched Successfully");
        } else {
            return JsonResponses.generateResponse1(false, fees_id, "Fees Not Found for ID: " + fees_id);
        }
    }

}

