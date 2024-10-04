package io.github.xiefrish2021.ncehIO.commons.encoder;

import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import io.github.xiefrish2021.ncehIO.handler.IEncoder;
import io.github.xiefrish2021.ncehIO.session.Session;

@SuppressWarnings("all")
public abstract class MessageEncoder<I> implements IEncoder {
    public abstract void encode0(Session session, I in, ByteBuffer out);

    @Override
    @SuppressWarnings("unchecked")
    public void encode(Session session, Object in, ByteBuffer out) {
        this.encode0(session, (I) in, out);
    }
}
