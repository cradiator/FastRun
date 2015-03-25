package com.sysdbg.fastrun;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by xinzhao on 3/23/2015.
 */
public class NewProcessItemTest {
    private static final String NAME = "some name";
    private static final String DESCRIPTION = "some description";
    private static final UUID ID = UUID.fromString("76895313-839E-4E89-BAFC-B253BFF3173F");
    private static final String EMPTY_STRING = "";

    private static final String[] EMPTY_COMMANDLINE = {};
    private static final String[] ONE_ITEM_COMMANDLINE = {"cmdline"};
    private static final String[] NULL_ITEM_COMMANDLINE = {"cmdline", null};
    private static final String[] EMPTY_ITEM_COMMANDLINE = {"cmdline", ""};

    private NewProcessItem mItem;
    private Executor mExecutor;

    @Before
    public void setUp() {
        mExecutor = mock(Executor.class);

        mItem = new NewProcessItem();
        mItem.setExecutor(mExecutor);
    }

    @Test
    public void testDefaultConstructor() {
        NewProcessItem item = new NewProcessItem();
        assertNotNull(item.getId());
    }

    @Test
    public void testConstructWithNull() {
        NewProcessItem item = new NewProcessItem(null);
        assertNotNull(item.getId());
    }

    @Test
    public void testConstructWithUUID() {
        NewProcessItem item = new NewProcessItem(ID);
        assertEquals(ID, item.getId());
    }

    @Test
    public void testRandomUUID() {
        NewProcessItem item1 = new NewProcessItem();
        NewProcessItem item2 = new NewProcessItem();

        assertNotEquals(item1.getId(), item2.getId());
    }

    @Test
    public void testName() {
        assertEquals("default name should be empty string", EMPTY_STRING, mItem.getName());

        mItem.setName(NAME);
        assertEquals(NAME, mItem.getName());

        mItem.setName(null);
        assertEquals("set a null name should return empty string, not null", EMPTY_STRING, mItem.getName());
    }

    @Test
    public void testDescription() {
        assertEquals("default description should be empty string", EMPTY_STRING, mItem.getDescription());

        mItem.setDescription(DESCRIPTION);
        assertEquals(DESCRIPTION, mItem.getDescription());

        mItem.setDescription(null);
        assertEquals(EMPTY_STRING, mItem.getDescription());
    }

    @Test
    public void testCommandLine() {
        assertArrayEquals("default command lien should be empty string array",
                EMPTY_COMMANDLINE, mItem.getCommandLine());

        mItem.setCommandLine(ONE_ITEM_COMMANDLINE);
        assertArrayEquals(ONE_ITEM_COMMANDLINE, mItem.getCommandLine());

        mItem.setCommandLine(null);
        assertArrayEquals(EMPTY_COMMANDLINE, mItem.getCommandLine());
    }

    @Test
    public void immutableCommandLine() {
        String[] cmd_line  = {"foo"};
        String[] cmd_line2 = {"foo"};

        mItem.setCommandLine(cmd_line);
        cmd_line[0] = "bar";

        assertArrayEquals("command line should be immmutable", cmd_line2, mItem.getCommandLine());
    }

    @Test
    public void illegalCommandLine() {
        try {
            mItem.setCommandLine(NULL_ITEM_COMMANDLINE);
            fail("set command line containing null item should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }

        try {
            mItem.setCommandLine(EMPTY_ITEM_COMMANDLINE);
            fail("set command line containing empty string should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testExecutor() {
        Executor dummy = mock(Executor.class);
        mItem.setExecutor(dummy);

        assertEquals(dummy, mItem.getExecutor());
    }

    @Test
    public void testRun() throws Exception {
        mItem.run();
        verify(mExecutor).run(mItem);
    }

    @Test(expected = IllegalStateException.class)
    public void nullExecutor() throws Exception {
        mItem.setExecutor(null);
        mItem.run();
    }

    @Test(expected = IllegalStateException.class)
    public void illegalArgumentExecutor() throws Exception {
        doThrow(new IllegalArgumentException()).when(mExecutor).run(mItem);
        mItem.run();
    }

    @Test(expected = IOException.class)
    public void IOExceptionExecutor() throws Exception {
        doThrow(new IOException()).when(mExecutor).run(mItem);
        mItem.run();
    }

    @Test(expected = SecurityException.class)
    public void SecurityExceptionExecutor() throws Exception {
        doThrow(new SecurityException()).when(mExecutor).run(mItem);
        mItem.run();
    }
}
