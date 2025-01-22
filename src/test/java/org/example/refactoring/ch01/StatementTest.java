package org.example.refactoring.ch01;

import org.example.refactoring.ch01.data.*;
import org.example.refactoring.ch01.refactor.Statement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StatementTest {

    @Test
    void statementTest() throws Exception {
        // given
        Map<String, Play> playMap = new HashMap<>();
        playMap.put("hamlet", new Play("Hamlet", PlayType.TRAGEDY));
        playMap.put("as-like", new Play("As You Like It", PlayType.COMEDY));
        playMap.put("othello", new Play("Othello", PlayType.TRAGEDY));
        Plays plays = new Plays(playMap);

        ArrayList<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));
        Invoice invoice = new Invoice("BigCo", performances);
        String expected = "청구 내역 (고객명: BigCo)\n" +
                "Hamlet: $650 55석\n" +
                "As You Like It: $580 35석\n" +
                "Othello: $500 40석\n" +
                "총액: $1730\n" +
                "적립 포인트: 47점\n";
        Statement statement = new Statement();

        // when
        String actual = statement.statement(invoice, plays);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void htmlTest() throws Exception {
        // given
        Map<String, Play> playMap = new HashMap<>();
        playMap.put("hamlet", new Play("Hamlet", PlayType.TRAGEDY));
        playMap.put("as-like", new Play("As You Like It", PlayType.COMEDY));
        playMap.put("othello", new Play("Othello", PlayType.TRAGEDY));
        Plays plays = new Plays(playMap);

        ArrayList<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 55));
        performances.add(new Performance("as-like", 35));
        performances.add(new Performance("othello", 40));
        Invoice invoice = new Invoice("BigCo", performances);
        String expected = """
                <h1>청구 내역 (고객명: BigCo)</h1>
                <table>
                <tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>
                <tr><td>Hamlet</td><td>55</td><td>650</td></tr>
                <tr><td>As You Like It</td><td>35</td><td>580</td></tr>
                <tr><td>Othello</td><td>40</td><td>500</td></tr>
                </table>
                <p>총액: <em>1730</em></p>
                <p>적립 포인트: <em>47</em>점</p>""";
        Statement statement = new Statement();

        // when
        String actual = statement.htmlStatement(invoice, plays);

        // then
        assertThat(actual).isEqualTo(expected);
    }

}