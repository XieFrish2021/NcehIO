package io.github.xiefrish2021.ncehIO.handler;

import io.github.xiefrish2021.ncehIO.handler.event.SessionEventType;
import io.github.xiefrish2021.ncehIO.session.Session;

@SuppressWarnings("all")
public interface IHandler {
    void handle(Session session, Object data);

    default void exceptionCaught(Session session, Throwable throwable) {
        throwable.printStackTrace(System.out);
    }

    default void callSessionEvent(Session session, SessionEventType type) {}
}
