package com.sysdbg.fastrun.persistent.impl;

import com.sysdbg.fastrun.exception.ItemProviderException;
import com.sysdbg.fastrun.item.Item;
import com.sysdbg.fastrun.persistent.ItemProvider;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by crady on 8/8/2015.
 */
@Component
public class HibernateItemProvider implements ItemProvider {
    @Autowired
    SessionFactory mSessionFactory;

    @Override
    public List<Item> getAllItems() throws ItemProviderException {
        Session session = null;
        Transaction tx = null;
        List<Item> result = null;

        try {
            session = mSessionFactory.openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Item.class);
            result = criteria.list();
        } catch (HibernateException e) {
            throw new ItemProviderException(e);
        } finally {
            if (tx != null) {
                tx.commit();
            }

            if (session != null) {
                session.close();
            }
        }

        return result;
    }

    @Override
    public void saveItem(Item item) throws ItemProviderException {
        if (item == null) {
            throw new ItemProviderException("item is null");
        }

        Session session = null;
        Transaction tx = null;

        try {
            session = mSessionFactory.openSession();
            tx = session.beginTransaction();

            session.save(item);
        } catch (HibernateException e) {
            throw new ItemProviderException(e);
        } finally {
            if (tx != null) {
                tx.commit();
            }

            if (session != null) {
                session.close();
            }
        }
    }
}
