package tg.teasy.warehouse.payload;

import lombok.Data;
import tg.teasy.warehouse.entity.Input;
import tg.teasy.warehouse.entity.Product;

import javax.persistence.ManyToOne;
import java.sql.Date;


@Data
public class Input_ProductDTO {
    private Integer id;

    private Integer product_id;
    private Double amount;
    private Double price;
    private Date expireDate;

    private Integer input_id;
}
