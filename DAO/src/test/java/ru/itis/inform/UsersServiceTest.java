package ru.itis.inform;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersServiceTest {
    private UsersDao userdao;
    private ArrayList<User> stubData;
    private UsersService users;


    @Before
    public void setUp(){
        userdao = mock(UsersDaoFileBasedImpl.class);

        User user1 = new User(21, "John");
        User user2 = new User(22, "Adam");
        User user3 = new User(23, "Jack");

        stubData = new ArrayList<User>();

        stubData.add(user1);
        stubData.add(user2);
        stubData.add(user3);

        users = new UsersService(userdao);
    }

    @Test
    public void testIsRegistered() throws Exception {
        when(userdao.findAll()).thenReturn(stubData);

        assertTrue(users.isRegistered("Jack"));
        assertFalse(users.isRegistered("Greg"));
    }
}