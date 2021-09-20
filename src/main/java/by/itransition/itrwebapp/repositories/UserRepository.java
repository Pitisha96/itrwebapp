package by.itransition.itrwebapp.repositories;

import by.itransition.itrwebapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByUsername(String name);
    List<User> findAllByOrderByIdAsc();
    long countAllBySocial(String social);
    List<User> findAllByUsernameAndSocial(String username,String Social);
}
