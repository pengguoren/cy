package com.school.netty.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest7 {
    public static void main(String[] args) throws  Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest4.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,6,5);
        mappedByteBuffer.put(0, (byte)'k');
        mappedByteBuffer.put(4, (byte)'k');
        randomAccessFile.close();
    }
}
