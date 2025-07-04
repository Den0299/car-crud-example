package com.example.car_crud_example.service;

import com.example.car_crud_example.entity.Car;
import com.example.car_crud_example.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car) {
        Car newCar = carRepository.save(car);

        return newCar;
    }

    public List<Car> getAllCars() {
        List<Car> cars = carRepository.findAll();

        return cars;
    }

    public Optional<Car> getCar(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);

        return optionalCar;
    }

    public Optional<Car> updateCar(Long carId, Car carDetails) {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isPresent()) {

            optionalCar.get().setType(carDetails.getType());

            Car updatedCar = carRepository.save(optionalCar.get());
            return Optional.of(updatedCar);
        }

        return Optional.empty();
    }

    public Optional<Car> deleteCar (Car car) {
        carRepository.delete(car);
        return Optional.of(car);
    }

    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}