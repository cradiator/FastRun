package com.sysdbg.fastrun;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by crady on 3/25/2015.
 */
public class Utils {
    private static final byte[] EMPTY_BYTE_ARRAY = {};
    private static final int BUFFER_SIZE = 128;

    public static byte[] InputStreamToByteArray(InputStream input) throws IOException {
        if (input == null)
            return EMPTY_BYTE_ARRAY;

        // read whole input to a ByteArrayOutputStream
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while (true) {
            int size = input.read(buffer);

            // the end of input
            if (size == -1) {
                break;
            }

            output.write(buffer, 0, size);
        }

        // get final byte array
        return output.toByteArray();
    }
    public static String InputStreamToString(InputStream input, Charset charset) throws IOException {
        return null;
    }
}
