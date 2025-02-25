package com.producer.controllers;

import com.producer.models.Investment;
import com.producer.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    @Autowired
    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @PostMapping
    public ResponseEntity<Investment> create(@RequestBody Investment investment) {
        Investment createdInvestment = this.investmentService.create(investment);

        return ResponseEntity.status(HttpStatus.OK).body(createdInvestment);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Investment investment = this.investmentService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(investment);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Investment> update(@PathVariable UUID id, @RequestBody Investment investment) {
        Investment updatedInvestment = this.investmentService.update(id, investment);

        return ResponseEntity.status(HttpStatus.OK).body(updatedInvestment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.investmentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
