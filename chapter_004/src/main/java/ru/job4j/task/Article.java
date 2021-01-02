package ru.job4j.task;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 2)
@Measurement(iterations = 3, time = 2)
public class Article {
    static String text = "Мой дядя самых честных правил, "
            + "Когда не в шутку занемог, "
            + "Он уважать себя заставил "
            + "И лучше выдумать не мог. "
            + "Его пример другим наука; "
            + "Но, боже мой, какая скука "
            + "С больным сидеть и день и ночь, "
            + "Не отходя ни шагу прочь! "
            + "Какое низкое коварство "
            + "Полуживого забавлять, "
            + "Ему подушки поправлять, "
            + "Печально подносить лекарство, "
            + "Вздыхать и думать про себя: "
            + "Когда же черт возьмет тебя!";
    static String check = "Мой дядя мог думать про тебя и день и ночь";

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Article.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public static boolean generateByv1() {
        var source = Arrays.stream(text.replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").split(" ")).collect(Collectors.groupingBy(v -> v));
        var test = Arrays.stream(check.replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").split(" ")).collect(Collectors.groupingBy(v -> v));

        boolean can = true;
        for (String key : test.keySet()) {
            if (!source.containsKey(key)
                    || source.get(key).size() < test.get(key).size()) {
                can = false;
                break;
            }
        }
        return can;
    }

    @Benchmark
    public static boolean generateByv2() {
        String[] source = text.replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").split(" ");
        String[] test = check.replaceAll("[^a-zA-Zа-яёА-ЯЁ ]", "").split(" ");

        List<String> testList = new ArrayList<>(Arrays.asList(test));
        testList.removeAll(Arrays.asList(source));

        return testList.size() == 0;
    }
}