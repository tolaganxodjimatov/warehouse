package tg.teasy.warehouse.payload;

import lombok.Data;
import tg.teasy.warehouse.entity.Warehouse;

import java.util.Set;

@Data
public class UsersDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String code;

    private String password;

    private boolean active = true;

    private Set<Integer> warehouse_id;
}
