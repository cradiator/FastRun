package com.sysdbg.fastrun.item;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

/**
 * Created by xinzhao on 3/24/2015.
 */
public class NewProcessExecutorTest {
    private static final String[] NOT_EXIST_CMD = {"foobarnotexit", "aa"};

    private NewProcessExecutor mExecutor;
    private NewProcessItem mItem;

    @Before
    public void setUp() {
        mExecutor = new NewProcessExecutor();
        mItem = new NewProcessItem();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullItem() throws Exception {
        mExecutor.run(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNewProcessItem() throws Exception {
        mExecutor.run(mock(Item.class));
    }

    @Test(expected = IOException.class)
    public void notExistCmd() throws Exception {
        mItem.setCommandLine(NOT_EXIST_CMD);
        mExecutor.run(mItem);
    }

    /*
    @Test
    public void notepadCmd() throws Exception {
        String[] cmd = {"notepad.exe", "c:\\LibAntiPrtSc_ERROR.log"};
        mItem.setCommandLine(cmd);
        mExecutor.run(mItem);
    }

    @Test
    public void securityCmd() throws Exception {
        String[] cmd = {"regedit.exe"};
        mItem.setCommandLine(cmd);
        mExecutor.run(mItem);
    }
    */
}
