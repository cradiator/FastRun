package com.sysdbg.fastrun.item.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crady on 8/5/2015.
 */
public class ProcessItemImplTest {
    private final static String FOO_BAR = "FooBar";
    private ProcessItemImpl mItem;

    @Before
    public void setup() {
        mItem = new ProcessItemImpl();
    }

    @Test
    public void imagePath() {
        Assert.assertEquals(null, mItem.getImagePath());

        mItem.setImagePath(FOO_BAR);
        Assert.assertEquals(FOO_BAR, mItem.getImagePath());
    }

    @Test
    public void nullArguments() {
        Assert.assertEquals(null, mItem.getArguments());

        mItem.setArguments(null);
        Assert.assertEquals(null, mItem.getArguments());
    }

    @Test
    public void addArguments() {
        mItem.addArgument(FOO_BAR);

        List<String> args = mItem.getArguments();
        Assert.assertNotNull(args);
        Assert.assertEquals(1, args.size());
        Assert.assertEquals(FOO_BAR, args.get(0));
    }

    @Test
    public void setArguments() {
        List<String> src_args = new ArrayList<String>();
        src_args.add(FOO_BAR);

        mItem.setArguments(src_args);
        Assert.assertEquals(src_args, mItem.getArguments());
    }

    @Test
    public void immutableArguments() {
        List<String> src_args = new ArrayList<String>();
        src_args.add(FOO_BAR);
        mItem.setArguments(src_args);

        Assert.assertNotSame(src_args, mItem.getArguments());

        src_args.add("more..");
        Assert.assertNotEquals(src_args, mItem.getArguments());
    }
}
