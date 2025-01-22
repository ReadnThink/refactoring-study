package org.example.refactoring.ch01.refactor;

import org.example.refactoring.ch01.data.Performance;
import org.example.refactoring.ch01.data.Play;
import org.example.refactoring.ch01.data.PlayType;

public class PerformanceCalculator {
    private Performance performance;
    private Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int amountFor() throws Exception {
        int result;
        switch (play.getType()) {
            case TRAGEDY :
                result = 40_000;
                if(performance.getAudience() > 30) {
                    result += 1_000 * (performance.getAudience() - 30);
                }
                break;
            case COMEDY :
                result = 30_000;
                if(performance.getAudience() > 30) {
                    result += 10_000 + 500 * (performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default :
                throw new Exception(String.format("알 수 없는 장르: %s", play));
        }
        return result;
    }

    public int volumeCreditFor() {
        int result = 0;

        result += Math.max(performance.getAudience() - 30, 0);

        if (play.getType().equals(PlayType.COMEDY)) {
            result += Math.floor(performance.getAudience() / 5);
        }

        return result;
    }
}