package io.github.xiefrish2021.ncehIO.commons;

import io.github.xiefrish2021.ncehIO.commons.encoder.MessageEncoder;
import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import io.github.xiefrish2021.ncehIO.handler.IDecoder;
import io.github.xiefrish2021.ncehIO.session.Session;

@SuppressWarnings("all")
public abstract class MessageCodec<I, O> extends MessageEncoder<O> implements IDecoder<O> {
    @Override
    public void encode0(Session session, O in, ByteBuffer out) {
        this.encodeMessage(session, in, out);
    }

    @Override
    @SuppressWarnings("unchecked")
    public O decode(Session session, Object data) {
        return decodeMessage(session, (I) data);
    }

    public abstract O decodeMessage(Session session, I input);

    public abstract void encodeMessage(Session session, O in, ByteBuffer out);
}
