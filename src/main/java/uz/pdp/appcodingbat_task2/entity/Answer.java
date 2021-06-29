package uz.pdp.appcodingbat_task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean completed=false;

    private String code;

    @ManyToOne
    private Task task;

    @ManyToOne
    private User user;
}
