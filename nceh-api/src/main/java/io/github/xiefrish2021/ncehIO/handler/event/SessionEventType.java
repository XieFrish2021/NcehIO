package io.github.xiefrish2021.ncehIO.handler.event;

@SuppressWarnings("all")
public enum SessionEventType {
    CONNECTED,
    DISCONNECTED,
    ACTIVE,
    IDLE,
    WRITE;

    Object data;

    public SessionEventType setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }
}
