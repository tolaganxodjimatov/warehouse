package tg.teasy.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tg.teasy.warehouse.entity.Client;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Warehouse;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
public class OutputDTO {
    private Integer id;
    private Timestamp date;//lib sql dan oldim
//    @ManyToOne
    private Integer warehouse_id;
//    @ManyToOne
    private  Integer client_id;
//    @ManyToOne
    private Integer currency_id;
    private String factureNumber;
//    @Column(unique = true, nullable = false)
    private String code;

}
