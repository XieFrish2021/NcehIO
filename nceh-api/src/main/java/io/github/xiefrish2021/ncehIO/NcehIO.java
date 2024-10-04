package io.github.xiefrish2021.ncehIO;

import io.github.xiefrish2021.ncehIO.server.Server;

@SuppressWarnings("all")
public interface NcehIO {
    static Server createTCPServer() {
        try {
            Class<?> defaultTCP = Class.forName("io.github.xiefrish2021.ncehIO.core.DefaultTCPServer");

            Object instance = defaultTCP.getConstructor().newInstance();
            if (instance instanceof Server server) {
                return server;
            } else {
                throw new RuntimeException("TCPServer cannot be Server.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
