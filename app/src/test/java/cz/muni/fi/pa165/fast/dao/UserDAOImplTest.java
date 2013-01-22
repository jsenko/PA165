package cz.muni.fi.pa165.fast.dao;

import cz.muni.fi.pa165.fast.model.Match;
import cz.muni.fi.pa165.fast.model.User;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDAOImplTest {
    
    private static Context context;
    private static UserDAO udaoi;
    private static UserFakeEntityManager em;
    
    public UserDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NamingException {
        context = EJBContainer.createEJBContainer().getContext();
        udaoi = (UserDAO) context.lookup("java:global/app/UserDAOImpl");
        em = (UserFakeEntityManager) context.lookup("java:global/app/UserFakeEntityManager");
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException {
        context.close();
    }
    
    @Test
    public void createUser() {
        User user = new User();

        user.setLogin("administrator");
        
        udaoi.create(user);

        assertNotNull(user.getId());

        em.remove(user);
    }

    @Test
    public void createUserInvalid() {
        User user = null;
        try {
            udaoi.create(user);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }
    
    @Test
    public void updateUser() {
        User user = new User();

        em.persist(user);

        Long userId = user.getId();

        user.setPassword("password");

        udaoi.update(user);

        User dbUser = em.find(User.class, userId);

        assertEquals("password", dbUser.getPassword());

        em.remove(user);
    }

    @Test
    public void updateUserInvalid() {
        User user = null;
        try {
            udaoi.update(user);
            fail();
        } catch (EJBException ex) {
            //OK
        }

        user = new User();
        user.setId(Long.MAX_VALUE);
        try {
            udaoi.update(user);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    @Test
    public void deleteUser() {
        User user = new User();

        em.persist(user);

        Long userId = user.getId();

        udaoi.delete(user);

        assertNull(em.find(User.class, userId));
    }

    @Test
    public void deleteUserInvalid() {
        User user = null;
        try {
            udaoi.delete(user);
            fail();
        } catch (EJBException ex) {
            //OK
        }

        user = new User();
        user.setId(Long.MAX_VALUE);
        try {
            udaoi.delete(user);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    @Test
    public void getById() {
        User user = new User();
        user.setPassword("password");

        em.persist(user);

        Long userId = user.getId();

        User dbUser = udaoi.getById(userId);

        assertEquals("password", dbUser.getPassword());

        em.remove(user);
    }

    @Test
    public void getByIdInvalid() {
        try {
            udaoi.getById(null);
            fail();
        } catch (EJBException ex) {
            //OK
        }

        try {
            udaoi.getById(Long.MAX_VALUE);
            fail();
        } catch (EJBException ex) {
            //OK
        }
    }

    @Test
    public void findAll() {
        User user1 = new User();
        user1.setPassword("pass1");
        User user2 = new User();
        user2.setPassword("pass2");

        em.persist(user1);
        em.persist(user2);

        Long user1Id = user1.getId();
        Long user2Id = user2.getId();

        Collection<User> users = udaoi.findAll();

        assertEquals(2, users.size());

        for (User user : users) {
            if (user1Id.equals(user.getId())) {
                assertEquals("pass1", user.getPassword());
            }

            if (user2Id.equals(user.getId())) {
                assertEquals("pass2", user.getPassword());
            }
        }

        em.remove(user1);
        em.remove(user2);
    }
    
    @Test
    public void findByLogin(){
        User user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("pass1");
        User user2 = new User();
        user2.setPassword("pass2");
        user2.setLogin("user2");

        em.persist(user1);
        em.persist(user2);
        
        Long user1Id = user1.getId();
        
        User userFromDb = udaoi.findByLogin("user1");
        
        assertEquals(user1Id, userFromDb.getId());
        assertEquals("pass1", userFromDb.getPassword());
        
        em.remove(user2);
        em.remove(user1);
    }
    
    @Test
    public void findByLoginInvalid(){
        try{
            User u = udaoi.findByLogin(null);
        }catch(EJBException ex){
            //OK
        }
        
        User u = udaoi.findByLogin("nonexistinglogin");
        assertNull(u);
    }
    
    @Stateless
    public static class UserFakeEntityManager {

        @PersistenceContext(name = "TestPU")
        private EntityManager em;

        public void remove(User u) {
            em.remove(em.find(User.class, u.getId()));
        }

        public void persist(Object o) {
            em.persist(o);
        }

        public User find(Class<User> c, Object o) {
            return em.find(c, o);
        }
    }
}
