package org.example.refactoring.ch01;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}