package org.example.refactoring.ch01.refactor.calculator;

import org.example.refactoring.ch01.refactor.PerformanceCalculator;
import org.example.refactoring.ch01.data.Performance;
import org.example.refactoring.ch01.data.Play;

public class ComedyCalculator extends PerformanceCalculator {
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }
}

