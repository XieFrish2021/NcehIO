package io.github.xiefrish2021.ncehIO.commons.string;

import io.github.xiefrish2021.ncehIO.commons.MessageCodec;
import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import io.github.xiefrish2021.ncehIO.session.Session;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("all")
public class StringMessageCodec extends MessageCodec<ByteBuffer, String> {
    @Override
    public String decodeMessage(Session session, ByteBuffer input) {
        return new String(input.array(), StandardCharsets.UTF_8);
    }

    @Override
    public void encodeMessage(Session session, String in, ByteBuffer out) {
        out.writeBytes(in.getBytes(StandardCharsets.UTF_8));
    }
}
