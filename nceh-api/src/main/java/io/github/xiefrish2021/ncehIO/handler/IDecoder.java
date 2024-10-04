package io.github.xiefrish2021.ncehIO.handler;

import io.github.xiefrish2021.ncehIO.session.Session;

public interface IDecoder<O> {
    O decode(Session session, Object data);
}
