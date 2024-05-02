package com.guoba.tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SeededRandom {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        execute();
    }

    private static void execute() throws ExecutionException, InterruptedException {
        List<PowerResult> goals = new ArrayList<>();
        goals.add(new PowerResult(new HashSet<>(List.of(2, 14, 22, 24, 35, 36)), 6));
        goals.add(new PowerResult(new HashSet<>(List.of(2, 6, 13, 26, 28, 32)), 8));
        goals.add(new PowerResult(new HashSet<>(List.of(10, 11, 12, 23, 24, 25)), 10));
        goals.add(new PowerResult(new HashSet<>(List.of(2, 3, 11, 20, 25, 39)), 10));

        int threads = 1000;
        List<Future<PowerResult>> resultList;
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            BigDecimal max = new BigDecimal(Long.MAX_VALUE);
            BigDecimal min = new BigDecimal(Long.MIN_VALUE);
            BigDecimal total = max.subtract(min);
            BigDecimal step = total.divideToIntegralValue(BigDecimal.valueOf(threads));

            resultList = new ArrayList<>();
            for (long i = Long.MIN_VALUE; i < Long.MAX_VALUE; i += step.longValue()) {
                resultList.add(executorService.submit(new LottoThread(i, i, i + step.longValue(), goals)));
            }
        }
        List<PowerResult> suggestions = new ArrayList<>();
        for (Future<PowerResult> result : resultList) {
            PowerResult powerResult = result.get();
            if (powerResult != null) {
                suggestions.add(powerResult);
                System.out.println("Found " + powerResult);
            }
        }

    }
}

class PowerResult {
    Set<Integer> lotto;
    Integer pb;

    public PowerResult(Set<Integer> lotto, Integer pb) {
        this.lotto = lotto;
        this.pb = pb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerResult powerResult = (PowerResult) o;
        return lotto.equals(powerResult.lotto) && pb.equals(powerResult.pb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, pb);
    }

    @Override
    public String toString() {
        return lotto.toString() + ", " + pb;
    }
}

class LottoThread implements Callable<PowerResult> {
    private final long threadId;
    private final long start;
    private final long end;
    private final List<PowerResult> goals;

    LottoThread(long threadId, long start, long end, List<PowerResult> goals) {
        this.threadId = threadId;
        this.start = start;
        this.end = end;
        this.goals = goals;
    }

    @Override
    public PowerResult call() {
        System.out.println("["+threadId+"] Start...");
        long counter = 1;
        for (long i = start; i < end; i++) {
            Random r = new Random(i);
            boolean candidate = isCandidate(r);
            if (counter % 10000 == 0) {
                System.out.println("["+threadId+"] processed " + counter);
            }
            counter++;
            if (candidate) {
                System.out.println("["+threadId+"] End");
                System.out.printf("[%d]: %d : %b%n", threadId, i, candidate);
                return nextPowerResult(r);
            }
        }
        System.out.println("["+threadId+"] End");
        System.out.println("<NULL>");
        return null;
    }

    private boolean isCandidate(Random r) {
        for (PowerResult goal : goals) {
            PowerResult attempt = nextPowerResult(r);
            if (!attempt.equals(goal)) {
                return false;
            }
        }
        return true;
    }

    private PowerResult nextPowerResult(Random r) {
        Set<Integer> ns = new HashSet<>();
        while (ns.size() < 6) {
            ns.add(r.nextInt(1, 41));
        }
        int pb = r.nextInt(1, 11);
        return new PowerResult(ns, pb);
    }
}