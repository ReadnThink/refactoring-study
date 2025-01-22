package org.example.refactoring.ch01.calculator;

import org.example.refactoring.ch01.PerformanceCalculator;
import org.example.refactoring.ch01.data.Performance;
import org.example.refactoring.ch01.data.Play;

public class TragedyCalculator extends PerformanceCalculator {

    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }
}
