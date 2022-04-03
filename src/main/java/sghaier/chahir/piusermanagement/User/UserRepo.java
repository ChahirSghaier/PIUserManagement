package sghaier.chahir.piusermanagement.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);



    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);


    @Query
    List<User> findByfirstNameContains(String firstName);



    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.password = ?1 WHERE a.email = ?2")
    void resetPassword(String password,String email);


    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.locked = TRUE WHERE a.email = ?1")
    int lockedAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.locked = FALSE WHERE a.email = ?1")
    int unlockedAppUser(String email);



    List<User> findByFirstNameContains(String firstName);

    List<User> findByFirstNameOrLastNameContains(String firstName, String lastName);

    List<User> findByLocked(Boolean locked);


    @Query("SELECT c.age, COUNT(c.age) FROM User AS c GROUP BY c.age ORDER BY c.age DESC")
    List<Object[]> countTotalUsersByYear();

}
