package uz.pdp.appcodingbat_task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat_task2.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean findByEmail(String email);
}
