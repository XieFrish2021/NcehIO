package io.github.xiefrish2021.ncehIO.options;

@SuppressWarnings("all")
public class NcehOption<V> {
    V value;

    public NcehOption(V value) {
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }
}
