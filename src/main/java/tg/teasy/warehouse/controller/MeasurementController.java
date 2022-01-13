package tg.teasy.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tg.teasy.warehouse.entity.Measurement;
import tg.teasy.warehouse.payload.Result;
import tg.teasy.warehouse.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @GetMapping("/{measurmentId}")
    public Result getMeasurementByIdController(@PathVariable Integer measurmentId){
        Result result = measurementService.getMeasurementByIdService(measurmentId);
        return result;
    }

    @PutMapping("/{measurmentId}")
    public Result editMeasurementByIdController(@PathVariable Integer measurementId,@RequestBody Measurement measurement){
        Result result = measurementService.editMeasurementByIdService(measurementId, measurement);
        return result;
    }

    @DeleteMapping("/{measurmentId}")
    public Result deleteMeasurementByIdController(@PathVariable Integer measurementId){
        Result result = measurementService.deleteMeasurementByIdService(measurementId);
        return result;
    }

}
