# NcehIO

<h4>NcehIO is a NIO network framework based on Apache Mina</h4>
<h4>And with reference to the Atty framework that the author wrote before, it was written. </h4>
![Apache License](https://img.shields.io/github/license/XieFrish2021/NcehIO?style=flat-square/)

**English** | **[中文](./README.md)**\
The basic parts of the framework:
- Nceh-buffer (frame buffer, which is the carrier of data transmission)
- Nceh-core (the main part of the framework, which is basically written in the interface implementation of Nceh-api)
- nceh-api (framework call part, which is called for development)

Extensions of the framework:
- Nceh-commons

## How to use.
```gradle
dependencies {
    implementation("io.github.xiefrish2021:nceh-all:LAST_VERSION")
}
```

```xml
<dependencies>
    <dependency>
        <groupId>io.github.xiefrish2021</groupId>
        <artifactId>nceh-all</artifactId>
        <version>LAST_VERSION</version>
    </dependency>
</dependencies>
```

## How to create a server.
```java
import io.github.xiefrish2021.ncehIO.server.Server;
import io.github.xiefrish2021.ncehIO.NcehIO;

public class Main {
    public static void main(String[] args) {
        Server server = NcehIO.createTCPServer();
        server.encoder(new StringMessageCodec());
        server.decoder(new StringMessageCodec());

        server.handler(new MessageHandler<String>() {
            @Override
            public void handleMessage(Session session, String data) {
                System.out.println(data);
            }
        });
        server.listen(12345);
        
        
    }
}
```
Based on the above code, you can create a server for the String output.

### how to test your server.
Windows cmd: Enter the following command
```bash
telnet 127.0.0.1 12345
```
After entering, enter any character in the black box of the terminal, and the server log will be entered below\
As for how to test other operating systems other than Windows, it can only be up to you:)
