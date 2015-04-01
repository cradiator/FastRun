package com.sysdbg.fastrun;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by crady on 3/25/2015.
 */
public class SettingsTest {
    private Settings mSettings;
    private InputStream mEmptyInputStream;

    @Before
    public void setUp() {
        mSettings = new Settings();
        mEmptyInputStream = new ByteArrayInputStream(new byte[0]);
    }

    @Test
    public void settingDefaultStatus() {
        List<Item> items = mSettings.getItems();
        assertNotNull(items);
        assertEquals(0, items.size());
    }

    @Test
    public void getItemsReturnValueIsImmutable() {
        List<Item> items = mSettings.getItems();

        // backup items in items_bak
        List<Item> items_bak = new ArrayList<Item>(items.size());
        items_bak.addAll(items);

        // add some items
        items.add(mock(Item.class));

        // mSettings.getItems() return value shouldn't be change
        assertEquals("getItems() return value is not immutable", items_bak, mSettings.getItems());
    }

    @Test
    public void loadWithNull() throws Exception {
        mSettings.load(null);

        List<Item> items = mSettings.getItems();
        assertEquals(0, items.size());
    }

    @Test
    public void loadWithEmptyStream() throws Exception {
        mSettings.load(mEmptyInputStream);

        List<Item> items = mSettings.getItems();
        assertEquals(0, items.size());
    }

    @Test
    public void loadWithEmptyJson() throws Exception {
        String EMPTY_JSON = "{}";
        mSettings.load(StringToUtf8InputStream(EMPTY_JSON));

        List<Item> items = mSettings.getItems();
        assertEquals(0, items.size());
    }

    @Test
    public void loadWithEmptyItemListJson() throws Exception {
        String EMPTY_LIST_JSON = "{\"NewProcessItems\" : []}";
        mSettings.load(StringToUtf8InputStream(EMPTY_LIST_JSON));

        List<Item> items = mSettings.getItems();
        assertEquals(0, items.size());
    }

//    @Test
//    public void loadWithItemListJson() throws Exception {
//        String JSON = "{\"NewProcessItems\" : [{\"Name\" : \"Foo\", \"Description\" : \"Bar\"}]}"
//    }

    private InputStream StringToUtf8InputStream(String s) throws UnsupportedEncodingException {
        byte[] data = s.getBytes("UTF-8");
        return new ByteArrayInputStream(data);
    }
}
