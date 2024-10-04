package io.github.xiefrish2021.ncehIO.handler;

import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import io.github.xiefrish2021.ncehIO.session.Session;

public interface IEncoder {
    void encode(Session session, Object in, ByteBuffer out);
}
