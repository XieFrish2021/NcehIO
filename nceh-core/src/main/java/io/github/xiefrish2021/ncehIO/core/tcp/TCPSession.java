package io.github.xiefrish2021.ncehIO.core.tcp;

import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import io.github.xiefrish2021.ncehIO.core.DefaultTCPServer;
import io.github.xiefrish2021.ncehIO.handler.event.SessionEventType;
import io.github.xiefrish2021.ncehIO.options.NcehOptions;
import io.github.xiefrish2021.ncehIO.session.Session;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.net.SocketAddress;

public class TCPSession implements Session {
    private final IoSession session;
    private final DefaultTCPServer server;

    public TCPSession(IoSession session, DefaultTCPServer server) {
        this.session = session;
        this.server = server;
    }

    private IoSession getSession() {
        return this.session;
    }

    private DefaultTCPServer getServer() {
        return this.server;
    }

    @Override
    public SocketAddress remoteAddress() {
        return getSession().getRemoteAddress();
    }

    @Override
    public SocketAddress localAddress() {
        return getSession().getLocalAddress();
    }

    @Override
    public void setAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    @Override
    public Object getAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    @Override
    public void write(Object data) {
        SessionEventType type = SessionEventType.WRITE;
        type.setData(data);
        server.frameHandler.callSessionEvent(this, type);
        if (getServer().encoder == null) {
            getSession().write(data);
        } else {
            Integer write_buffer_size = this.server.options(NcehOptions.WRITE_BUFFER_SIZE);
            ByteBuffer buffer = ByteBuffer.allocate(write_buffer_size);
            getServer().encoder.encode(this, data, buffer);

            IoBuffer allocate = IoBuffer.allocate(write_buffer_size);
            allocate.put(buffer.array());
            allocate.flip();
            this.getSession().write(allocate);
        }
    }

    @Override
    public void disconnect() {
        this.getSession().closeNow();
    }

    @Override
    public boolean isActive() {
        return this.getSession().isActive();
    }

    @Override
    public boolean isClosing() {
        return this.getSession().isClosing();
    }

    @Override
    public String toString() {
        return "Session-" + getSession().getId();
    }
}
