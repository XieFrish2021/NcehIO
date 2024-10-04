package io.github.xiefrish2021.ncehIO.session;

import java.net.SocketAddress;

@SuppressWarnings("all")
public interface Session {
    SocketAddress remoteAddress();

    SocketAddress localAddress();

    void setAttribute(Object key, Object value);

    Object getAttribute(Object key);

    void write(Object data);

    void disconnect();

    boolean isActive();

    boolean isClosing();
}
