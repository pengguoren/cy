package com.school.netty.nio;

import java.nio.ByteBuffer;

public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i <9; i++) {
            byteBuffer.put((byte) i);
        }
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        readOnlyBuffer.flip();
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());


        }

       // readOnlyBuffer.put((byte)12);
    }
}
