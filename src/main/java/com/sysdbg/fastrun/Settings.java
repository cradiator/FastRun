package com.sysdbg.fastrun;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crady on 3/25/2015.
 */
public class Settings {
    private List<NewProcessItem> mNewProcessItems;

    public Settings() {
        mNewProcessItems = new ArrayList<NewProcessItem>();
    }

    List<Item> getItems() {
        ArrayList<Item> result = new ArrayList<Item>();
        result.addAll(mNewProcessItems);
        return result;
    }

    void load(InputStream savedSettings) throws IOException, IllegalArgumentException {

    }
}
