package org.example.refactoring.ch01;

public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder();
        result.append(String.format("청구 내역 (고객명: %s)", invoice.getCustomer())).append("\n");
        for(Performance performances : invoice.getPerformances()) {
            Play play = plays.get(performances.getPlayId());
            int thisAmount = amountFor(performances, play);
            // 포인트를 적립한다
            volumeCredits += Math.max(performances.getAudience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다
            if(play.getType().equals(PlayType.COMEDY)) {
                volumeCredits += (performances.getAudience() / 5);
            }
            result.append(String.format("%s: $%d %d석\n", play.getName(), thisAmount / 100, performances.getAudience()));
            totalAmount += thisAmount;
        }
        result.append(String.format("총액: $%d\n", totalAmount / 100));
        result.append(String.format("적립 포인트: %d점\n", volumeCredits));
        return result.toString();
    }

    private static int amountFor(Performance performances, Play play) throws Exception {
        int result;
        switch (play.getType()) {
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
                throw new Exception(String.format("알 수 없는 장르: %s", play.getType()));
        }
        return result;
    }
}