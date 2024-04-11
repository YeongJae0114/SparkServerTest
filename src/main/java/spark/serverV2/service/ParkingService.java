package spark.serverV2.service;

import spark.serverV2.domain.ParkingInfo;

import java.util.List;
import java.util.Optional;

public interface ParkingService {
   ParkingInfo saveParkingInfo(ParkingInfo parkingInfo);

   List<ParkingInfo> getAllParkingInfo();

   Optional<ParkingInfo>getParkingInfoById(Long id);

   void deleteParkingInfo(Long id);

   void deleteAll();

}
