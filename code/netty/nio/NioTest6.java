package com.school.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest6 {
    public static void main(String[] args) throws  Exception{
        FileInputStream fileInputStream = new FileInputStream("NioTest1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel channeIn = fileInputStream.getChannel();
        FileChannel channeOut = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        while(true){
            System.out.println(byteBuffer);
            byteBuffer.clear();
            byteBuffer.mark();
            channeIn.position(0);
            System.out.println(channeIn.position());
            int read = channeIn.read(byteBuffer);

            if(-1==read){
                break;
            }
            byteBuffer.flip();
            System.out.println(byteBuffer);
            channeOut.write(byteBuffer);
            byteBuffer.position(0);
            System.out.println(byteBuffer);
            byteBuffer.flip();
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
