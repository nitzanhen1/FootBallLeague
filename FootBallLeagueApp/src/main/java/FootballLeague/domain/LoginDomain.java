package FootballLeague.domain;


import FootballLeague.entity.SubscriberEntity;
import FootballLeague.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

@Service
public class LoginDomain {

    @Autowired
    SubscriberRepository subscriberRepository;
    //params- username &password

    //subscriber repo- find by userName and password
    //if subscriber not found- send error msg =>TODO: change UC to end operation
    //else change loggedIn from false to true
    //repo- save

    public boolean login(String userName,String password){
        if(userName==null || password==null) {
            //invalid params
            throw new NullPointerException("invalid params");
        }

        SubscriberEntity subscriberEntity= subscriberRepository.getOneByUserName(userName);
        if(subscriberEntity == null){
            //subscriber doesn't exist in the DB
            throw new NoSuchElementException("subscriber doesn't exist in the DB");
        }

        if(!subscriberEntity.getPassword().equals(password)){
            //incorrect password entered
            throw new InputMismatchException("incorrect password entered");
        }
        if(!subscriberEntity.isLoggedIn()) {
            //subscriber not logged in -> login
            subscriberEntity.setLoggedIn(true);
            subscriberRepository.save(subscriberEntity);
            return true;
        }
        //subscriber already logged in
        return false;
    }
}
