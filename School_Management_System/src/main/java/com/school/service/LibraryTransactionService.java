package com.school.service;

import com.school.beans.LibraryTransaction;
import com.school.beans.Parent;
import com.school.request.LibraryTransactionRequest;
import com.school.request.ParentRequest;

import java.util.List;
import java.util.Optional;

public interface LibraryTransactionService {

    LibraryTransaction saveLibraryTransaction(LibraryTransactionRequest libraryRequest, String institute_id);

    LibraryTransaction updateLibraryTransaction(LibraryTransactionRequest libraryRequest, String institute_id, int id);

    List<LibraryTransaction> getallLibraryTransaction(String institute_id);

    Optional<LibraryTransaction> findLibraryTransactiontById(int id, String institute_id);

    int deleteLibraryTransactionByid(int id, String institute_id);

    int activeLibraryTransactionByid(int id, String institute_id);

    List<LibraryTransaction> getdeletedLibraryTransaction(String institute_id);

    String findLibraryTransactionByLibraryTransactionId(String parent_id, String institute_id);

}
