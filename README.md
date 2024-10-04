# NcehIO

<h4>NcehIO是一个基于Apache Mina的一个NIO网络框架</h4>
<h4>并参考了作者之前写的Atty框架，而写出来的。</h4>
[Apache License](https://img.shields.io/github/license/XieFrish2021/NcehIO?style=flat-square/)

**[English](./README_EN.md)** | **中文**\
该框架的基础部分：
 - Nceh-buffer (框架缓冲，是数据的传输载体)
 - Nceh-core （框架主要部分，里面基本写的都是Nceh-api的接口实现）
 - Nceh-api（框架调用部分，里面都是开发所需要调用的）

该框架的拓展部分：
 - Nceh-commons

## 如何引入
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

## 如何创建服务器
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
根据上面的代码，就能创建出一个String输出的服务器了。

### 如何测试
Windows cmd输入以下指令
```bash
telnet 127.0.0.1 12345
```
回车后，在终端的黑框里面随便输入字符，服务器日志都会在下面输入\
至于除了Windows之外的其他操作系统如何测试，那只能看你造化了:)
