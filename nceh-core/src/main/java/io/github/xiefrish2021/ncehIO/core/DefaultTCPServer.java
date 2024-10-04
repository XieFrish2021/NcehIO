package io.github.xiefrish2021.ncehIO.core;

import io.github.xiefrish2021.ncehIO.core.tcp.TCPSession;
import io.github.xiefrish2021.ncehIO.core.tcp.TCProtocolCodec;
import io.github.xiefrish2021.ncehIO.handler.IDecoder;
import io.github.xiefrish2021.ncehIO.handler.IHandler;
import io.github.xiefrish2021.ncehIO.handler.IEncoder;
import io.github.xiefrish2021.ncehIO.handler.event.SessionEventType;
import io.github.xiefrish2021.ncehIO.options.NcehOption;
import io.github.xiefrish2021.ncehIO.options.NcehOptions;
import io.github.xiefrish2021.ncehIO.server.Server;
import io.github.xiefrish2021.ncehIO.session.Session;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;

@SuppressWarnings("all")
public class DefaultTCPServer implements Server {
    private final SocketAcceptor acceptor = new NioSocketAcceptor();
    private boolean isRunning;
    private DefaultTCPServer server;
    public IHandler frameHandler;
    private IDecoder<?> decoder;
    public IEncoder encoder;
    private final IoHandlerAdapter handler = new IoHandlerAdapter() {
        private Session session;

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            this.session = new TCPSession(session, server);
            frameHandler.callSessionEvent(this.session, SessionEventType.CONNECTED);
            super.sessionCreated(session);
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            frameHandler.callSessionEvent(this.session, SessionEventType.DISCONNECTED);
            super.sessionClosed(session);
        }

        @Override
        public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
            frameHandler.callSessionEvent(this.session, SessionEventType.IDLE);
            super.sessionIdle(session, status);
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            if (decoder == null) {
                frameHandler.handle(this.session, message);
            } else {
                Object decode = decoder.decode(this.session, message);
                frameHandler.handle(this.session, decode);
            }
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            frameHandler.callSessionEvent(this.session, SessionEventType.ACTIVE);
            super.sessionOpened(session);
        }
    };

    public DefaultTCPServer() {
        this.isRunning = true;
        this.server = this;
        this.setupServerOptions();
        acceptor.getFilterChain().addLast(
                "codec",
                new ProtocolCodecFilter(
                        new TCProtocolCodec()
                )
        );
        acceptor.setHandler(handler);
    }

    @Override
    public void listen(InetSocketAddress address) {
        try {
            acceptor.bind(address);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown() {
        if (isRunning) {
            isRunning = false;
            Collection<IoSession> sessions =
                    acceptor.getManagedSessions().values();
            for (IoSession session : sessions) {
                session.closeNow();
            }

            acceptor.unbind();
            acceptor.dispose();
        }
    }

    @Override
    public void handler(IHandler handler) {
        this.frameHandler = handler;
    }

    @Override
    public void decoder(IDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void encoder(IEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public <V> void options(NcehOption<V> option, V value) {
        option.setValue(value);
        this.setupServerOptions();
    }

    @Override
    public <V> V options(NcehOption<V> option) {
        return option.getValue();
    }

    private void setupServerOptions() {
        SocketSessionConfig config = this.acceptor.getSessionConfig();

        config.setKeepAlive(options(NcehOptions.KEEP_ALIVE));
        config.setTcpNoDelay(options(NcehOptions.TCP_NO_DELAY));
    }
}
