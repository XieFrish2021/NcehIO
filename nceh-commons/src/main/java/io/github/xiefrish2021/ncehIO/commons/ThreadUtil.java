package io.github.xiefrish2021.ncehIO.commons;

@SuppressWarnings("all")
public class ThreadUtil {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
