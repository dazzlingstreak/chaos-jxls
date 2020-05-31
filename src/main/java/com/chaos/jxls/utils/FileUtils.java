package com.chaos.jxls.utils;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {


    public static byte[] readInputStream(InputStream is) throws IOException {

        //每次读取64KB大小，待优化
        byte[] bytes = new byte[1024 * 64];

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
