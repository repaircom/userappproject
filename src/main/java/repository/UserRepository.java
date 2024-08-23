package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by their phone number
    Optional<User> findByPhoneNumber(String phoneNumber);

    // You can add more custom queries if needed
}
