package tg.teasy.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tg.teasy.warehouse.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    @ManyToOne(optional = false)
    private Category category;

    @OneToOne
    private Attachment photo;

    private String code;//unikalniy kod dlya kajdogo tovara

    @ManyToOne(optional = false)
    private Measurement measurement;

}
