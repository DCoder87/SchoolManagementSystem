package com.school.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.school.beans.TransactionTypeMaster;
import com.school.commom.responses.JsonResponses;
import com.school.repository.InstituteRepository;
import com.school.repository.TransactionTypeMasterRepository;
import com.school.request.TransactionTypeRequest;
import com.school.service.TransactionTypeMasterService;

@RestController
@RequestMapping("transaction_type")
public class TransactionTypeMasterController {

	
	@Autowired
	InstituteRepository instituteRepository;
	
	LocalDateTime today = LocalDateTime.now();
	
	@Autowired
	TransactionTypeMasterRepository transactiontypemasterrepo;
	
	@Autowired
	
	TransactionTypeMasterService transactiontypemasterService;
	
	@PostMapping("{institute_id}")
	public Map<String,Object> saveTransactionType(@RequestBody TransactionTypeRequest transactiontype,@PathVariable String institute_id)
	{
		
	
		TransactionTypeMaster transaction=transactiontypemasterService.saveTransactionType(transactiontype, institute_id);
		
		return transaction!=null?JsonResponses.generateResponse1(true,transaction,"TransactionType saved"):
			JsonResponses.generateResponse1(false,transaction, "Invalid-data");
	}
	
	@GetMapping("{institute_id}")
	 public Map<String,Object> getAllTransactionTypes(@PathVariable String institute_id)
	 {
		List<TransactionTypeMaster> alltrasactiontype=transactiontypemasterService.allTransactionType(institute_id);
		
		return !alltrasactiontype.isEmpty()?JsonResponses.generateResponse1(true, alltrasactiontype, "All Transaction Type Fetched"):
			JsonResponses.generateResponse1(false, null,"Transaction Type Not Available");
	 }
	
	@GetMapping("/active/{institute_id}")
	 public Map<String,Object> getAllactiveTransactionTypes(@PathVariable String institute_id)
	 {
		List<TransactionTypeMaster> allactivetrasactiontype=transactiontypemasterService.allActiveTransactionType(institute_id);
		
		return !allactivetrasactiontype.isEmpty()?JsonResponses.generateResponse1(true, allactivetrasactiontype, "All Active Transaction Type Fetched"):
			JsonResponses.generateResponse1(false, null,"Active Transaction_Type Not Available");
	 }
	

	@GetMapping("/deactive/{institute_id}")
	 public Map<String,Object> getAlldeactiveTransactionTypes(@PathVariable String institute_id)
	 {
		List<TransactionTypeMaster> alldeactivetrasactiontype=transactiontypemasterService.allDeactiveTransactionType(institute_id);
		
		return !alldeactivetrasactiontype.isEmpty()?JsonResponses.generateResponse1(true, alldeactivetrasactiontype, "All Deactive Transaction Type Fetched"):
			JsonResponses.generateResponse1(false, null,"Deactive Transaction_Type Not Available");
	 }
	
	@GetMapping("/edit/{institute_id}/{transaction_type_id}")
	
	public Map<String,Object> getTransactionTypeById(@PathVariable int transaction_type_id,@PathVariable String institute_id)
	{
		TransactionTypeMaster transaction_type=transactiontypemasterService.getById(transaction_type_id, institute_id);
		
		return transaction_type!=null?JsonResponses.generateResponse1(true, transaction_type, "Transaction_Type Found"):
			JsonResponses.generateResponse1(false, transaction_type_id, "Transaction Type Not Found for this Id "+transaction_type_id+"");
	}
	
	
	@PutMapping("/{transaction_type_id}/{institute_id}")
	public Map<String,Object> updateTransactionType(@RequestBody TransactionTypeRequest transaction ,@PathVariable int transaction_type_id,@PathVariable String institute_id)
	{
		TransactionTypeMaster transaction_type=transactiontypemasterService.getById(transaction_type_id, institute_id);
		
		if(transaction_type!=null)
		{
			transaction_type.setType_name(transaction.getType_name());
			transaction_type.setType_description(transaction.getType_description());
			TransactionTypeMaster transaction1=transactiontypemasterrepo.save(transaction_type);
			
			return transaction1!=null?JsonResponses.generateResponse1(true,transaction1 ,"TransactionType Updated"):
				JsonResponses.generateResponse1(false,transaction, "Invalid-data");
		}
		else
		{
			return JsonResponses.generateResponse1(false, transaction_type_id, "Transaction Type Not Found for this Id "+transaction_type_id+"");
		}
	}
	
	@PutMapping("active/{institute_id}/{transaction_type_id}")
	public Map<String,Object> activeTransactionTypeMaster(@PathVariable int transaction_type_id,@PathVariable String institute_id)
	{
		TransactionTypeMaster transaction_type=transactiontypemasterService.getById(transaction_type_id, institute_id);
		
		if(transaction_type!=null)
		{
			transaction_type.setStatus(1);
			TransactionTypeMaster activetransaction=transactiontypemasterrepo.save(transaction_type);
			return activetransaction!=null?JsonResponses.generateResponse1(true, activetransaction, "Trasaction  Activated"):
				JsonResponses.generateResponse1(false, activetransaction, "Not Activated");
				
		}
		
			return JsonResponses.generateResponse1(false, transaction_type_id, "Transaction Type Not Found for this Id "+transaction_type_id+"");
		
	}
	
	@DeleteMapping("delete/{institute_id}/{transaction_type_id}")
	public Map<String,Object> deactiveTransactionTypeMaster(@PathVariable int transaction_type_id,@PathVariable String institute_id)
	{
		TransactionTypeMaster transaction_type=transactiontypemasterService.getById(transaction_type_id, institute_id);
		
		if(transaction_type!=null)
		{
			transaction_type.setStatus(0);
			TransactionTypeMaster deactivetransaction=transactiontypemasterrepo.save(transaction_type);
			return deactivetransaction!=null?JsonResponses.generateResponse1(true, deactivetransaction, "Trasaction  Activated"):
				JsonResponses.generateResponse1(false, deactivetransaction, "Not Activated");
				
		}
		
			return JsonResponses.generateResponse1(false, transaction_type_id, "Transaction Type Not Found for this Id "+transaction_type_id+"");
		
	}
}
