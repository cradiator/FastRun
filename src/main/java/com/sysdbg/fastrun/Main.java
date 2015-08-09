package com.sysdbg.fastrun;

import com.sysdbg.fastrun.item.Item;
import com.sysdbg.fastrun.item.ItemFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by crady on 8/8/2015.
 */
public class Main {
    public void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/sysdbg/fastrun/spring.xml");
        ItemFactory factory = (ItemFactory)context.getBean("processItemFactory");
        Item item = factory.createItem();
        Item item2 = factory.createItem();
        return;
    }
}
