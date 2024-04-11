package spark.serverV2.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.serverV2.domain.ParkingInfo;

import spark.serverV2.service.ParkingService;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {
    private final ParkingService parkingService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ApiController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @GetMapping("/parking")
    public List<ParkingInfo> getAllParkingInfo() {
        return parkingService.getAllParkingInfo();
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<ParkingInfo> getParkingInfoById(@PathVariable Long id) {
        Optional<ParkingInfo> parkingInfoOptional = parkingService.getParkingInfoById(id);

        return parkingInfoOptional
                .map(parkingInfo -> ResponseEntity.ok().body(parkingInfo))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/parking")
    public ResponseEntity<String> addParkingInfo(@RequestParam Integer currentCar,
                                                 @RequestParam Integer emptySpace,
                                                 @RequestParam String parkingName,
                                                 @RequestParam Integer totalSpace,
                                                 @RequestParam MultipartFile image){
        try{
            byte[] img = image.getBytes();

            ParkingInfo parkingInfo = new ParkingInfo();
            parkingInfo.setCurrentCar(currentCar);
            parkingInfo.setEmptySpace(emptySpace);
            parkingInfo.setParkingName(parkingName);
            parkingInfo.setTotalSpace(totalSpace);
            parkingInfo.setImage(img);

            parkingService.saveParkingInfo(parkingInfo);
            return ResponseEntity.ok("Data saved successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the data and image.");
        }
    }
    @PatchMapping("/parking/{id}")
    public ResponseEntity<String> updateParkingInfo(
            @PathVariable Long id,
            @RequestParam(required = false) Integer currentCar,
            @RequestParam(required = false) Integer emptySpace,
            @RequestParam(required = false) String parkingName,
            @RequestParam(required = false) Integer totalSpace,
            @RequestPart(required = false) MultipartFile image
    ){
        try{
            Optional<ParkingInfo> existingDataOptional = parkingService.getParkingInfoById(id);
            if (existingDataOptional.isPresent()){
                ParkingInfo existingDate = existingDataOptional.get();
                if (image != null){
                    byte[] imageData = image.getBytes();
                    existingDate.setImage(imageData);
                }
                if (currentCar != null){
                    existingDate.setCurrentCar(currentCar);
                }
                if (emptySpace != null){
                    existingDate.setEmptySpace(emptySpace);
                }
                if (parkingName != null){
                    existingDate.setParkingName(parkingName);
                }
                if (totalSpace != null){
                    existingDate.setTotalSpace(totalSpace);
                }

                parkingService.saveParkingInfo(existingDate);
                return ResponseEntity.ok("Data updated successfully.");
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found for Id"+ id);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the data.");
        }
    }

    @GetMapping("/parking/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        parkingService.deleteParkingInfo(id);
        return ResponseEntity.ok("Data delete successfully.");
    }


    @GetMapping("/parking/deleteAll/")
    public ResponseEntity<String> deleteAll(){
        parkingService.deleteAll();
        return ResponseEntity.ok("Data delete all successfully.");
    }
}
