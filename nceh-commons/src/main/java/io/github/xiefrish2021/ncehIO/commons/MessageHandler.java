package io.github.xiefrish2021.ncehIO.commons;

import io.github.xiefrish2021.ncehIO.handler.IHandler;
import io.github.xiefrish2021.ncehIO.session.Session;

@SuppressWarnings("all")
public abstract class MessageHandler<I> implements IHandler {
    public abstract void handleMessage(Session session, I data);

    @Override
    @SuppressWarnings("unchecked")
    public void handle(Session session, Object data) {
        this.handleMessage(session, (I) data);
    }
}
