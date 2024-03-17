package spark.serverV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import spark.serverV2.domain.ParkingInfo;

@Transactional
public interface ParkingInfoRepository extends JpaRepository<ParkingInfo, Long> {

}
