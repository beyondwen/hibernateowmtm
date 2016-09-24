package com.wenhao.hibernateowmtm.dao;

import com.wenhao.hibernateowmtm.domain.Role;
import com.wenhao.hibernateowmtm.domain.User;
import com.wenhao.hibernateowmtm.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2016/09/24.
 */
public class DomainDaoTest {
    @Test
    public void updateRole() throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Role role = session.get(Role.class, 3L);
        Role role1 = session.get(Role.class, 5L);
        User user = session.get(User.class, 1L);
        user.getRoles().remove(role);
        user.getRoles().add(role1);
        transaction.commit();
        session.close();
    }

    @Test
    public void deleteRole() throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Role role = session.get(Role.class, 3L);
        User user = session.get(User.class, 1L);
        user.getRoles().remove(role);
        transaction.commit();
        session.close();
    }

    @Test
    public void deleteUser1Role3() throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.get(User.class, 1L);
        user.getRoles().clear();
        transaction.commit();
        session.close();
    }

    @Test
    public void deleteUser1() throws Exception {
        Session session = HibernateUtils.getSession();
        User user = session.get(User.class, 1L);
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Test
    public void get() throws Exception {
        Session session = HibernateUtils.getSession();
        User user = session.get(User.class, 3L);
        System.out.println(user.getRoles());
    }

    @Before
    public void save() throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user1 = new User("wenhao", "123456");
        User user2 = new User("xxxxxx", "123456");
        session.save(user1);
        session.save(user2);
        Role role1 = new Role("cccc");
        Role role2 = new Role("cccc");
        Role role3 = new Role("cccc");
        session.save(role1);
        session.save(role2);
        session.save(role3);
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user2.getRoles().add(role1);
        user2.getRoles().add(role2);
        user2.getRoles().add(role3);
        transaction.commit();
        session.close();
    }


}