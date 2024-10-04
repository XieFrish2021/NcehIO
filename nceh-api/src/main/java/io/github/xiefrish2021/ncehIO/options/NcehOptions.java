package io.github.xiefrish2021.ncehIO.options;

public class NcehOptions {
    public static NcehOption<Boolean> TCP_NO_DELAY = new NcehOption<>(false);

    public static NcehOption<Integer> WRITE_BUFFER_SIZE = new NcehOption<>(1024);

    public static NcehOption<Boolean> KEEP_ALIVE = new NcehOption<>(false);
}
