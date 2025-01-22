package org.example.refactoring.ch01;

import org.example.refactoring.ch01.data.*;

import java.util.List;

public class StatementData {
    private Invoice invoice;
    private Plays plays;

    private StatementData(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
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
        return new PerformanceCalculator(performance, playFor(performance)).amountFor();
    }

    int getTotalAmount() throws Exception {
        int result = 0;
        for(Performance performances : invoice.getPerformances()) {
            result += amountFor(performances);
        }
        return result / 100;
    }

    int totalVolumeCredits() {
        int volumeCredit = 0;
        for(Performance performances : invoice.getPerformances()) {
            volumeCredit += volumeCreditFor(plays, performances);
        }
        return volumeCredit;
    }

    private int volumeCreditFor(Plays plays, Performance performances) {
        int result = 0;
        // 포인트를 적립한다
        result += Math.max(performances.getAudience() - 30, 0);

        // 희극 관객 5명마다 추가 포인트를 제공한다
        if(playFor(performances).getType().equals(PlayType.COMEDY)) {
            result += (performances.getAudience() / 5);
        }
        return result;
    }
}