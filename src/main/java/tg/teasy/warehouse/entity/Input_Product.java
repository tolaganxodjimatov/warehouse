package tg.teasy.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tg.teasy.warehouse.entity.template.AbsEntity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Input_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double amount;

    private Double price;

    private Date expireDate;

    @ManyToOne
    private Input input;
}
