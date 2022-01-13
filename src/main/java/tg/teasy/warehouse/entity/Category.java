package tg.teasy.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tg.teasy.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Category extends AbsEntity {
    @ManyToOne
    private  Category parentCategory;//null,1,1,1
}
