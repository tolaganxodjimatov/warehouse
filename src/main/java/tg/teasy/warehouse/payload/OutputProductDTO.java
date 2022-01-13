package tg.teasy.warehouse.payload;

import lombok.Data;
import tg.teasy.warehouse.entity.Output;
import tg.teasy.warehouse.entity.Product;

import javax.persistence.*;

@Data
public class OutputProductDTO {

    private Integer id;
    //    @ManyToOne
    private Integer product_id;
    //    @Column(nullable = false)
    private Double amount;
    private Double price;
    //    @ManyToOne
    private Integer output_id;

}
