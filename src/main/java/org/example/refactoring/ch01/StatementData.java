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

    public Play playFor(Plays plays, Performance performances) {
        return plays.get(performances.getPlayId());
    }

    int amountFor(Performance performances, Plays plays) throws Exception {
        int result;
        switch (playFor(plays, performances).getType()) {
            case TRAGEDY :
                result = 40_000;
                if(performances.getAudience() > 30) {
                    result += 1_000 * (performances.getAudience() - 30);
                }
                break;
            case COMEDY :
                result = 30_000;
                if(performances.getAudience() > 30) {
                    result += 10_000 + 500 * (performances.getAudience() - 20);
                }
                result += 300 * performances.getAudience();
                break;
            default :
                throw new Exception(String.format("알 수 없는 장르: %s", playFor(plays, performances)));
        }
        return result;
    }

    int getTotalAmount() throws Exception {
        int result = 0;
        for(Performance performances : invoice.getPerformances()) {
            result += amountFor(performances, plays);
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
        if(playFor(plays, performances).getType().equals(PlayType.COMEDY)) {
            result += (performances.getAudience() / 5);
        }
        return result;
    }
}