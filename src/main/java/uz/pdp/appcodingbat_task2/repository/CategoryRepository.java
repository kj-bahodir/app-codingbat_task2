package uz.pdp.appcodingbat_task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat_task2.entity.Category;
import uz.pdp.appcodingbat_task2.entity.Task;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
