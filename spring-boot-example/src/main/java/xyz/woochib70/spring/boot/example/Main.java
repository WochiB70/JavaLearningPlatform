package xyz.woochib70.spring.boot.example;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        List<String> list = pickTwo("Good", "Fast", "Cheap", "123");
//        String[] two = pickTwo("Good", "Fast", "Cheap");
        EnumSet<DayOfWeek> monday = EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY);

        EnumMap<DayOfWeek, String> dayOfWeekStringEnumMap = new EnumMap<>(DayOfWeek.class);

    }

    static <T> T[] pickTwo(T a, T b, T c) {
        return switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                yield toArray(a, b);
            case 1:
                yield toArray(a, c);
            case 2:
                yield toArray(b, c);
            default:
                throw new AssertionError();
        };
    }

    static <T> List<T> pickTwo(T a, T b, T c, T d) {
        return switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0:
                yield toList(a, b);
            case 1:
                yield toList(a, c);
            case 2:
                yield toList(b, c);
            default:
                throw new AssertionError();
        };

    }

    public static <T> T[] toArray(T... arr) {
        return arr;
    }

    public static <T> List<T> toList(T... arr) {
        return List.of(arr);
    }
}
