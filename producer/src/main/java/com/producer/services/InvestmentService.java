package com.producer.services;

import com.producer.models.Investment;
import com.producer.models.User;
import com.producer.repositores.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public Investment create(Investment investment) {
        return this.investmentRepository.save(investment);
    }

    public Investment findById(UUID id) {
        return this.investmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Investment not found. Searched ID: " + id));
    }

    public Investment update(UUID id, Investment investment) {
        Investment investmentToUpdate = this.findById(id);

        investmentToUpdate.setMonths(investment.getMonths());
        investmentToUpdate.setUser(investment.getUser());
        investmentToUpdate.setValue(investment.getValue());
        investmentToUpdate.setStatus(investment.getStatus());

        return this.investmentRepository.save(investmentToUpdate);
    }

    public void delete(UUID id) {
        Investment investmentToDelete = this.findById(id);

        this.investmentRepository.delete(investmentToDelete);
    }
}
