package tg.teasy.warehouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tg.teasy.warehouse.entity.template.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp date;//lib sql dan oldim

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private  Client client;

    @ManyToOne
    private Currency currency;

    private String factureNumber;

    @Column(unique = true, nullable = false)
    private String code;

}
