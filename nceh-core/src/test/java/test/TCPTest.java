package test;

import io.github.xiefrish2021.ncehIO.NcehIO;
import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;
import io.github.xiefrish2021.ncehIO.handler.IDecoder;
import io.github.xiefrish2021.ncehIO.handler.IHandler;
import io.github.xiefrish2021.ncehIO.handler.event.SessionEventType;
import io.github.xiefrish2021.ncehIO.server.Server;
import io.github.xiefrish2021.ncehIO.session.Session;

import java.nio.charset.StandardCharsets;

public class TCPTest {
    public static void main(String[] args) {
        Server server = NcehIO.createTCPServer();
        server.decoder((IDecoder<String>) (session, data) -> new String(((ByteBuffer) data).array(), StandardCharsets.UTF_8));

        server.handler(new IHandler() {
            @Override
            public void handle(Session session, Object data) {
                System.out.println(data);
                session.write(data);
            }

            @Override
            public void callSessionEvent(Session session, SessionEventType type) {
                if (type == SessionEventType.CONNECTED) {
                    System.out.println("Connected: " + session);
                }
            }
        });

        server.encoder((session, in, out) -> out.writeBytes(((String) in).getBytes(StandardCharsets.UTF_8)));

        server.listen(25565);
        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
    }
}
