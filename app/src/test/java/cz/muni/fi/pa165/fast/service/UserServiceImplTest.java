package cz.muni.fi.pa165.fast.service;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import cz.muni.fi.pa165.fast.convert.UserConvert;
import cz.muni.fi.pa165.fast.convert.impl.UserConvertImpl;
import cz.muni.fi.pa165.fast.dao.UserDAO;
import cz.muni.fi.pa165.fast.dto.UserDTO;
import cz.muni.fi.pa165.fast.model.User;
import cz.muni.fi.pa165.fast.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    @Mock
    private UserDAO userDaoMock;
    private UserConvert userConvert;
    private UserService service;
    
    public UserServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        JavaEEGloss convertGloss = new JavaEEGloss();
        userConvert = convertGloss.make(UserConvertImpl.class);
        JavaEEGloss serviceGloss = new JavaEEGloss();
        serviceGloss.addEJB(userDaoMock);
        serviceGloss.addEJB(userConvert);
        service = serviceGloss.make(UserServiceImpl.class);
    }
    
    @Test
    public void createUser() {
        UserDTO dto = new UserDTO();
        dto.setLogin("login");
        dto.setId(5l);
        User entity = new User();
        entity.setLogin("login");
        entity.setId(5l);

        service.create(dto);

        verify(userDaoMock).create(entity);
        verifyNoMoreInteractions(userDaoMock);
    }

    @Test
    public void createUserInvalid() {
        doThrow(new IllegalArgumentException()).when(userDaoMock).create(null);
        try {
            service.create(null);
            fail();
        } catch (Exception ex) {
            //OK
        }
    }
    
    @Test
    public void deleteUser() {
        UserDTO dto = new UserDTO();
        dto.setLogin("login");
        User entity = new User();
        entity.setLogin("login");

        service.delete(dto);

        verify(userDaoMock).delete(entity);
        verifyNoMoreInteractions(userDaoMock);
    }

    @Test
    public void deleteUserInvalid() {
        doThrow(new IllegalArgumentException()).when(userDaoMock).delete(null);
        try {
            service.delete(null);
            fail();
        } catch (RuntimeException ex) {
            //OK
        }
    }
    
    @Test
    public void findAll(){
        User u1 = new User();
        User u2 = new User();
        User u3 = new User();
        
        u1.setId(10l);
        u1.setLogin("u1");
        u1.setPassword("p1");
        u2.setId(20l);
        u2.setLogin("u2");
        u2.setPassword("p2");
        u3.setId(30l);
        u3.setLogin("u3");
        u3.setPassword("p3");
        
        List<User> userList = new ArrayList<User>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        
        when(userDaoMock.findAll()).thenReturn(userList);
        
        List<UserDTO> dtos = service.findAll();
        
        verify(userDaoMock).findAll();
        verifyNoMoreInteractions(userDaoMock);
    }
    
    @Test
    public void getByLogin(){
        User u1 = new User();
        u1.setId(10l);
        u1.setLogin("u1");
        u1.setPassword("p1");
        
        when(userDaoMock.findByLogin("u1")).thenReturn(u1);
        
        UserDTO u = service.getByLogin("u1");
        
        verify(userDaoMock).findByLogin("u1");
        verifyNoMoreInteractions(userDaoMock);
    }
}
