package com.example.car_crud_example.controller;

import com.example.car_crud_example.entity.Car;
import com.example.car_crud_example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.createCar(car);

        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/get-car/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable Long carId) {
        Optional<Car> car = carService.getCar(carId);

        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Long carId,
                                         @RequestBody Car carDetails) {

        Optional<Car> optionalCar = carService.updateCar(carId, carDetails);

        if (optionalCar.isPresent()) {
            return ResponseEntity.ok(optionalCar.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<Car> deleteCar(@RequestBody Car car) {
        Optional<Car> optionalCar = carService.deleteCar(car);

        if (optionalCar.isPresent()) {
            return ResponseEntity.ok().body(optionalCar.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllCars() {
        carService.deleteAllCars();

        return ResponseEntity.ok("All cars successfully deleted");
    }

}
