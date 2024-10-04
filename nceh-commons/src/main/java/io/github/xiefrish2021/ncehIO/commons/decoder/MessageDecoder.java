package io.github.xiefrish2021.ncehIO.commons.decoder;

import io.github.xiefrish2021.ncehIO.handler.IDecoder;
import io.github.xiefrish2021.ncehIO.session.Session;

@SuppressWarnings("all")
public abstract class MessageDecoder<I, O> implements IDecoder<O> {
    public abstract O decode0(Session session, I input);

    @Override
    @SuppressWarnings("unchecked")
    public O decode(Session session, Object data) {
        return decode0(session, (I) data);
    }
}
