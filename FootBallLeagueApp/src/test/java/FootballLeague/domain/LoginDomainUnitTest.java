package FootballLeague.domain;
import FootballLeague.entity.SubscriberEntity;
import FootballLeague.repository.SubscriberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;



@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginDomainUnitTest {
//this class is for unit tests for login

    @InjectMocks
    private LoginDomain loginDomain;

    @Mock
    private SubscriberRepository subscriberRepository;

    @Test
    public void successfulLoginExceptionTest() {
        //mocks
        String userName = "user10";
        String password = "password10";
        SubscriberEntity mockedSubscriberEntity = new SubscriberEntity(userName,password,false);
        when(subscriberRepository.getOneByUserName(userName)).thenReturn(mockedSubscriberEntity);

        //tests
        assertThatCode(() -> loginDomain.login(userName,password)).doesNotThrowAnyException();
        verify(subscriberRepository).save(mockedSubscriberEntity);
    }

    @Test
    public void successfulLoginReturnValueTest() {
        //mocks
        String userName = "user10";
        String password = "password10";
        SubscriberEntity subscriberEntity = new SubscriberEntity(userName,password,false);
        when(subscriberRepository.getOneByUserName(userName)).thenReturn(subscriberEntity);

        //tests
        assertTrue(loginDomain.login(userName,password));
        verify(subscriberRepository).save(subscriberEntity);
    }

    @Test
    public void loginNullPointerExceptionTest() {
        //mocks
        String userName = "user10";
        String password = null;
        SubscriberEntity subscriberEntity = new SubscriberEntity(userName,password,false);
        when(subscriberRepository.getOneByUserName(userName)).thenReturn(subscriberEntity);

        //tests
        assertThatCode(() -> loginDomain.login(userName,password)).isInstanceOf(NullPointerException.class);
        verify(subscriberRepository, never()).save(subscriberEntity);//save to the DB is never called
    }

    @Test
    public void loginWrongPasswordTest() {
        //mocks
        String userName = "user10";
        String password = "password10";
        String wrongPassword = "wordpass10";
        SubscriberEntity subscriberEntity = new SubscriberEntity(userName,password,false);
        when(subscriberRepository.getOneByUserName(userName)).thenReturn(subscriberEntity);

        //tests
        assertThatCode(() -> loginDomain.login(userName,wrongPassword)).isInstanceOf(InputMismatchException.class);
        verify(subscriberRepository, never()).save(subscriberEntity);//save to the DB is never called
    }

    @Test
    public void loginNoSubscriberInDBTest() {
        //mocks
        String userName = "user10";
        String password = "password10";
        SubscriberEntity subscriberEntity = new SubscriberEntity(userName,password,false);
        when(subscriberRepository.getOneByUserName(userName)).thenReturn(null);

        //tests
        assertThatCode(() -> loginDomain.login(userName,password)).isInstanceOf(NoSuchElementException.class);
        verify(subscriberRepository, never()).save(subscriberEntity);//save to the DB is never called
    }

    @Test
    public void SubscriberAlreadyLoggedInTest() {
        //mocks
        String userName = "user10";
        String password = "password10";
        SubscriberEntity subscriberEntity = new SubscriberEntity(userName,password,false);
        when(subscriberRepository.getOneByUserName(userName)).thenReturn(subscriberEntity);

        //tests
        assertThatCode(() -> loginDomain.login(userName,password)).doesNotThrowAnyException();
        assertFalse(loginDomain.login(userName,password));//user is no already logged in
        verify(subscriberRepository,times(1)).save(subscriberEntity);// save to the DB is only called once
    }
}