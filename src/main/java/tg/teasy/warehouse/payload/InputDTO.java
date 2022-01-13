package tg.teasy.warehouse.payload;

import lombok.Data;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Supplier;
import tg.teasy.warehouse.entity.Warehouse;

import java.sql.Timestamp;

@Data
public class InputDTO {

    private Integer id;
    private Timestamp date; //etibor berish kk bovotti 2 ta kutubhonadan osa bolarkjan
    private Integer warehouse_id;
    private Integer supplier_id;
    private Integer currency_id;
    private String factureNumber;
    private String code;

}
