package org.example.refactoring.ch01;

import java.util.List;

public class StatementData {
    private Invoice invoice;
    private Plays plays;

    public StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
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

    private Play playFor(Plays plays, Performance performances) {
        return plays.get(performances.getPlayId());
    }
}