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

    /**
     * Read whole input out into a byte array.
     * @param input The input stream.
     * @return The byte array read from input. If input is null, return a zero-length array.
     * @throws IOException Some error happen when read input.
     */
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

    /**
     * Read whole input, and translate the bytes into string.
     * @param input The input stream to read. This parameter can be null, which means a zero-length stream.
     * @param charset The charset for the stream bytes. This parameter cannot be null.
     *                The invalid character would be translated into default char of charset.
     * @return The result string.
     * @throws IOException Some error happen when reading input.
     * @throws NullPointerException The charset is null.
     */
    public static String InputStreamToString(InputStream input, Charset charset)
            throws IOException, NullPointerException {
        if (charset == null) {
            throw new NullPointerException("charset is null");
        }

        byte[] data = InputStreamToByteArray(input);
        if (data.length == 0) {
            return "";
        }

        String result = new String(data, charset);
        return result;
    }
}
