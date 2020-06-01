package com.chaos.jxls.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FileUtils {

    private final static ThreadLocal<byte[]> bytes_local = new ThreadLocal<>();
    private final static int INITIAL_CAPACITY = 1024 * 64;

    /**
     * 分配读取文件的数组大小，尽量减少每次读取文件时都new byte[]操作
     * 如果用来读取文件流的数组大小不超过 INITIAL_CAPACITY（64KB），那么可以第一次新增后，使用threadLocal缓存起来，后续再使用
     *
     * @param length
     * @return
     */
    private static byte[] allocateBytes(int length) {
        byte[] bytes = bytes_local.get();

        if (Objects.isNull(bytes)) {
            if (length <= INITIAL_CAPACITY) {
                bytes = new byte[INITIAL_CAPACITY];
                bytes_local.set(bytes);
            } else {
                bytes = new byte[length];
            }
        } else if (bytes.length < length) {
            bytes = new byte[length];
        }

        return bytes;
    }


    /**
     * 从文件流中读取内容到字节数组中
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream is) throws IOException {

        //每次读取64KB大小
        byte[] bytes = allocateBytes(INITIAL_CAPACITY);

        int offset = 0;
        for (; ; ) {
            int readCount = is.read(bytes, offset, bytes.length - offset);
            if (readCount == -1) {
                return bytes;
            }
            offset += readCount;
            //文件流还未读完，需要扩容
            if (offset == bytes.length) {
                byte[] newBytes = new byte[bytes.length * 3 / 2];
                System.arraycopy(bytes, 0, newBytes, 0, offset);
                bytes = newBytes;
            }
        }
    }
}
