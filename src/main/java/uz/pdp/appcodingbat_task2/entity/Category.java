package uz.pdp.appcodingbat_task2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.appcodingbat_task2.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AbsEntity {

    @ManyToOne
    private ProgramLanguage programLanguage;
}
