package uz.pdp.appcodingbat_task2.template;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
