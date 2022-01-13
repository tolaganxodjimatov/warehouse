package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tg.teasy.warehouse.entity.Supplier;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier) {
        boolean isExists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (isExists) return new Result("bu nomerlik Supplier mavjud", false, null);
        Supplier save = supplierRepository.save(supplier);
        return new Result("Yangi supplier saqlandi", true, null);
    }

    public Result getSupplierById(Integer supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent()) return new Result("note found", false, new Supplier());
        return new Result("OK", true, optionalSupplier.get());
    }

    public Result editSupplierById(Integer supplierId, Supplier supplier) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent()) return new Result("Topilmadi supplier", false, null);

        boolean existsByPhoneNumber = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (existsByPhoneNumber) return new Result("TelNomer mavjud supplier", false, null);

        Supplier supplierTuUpd = optionalSupplier.get();
        supplierTuUpd.setName(supplier.getName());
        supplierTuUpd.setPhoneNumber(supplier.getPhoneNumber());
        Supplier saved = supplierRepository.save(supplierTuUpd);
        return new Result("Supplier saqlandi", true, saved.getId());
    }

    public Result deleteSupplierById(Integer supplierId) {
        Optional<Supplier> byId = supplierRepository.findById(supplierId);
        if (!byId.isPresent()) new Result("Bunday supplier mavjud emas", false, null);
        supplierRepository.deleteById(supplierId);
        return new Result("O'chirildi Supplier", true, null);
    }

}
