package tg.teasy.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tg.teasy.warehouse.entity.Measurement;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) {
            Result result = new Result();
            result.setMessage("Bunday o'lchov birligi mavjud");
            result.setSuccess(false);
            //result.setObject();
            return result;
        }

        measurementRepository.save(measurement);
        Result result = new Result();
        result.setMessage("Muvaffaqiyatli saqlandi");
        result.setSuccess(true);
        //result.setObject();
//        return new Result("Muvaffaqiyatli saqlandi",true,null);
        return result;
    }

    public Result getMeasurementByIdService(Integer measurementId) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent()) return new Result("not found",false,new Measurement());
        Measurement measurement = optionalMeasurement.get();
        return new Result("ok",true,measurement);
    }

    public Result editMeasurementByIdService(Integer measurementId, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday ID lik o'lchov birligi mavjud emas", false, null);
        Measurement savedMeasurement = measurementRepository.save(measurement);
        return new Result("O'lchov birligi o'zgartirildi", true, savedMeasurement.getId());
    }

    public Result deleteMeasurementByIdService(Integer measurementId) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return new Result("Id lik o'lchov birligi mavjud emas", false, measurementId);
        measurementRepository.deleteById(measurementId);
        return new Result("o'lchov birligi o'chirildi ", false, measurementId);
    }
}
