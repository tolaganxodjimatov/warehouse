package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Currency;
import tg.teasy.warehouse.entity.Users;
import tg.teasy.warehouse.entity.Warehouse;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.payload.UsersDto;
import tg.teasy.warehouse.repository.UsersRepository;
import tg.teasy.warehouse.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUsers(UsersDto usersDto) {
        boolean existsByPhoneNumber = usersRepository.existsByPhoneNumber(usersDto.getPhoneNumber());
        if (existsByPhoneNumber) return new Result("Bunday Users bor", false, new Users());

        Users users= new Users();
        users.setActive(usersDto.isActive());
        users.setCode(usersDto.getCode());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setPassword(usersDto.getPassword());
        users.setPhoneNumber(usersDto.getPhoneNumber());

        List<Warehouse> listWarehouse = warehouseRepository.findAllById(usersDto.getWarehouse_id());
        Set<Warehouse> warehouseSet=new HashSet<>(listWarehouse);
        users.setWarehouseSet(warehouseSet);

        Users saved = usersRepository.save(users);
        return new Result("Saqlandi Users", true, saved);
    }

    public Result getUsersById(Integer usersID) {
        Optional<Users> optionalUsers = usersRepository.findById(usersID);
        if (!optionalUsers.isPresent()) return new Result("Users note found", false, new Users());
        Users users = optionalUsers.get();
        return new Result("OK", true, users);
    }

    public Result editUsers(Integer usersID, UsersDto usersDto) {
        Optional<Users> optionalUsers = usersRepository.findById(usersID);
        if (!optionalUsers.isPresent()) return new Result("Bunday Users yoq", false, null);
        Users usersToEdit = optionalUsers.get();

        usersToEdit.setActive(usersDto.isActive());
        usersToEdit.setCode(usersDto.getCode());
        usersToEdit.setFirstName(usersDto.getFirstName());
        usersToEdit.setLastName(usersDto.getLastName());
        usersToEdit.setPassword(usersDto.getPassword());
        usersToEdit.setPhoneNumber(usersDto.getPhoneNumber());

        List<Warehouse> listWarehouse = warehouseRepository.findAllById(usersDto.getWarehouse_id());
        Set<Warehouse> warehouseSet=new HashSet<>(listWarehouse);
        usersToEdit.setWarehouseSet(warehouseSet);

        Users saved = usersRepository.save(usersToEdit);
        return new Result("Ozgartirildi Users", true, saved.getId());
    }

    public Result deleteUsers(Integer usersID) {
        Optional<Users> optionalUsers = usersRepository.findById(usersID);
        if (!optionalUsers.isPresent()) return new Result("Bunday Users yoq", false, null);
        usersRepository.deleteById(usersID);
        return new Result("Ochirildi Users", true, null);
    }


}
