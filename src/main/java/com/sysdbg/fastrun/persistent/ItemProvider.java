package com.sysdbg.fastrun.persistent;

import com.sysdbg.fastrun.exception.ItemProviderException;
import com.sysdbg.fastrun.item.Item;

import java.util.List;

/**
 * Created by crady on 8/8/2015.
 */
public interface ItemProvider {
    /**
     * Read all items from underlying persistence layer.
     * @return
     */
    List<Item> getAllItems() throws ItemProviderException;

    /**
     * Save or update the item to underlying persistence layer.
     * @param item
     */
    void saveItem(Item item) throws ItemProviderException;
}
