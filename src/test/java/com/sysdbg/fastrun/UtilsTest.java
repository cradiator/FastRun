package com.sysdbg.fastrun;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by crady on 3/25/2015.
 */
public class UtilsTest {
    private static final String  UTF8 = "UTF-8";
    private static final String  HELLO_WORLD = "HelloWorld";
    private static final String  EMPTY_STRING = "";
    private static final Charset UTF8_CHARSET;

    private byte[] mEmptyBytes;
    private ByteArrayInputStream mEmptyInputStream;

    private byte[] mHelloWorldBytes;
    private ByteArrayInputStream mHelloWorldInputStream;

    private InputStream mExceptionStream;

    static {
        UTF8_CHARSET = Charset.forName(UTF8);
    }

    @Before
    public void setUp() throws Exception {
        mEmptyBytes = new byte[0];
        mEmptyInputStream = new ByteArrayInputStream(mEmptyBytes);

        mHelloWorldBytes = HELLO_WORLD.getBytes(UTF8_CHARSET);
        mHelloWorldInputStream = new ByteArrayInputStream(mHelloWorldBytes);

        mExceptionStream = mock(InputStream.class);
        IOException exception = new IOException();
        when(mExceptionStream.read()).thenThrow(exception);
        when(mExceptionStream.read(any(byte[].class))).thenThrow(exception);
        when(mExceptionStream.read(any(byte[].class), anyInt(), anyInt())).thenThrow(exception);
    }

    @Test
    public void emptyInputStreamToArray() throws Exception {
        byte[] result = Utils.InputStreamToByteArray(null);

        assertNotNull(result);
        assertEquals("InputStreamToByteArray(null) should return empty array", 0, result.length);
    }

    @Test
    public void normalStreamToArray() throws Exception {
        byte[] result = Utils.InputStreamToByteArray(mHelloWorldInputStream);
        assertArrayEquals(mHelloWorldBytes, result);
    }

    @Test
    public void emptyStreamToArray() throws Exception {
        byte[] result = Utils.InputStreamToByteArray(mEmptyInputStream);
        assertArrayEquals(mEmptyBytes, result);
    }

    @Test(expected = IOException.class)
    public void exceptionStreamToArray() throws Exception {
        Utils.InputStreamToByteArray(mExceptionStream);
    }

    @Test(expected = NullPointerException.class)
    public void nullCharsetStreamToString() throws Exception {
        Utils.InputStreamToString(mHelloWorldInputStream, null);
    }

    @Test
    public void nullInputStreamToString() throws Exception {
        String result = Utils.InputStreamToString(null, UTF8_CHARSET);
        assertEquals(EMPTY_STRING, result);
    }

    @Test
    public void emptyInputStreamToString() throws Exception {
        String result = Utils.InputStreamToString(mEmptyInputStream, UTF8_CHARSET);
        assertEquals(EMPTY_STRING, result);
    }

    @Test
    public void normalInputStreamToString() throws Exception {
        String result = Utils.InputStreamToString(mHelloWorldInputStream, UTF8_CHARSET);
        assertEquals(HELLO_WORLD, result);
    }

    @Test(expected = IOException.class)
    public void exceptionInputStreamToString() throws Exception {
        Utils.InputStreamToString(mExceptionStream, UTF8_CHARSET);
    }
}
