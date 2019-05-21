package com.school.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest2.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "hello ni hao hehe".getBytes();
        for (int i = 0; i <message.length ; i++) {
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
