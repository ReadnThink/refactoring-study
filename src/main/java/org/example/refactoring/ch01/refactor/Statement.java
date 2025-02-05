package org.example.refactoring.ch01.refactor;

import org.example.refactoring.ch01.data.Invoice;
import org.example.refactoring.ch01.data.Performance;
import org.example.refactoring.ch01.data.Plays;

public class Statement {
    public String statement(Invoice invoice, Plays plays) throws Exception {
        return renderPlanText(StatementData.createStatementData(invoice, plays), plays);
    }

    public String htmlStatement(Invoice invoice, Plays plays) throws Exception {
        return renderHtml(StatementData.createStatementData(invoice, plays), plays);
    }

    private String renderPlanText(StatementData statementData, Plays plays) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append(String.format("청구 내역 (고객명: %s)", statementData.getCustomer())).append("\n");
        for(Performance performances : statementData.getPerformances()) {
            result.append(String.format("%s: $%d %d석\n", statementData.playFor(performances).getName(), statementData.amountFor(performances) / 100, performances.getAudience()));
        }

        result.append(String.format("총액: $%d\n", statementData.getTotalAmount()));
        result.append(String.format("적립 포인트: %d점\n", statementData.totalVolumeCredits()));
        return result.toString();
    }

    private String renderHtml(StatementData statementData, Plays plays) throws Exception {
        StringBuilder result = new StringBuilder(String.format("<h1>청구 내역 (고객명: %s)</h1>\n", statementData.getCustomer()));
        result.append("<table>\n");
        result.append("<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>\n");
        for (Performance performances : statementData.getPerformances()) {
            result.append(String.format("<tr><td>%s</td><td>%d</td><td>%d</td></tr>\n",statementData.playFor(performances).getName(), performances.getAudience(), statementData.amountFor(performances) / 100));
        }
        result.append("</table>\n");

        result.append(String.format("<p>총액: <em>%d</em></p>\n", statementData.getTotalAmount()));
        result.append(String.format("<p>적립 포인트: <em>%d</em>점</p>", statementData.totalVolumeCredits()));
        return result.toString();
    }
}