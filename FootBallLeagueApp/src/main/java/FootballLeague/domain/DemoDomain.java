package FootballLeague.domain;

import FootballLeague.entity.UserEntity;
import FootballLeague.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoDomain {

    @Autowired
    private UserRepository userRepository;

    public DemoDomain() {
    }

    public UserEntity findUser(String id){
        return userRepository.findOneById(id);
    }
}
