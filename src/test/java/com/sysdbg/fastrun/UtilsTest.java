package com.sysdbg.fastrun;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by crady on 3/25/2015.
 */
public class UtilsTest {
    @Test
    public void emptyInputStreamToArray() throws Exception {
        byte[] result = Utils.InputStreamToByteArray(null);

        assertNotNull(result);
        assertEquals("InputStreamToByteArray(null) should return empty array", 0, result.length);
    }

    @Test
    public void normalStreamToArray() throws Exception {
        byte[] input_array = {0x1, 0x2, 0x3, 0x4, 0x5};
        ByteArrayInputStream in = new ByteArrayInputStream(input_array);

        byte[] result = Utils.InputStreamToByteArray(in);
        assertArrayEquals(input_array, result);
    }

    @Test(expected = IOException.class)
    public void exceptionStreamToArray() throws Exception {
        InputStream is = mock(InputStream.class);
        IOException exception = new IOException();
        when(is.read()).thenThrow(exception);
        when(is.read(anyObject())).thenThrow(exception);
    }
}
