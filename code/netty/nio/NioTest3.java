package com.school.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {
    public static void main(String[] args) throws  Exception{
        FileInputStream fileInputStream = new FileInputStream("NioTest1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel channeIn = fileInputStream.getChannel();
        FileChannel channeOut = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while(true){
            int read = channeIn.read(byteBuffer);
            System.out.println(byteBuffer);
            if(-1==read){
                break;
            }
            byteBuffer.flip();
            System.out.println(byteBuffer);
            channeOut.write(byteBuffer);
            byteBuffer.flip();
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
