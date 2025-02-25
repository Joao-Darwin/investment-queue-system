package com.producer.services;

import com.producer.models.Investment;
import com.producer.models.User;
import com.producer.models.enums.InvestmentStatus;
import com.producer.repositores.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvestmentService {

    private final UserService userService;
    private final InvestmentRepository investmentRepository;

    @Autowired
    public InvestmentService(UserService userService, InvestmentRepository investmentRepository) {
        this.userService = userService;
        this.investmentRepository = investmentRepository;
    }

    public Investment create(Investment investment) {
        User user = this.userService.findById(investment.getUser().getId());

        double userBalance = user.getBalance();
        if (userBalance <= 0) throw new IllegalArgumentException("User's balance should be greater than zero. Balance: " + userBalance);
        double investmentValue = investment.getValue();
        if (investmentValue <= 0) throw new IllegalArgumentException("Investment value should be greater than zero. Investment value: " + investmentValue);
        if (userBalance < investmentValue) throw new IllegalArgumentException("User does not have enough balance for the investment. Balance: " + userBalance + ". Investment Value: " + investmentValue);

        user.setBalance(userBalance - investmentValue);

        this.userService.update(user.getId(), user);

        investment.setStatus(InvestmentStatus.PROCESSING);
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
