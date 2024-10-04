package test;

import io.github.xiefrish2021.ncehIO.buffer.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(256);

        buffer.writeInt(114514);
        buffer.writeInt(1919810);

        System.out.println(buffer.readInt());
        System.out.println(buffer.readInt());
        System.out.println(buffer);
    }
}
