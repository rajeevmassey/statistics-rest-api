package org.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.statistics.model.Transaction;
import org.statistics.service.TransactionService;

/**
 * REST API for consuming transaction requests
 *
 * @author rajeevmassey
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
    }
}
