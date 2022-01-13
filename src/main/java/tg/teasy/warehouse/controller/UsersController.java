package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Users;
import tg.teasy.warehouse.entity.Warehouse;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.payload.UsersDto;
import tg.teasy.warehouse.service.UsersService;
import tg.teasy.warehouse.service.WarehouseService;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
//    WarehouseService warehouseService;


    @PostMapping
    public Result addUsers(@RequestBody UsersDto usersDto) {
        Result result = usersService.addUsers(usersDto);
        return result;
    }

    @GetMapping("/usersID")
    public Result getUsersById(@PathVariable Integer usersID) {
        Result result = usersService.getUsersById(usersID);
        return result;
    }

    @PutMapping("/usersID")
    public Result editUsers(@PathVariable Integer usersID, @RequestBody UsersDto usersDto) {
        Result result = usersService.editUsers(usersID, usersDto);
        return result;
    }

    @DeleteMapping("/usersID")
    public Result deleteUsers(@PathVariable Integer usersID) {
        Result result = usersService.deleteUsers(usersID);
        return result;
    }

}
