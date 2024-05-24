package com.school.controller;

import com.school.beans.LibraryTransaction;
import com.school.commom.responses.JsonResponses;
import com.school.request.LibraryTransactionRequest;
import com.school.service.LibraryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/library/transaction")
public class LibraryTransactionController {

	@Autowired
    LibraryTransactionService libraryTransactionService;

    @GetMapping("/{institute_id}")
    public Map<String, Object> getLibraryTransaction(@PathVariable String institute_id) {

        List<LibraryTransaction> alllibraryTransactions = libraryTransactionService.getallLibraryTransaction(institute_id);

        if (alllibraryTransactions.isEmpty()) {
            return JsonResponses.generateResponse1(false, alllibraryTransactions, "Institution Id is Invalid Or List is Empty");
        } else {
            return JsonResponses.generateResponse1(true, alllibraryTransactions, "Library Transaction Details Get Successfully");
        }
    }
    
 
    @PostMapping("/{institute_id}")
    public Map<String, Object> addLibraryTransaction(@PathVariable String institute_id,
                                         @RequestBody LibraryTransactionRequest libraryTransactionRequest) {
        LibraryTransaction savedLibraryTransaction = libraryTransactionService.saveLibraryTransaction(libraryTransactionRequest, institute_id);

        if (savedLibraryTransaction != null) {
            return JsonResponses.generateResponse1(true, savedLibraryTransaction, "library Transaction Added Successfully");

        } else {
            return JsonResponses.generateResponse1(false, libraryTransactionRequest, "Some Data is Null or Invalid");
        }
    }


    @PutMapping("/update/{institute_id}/{library_transaction_id}")
    public Map<String, Object> updateLibraryTransactionById(@PathVariable String institute_id, @PathVariable int library_transaction_id,
                                                @RequestBody LibraryTransactionRequest libraryTransactionRequest) {

        LibraryTransaction UpdatedLibraryTransaction = libraryTransactionService.updateLibraryTransaction(libraryTransactionRequest, institute_id, library_transaction_id);

        if (UpdatedLibraryTransaction != null) {
            return JsonResponses.generateResponse1(true, libraryTransactionRequest, "Library Transaction Date Updated Successfully");
        } else {
            return JsonResponses.generateResponse1(false, library_transaction_id, "Library Transaction Not Found fot this ID" + library_transaction_id);
        }
    }

    @Transactional
    @DeleteMapping("/delete/{institute_id}/{library_transaction_id}")
    public Map<String, Object> deleteLibraryTransactionById(@PathVariable String institute_id, @PathVariable int library_transaction_id)
            throws Exception {

        int deleted = libraryTransactionService.deleteLibraryTransactionByid(library_transaction_id, institute_id);

        if (deleted == 1) {
            return JsonResponses.generateResponse2(true, "Library Transaction Deleted Successfully");
        } else {
            return JsonResponses.generateResponse2(false, "No Library Transaction Found For this ID " + library_transaction_id);
        }
    }

    @GetMapping("/edit/{institute_id}/{library_transaction_id}")
    public Map<String, Object> findLibraryTransactionById(@PathVariable String institute_id, @PathVariable int library_transaction_id) {
        Optional<LibraryTransaction> OneLibraryTransaction = libraryTransactionService.findLibraryTransactiontById(library_transaction_id, institute_id);
        if (OneLibraryTransaction.isPresent()) {
            return JsonResponses.generateResponse1(true, OneLibraryTransaction, "Library Transaction Data Fetched Successfully");
        } else {
            return JsonResponses.generateResponse1(false, library_transaction_id, "Library Transaction Not Found for Id " + library_transaction_id);
        }
    }

}
