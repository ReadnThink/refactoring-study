package org.example.refactoring.ch01;

public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlanText(statementData, plays);
    }

    private String renderPlanText(StatementData statementData, Plays plays) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append(String.format("청구 내역 (고객명: %s)", statementData.getCustomer())).append("\n");
        for(Performance performances : statementData.getPerformances()) {
            result.append(String.format("%s: $%d %d석\n", playFor(plays, performances).getName(), amountFor(performances, plays) / 100, performances.getAudience()));
        }

        result.append(String.format("총액: $%d\n", getTotalAmount(statementData.getInvoice(), plays) / 100));
        result.append(String.format("적립 포인트: %d점\n", totalVolumeCredits(statementData.getInvoice(), plays)));
        return result.toString();
    }

    private int getTotalAmount(Invoice invoice, Plays plays) throws Exception {
        int result = 0;
        for(Performance performances : invoice.getPerformances()) {
            result += amountFor(performances, plays);
        }
        return result;
    }

    private int totalVolumeCredits(Invoice invoice, Plays plays) {
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

    private int amountFor(Performance performances, Plays plays) throws Exception {
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

    private Play playFor(Plays plays, Performance performances) {
        return plays.get(performances.getPlayId());
    }
}