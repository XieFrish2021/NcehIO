package io.github.xiefrish2021.ncehIO.server;

import io.github.xiefrish2021.ncehIO.handler.IDecoder;
import io.github.xiefrish2021.ncehIO.handler.IHandler;
import io.github.xiefrish2021.ncehIO.handler.IEncoder;
import io.github.xiefrish2021.ncehIO.options.NcehOption;

import java.net.InetSocketAddress;

/**
 * @ClassName Server.java
 * @Description  服务器的API接口类
 * @Author Frish2021
 * @Time 2024/10/3
 */
@SuppressWarnings("all")
public interface Server {
    void listen(InetSocketAddress address);

    default void listen(String host, int port) {
        this.listen(new InetSocketAddress(host, port));
    }

    default void listen(int port, String host) {
        this.listen(host, port);
    }

    default void listen(String host) {
        this.listen(new InetSocketAddress(host, 0));
    }

    default void listen(int port) {
        this.listen(new InetSocketAddress(port));
    }

    void shutdown();

    void handler(IHandler handler);

    void decoder(IDecoder<?> handler);

    void encoder(IEncoder handler);

    <V> void options(NcehOption<V> option, V value);

    <V> V options(NcehOption<V> option);
}
