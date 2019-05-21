package com.school.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("position:" + buffer.position());
        System.out.println("limit" + buffer.limit());
        System.out.println(buffer.mark() + "mark");
        for (int i = 0; i < 1; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        buffer.position(0);
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
