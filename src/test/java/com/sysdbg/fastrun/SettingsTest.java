package com.sysdbg.fastrun;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by crady on 3/25/2015.
 */
public class SettingsTest {
    private Settings mSettings;

    @Before
    public void setUp() {
        mSettings = new Settings();
    }

    @Test
    public void defaultSetting() {
        List<Item> items = mSettings.getItems();
        assertNotNull(items);
        assertEquals(0, items.size());
    }

    @Test
    public void immutableItemList() {
        List<Item> items = mSettings.getItems();

        // backup items in items_bak
        List<Item> items_bak = new ArrayList<Item>(items.size());
        items_bak.addAll(items);

        // add some items
        items.add(mock(Item.class));

        // mSettings.getItems() return value shouldn't be change
        assertEquals("getItems() return value is not immutable", items_bak, mSettings.getItems());
    }
}
