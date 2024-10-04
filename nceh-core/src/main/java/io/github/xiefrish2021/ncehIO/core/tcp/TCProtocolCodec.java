package io.github.xiefrish2021.ncehIO.core.tcp;

import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;

public class TCProtocolCodec implements ProtocolDecoder, ProtocolCodecFactory {
    @Override
    public void decode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput out) {
        if (in.remaining() > 0) {
            byte[] data = new byte[in.limit()];
            in.get(data);

            out.write(ByteBuffer.wrap(data));
        }
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) {
    }

    @Override
    public void dispose(IoSession ioSession) {
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) {
        return null;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) {
        return this;
    }
}
