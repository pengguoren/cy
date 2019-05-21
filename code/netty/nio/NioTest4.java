package com.school.netty.nio;

import java.nio.ByteBuffer;

public class NioTest4 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }
        byteBuffer.position(2);
        byteBuffer.limit(5);
        ByteBuffer sliceBuffer = byteBuffer.slice();
        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            sliceBuffer.put((byte) (b*2));
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }
    }
}
