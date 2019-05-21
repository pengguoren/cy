package com.school.netty.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest8 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest4.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        FileLock fileLock = fileChannel.lock(3, 6, true);
        System.out.println(fileLock.isValid());
        System.out.println(fileLock.isShared());
        fileLock.release();
        randomAccessFile.close();
    }
}
