package org.example.refactoring.ch01;

import org.example.refactoring.ch01.calculator.PerformanceCalculatorFactory;
import org.example.refactoring.ch01.data.*;

import java.util.List;

public class StatementData {
    private Invoice invoice;
    private Plays plays;
    private PerformanceCalculatorFactory performanceCalculatorFactory;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
        this.performanceCalculatorFactory = new PerformanceCalculatorFactory();
    }
    public static StatementData createStatementData(Invoice invoice, Plays plays) {
        return new StatementData(invoice, plays);
    }

    public String getCustomer() {
        return invoice.getCustomer(); 
    }
    public Invoice getInvoice() {
        return invoice;
    }

    public List<Performance> getPerformances() {
        return invoice.getPerformances();
    }

    public Play playFor(Performance performances) {
        return plays.get(performances.getPlayId());
    }

    public int amountFor(Performance performance) throws Exception {
        return performanceCalculatorFactory.createPerformanceCalculator(performance, playFor(performance)).amountFor();
    }

    int getTotalAmount() throws Exception {
        int result = 0;
        for(Performance performances : invoice.getPerformances()) {
            result += amountFor(performances);
        }
        return result / 100;
    }

    int totalVolumeCredits() throws Exception {
        int volumeCredit = 0;
        for(Performance performances : invoice.getPerformances()) {
            volumeCredit += volumeCreditFor(performances);
        }
        return volumeCredit;
    }

    private int volumeCreditFor(Performance performance) throws Exception {
        return performanceCalculatorFactory.createPerformanceCalculator(performance, playFor(performance)).volumeCreditFor();
    }
}