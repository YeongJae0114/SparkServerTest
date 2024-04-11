package spark.serverV2.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spark.serverV2.domain.ParkingInfo;
import spark.serverV2.repository.ParkingInfoRepository;
import spark.serverV2.service.ParkingService;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingInfoRepository parkingInfoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ParkingServiceImpl(ParkingInfoRepository parkingInfoRepository){
        this.parkingInfoRepository = parkingInfoRepository;
    }

    // 트랜잭션으로 저장
    @Transactional
    @Override
    public ParkingInfo saveParkingInfo(ParkingInfo parkingInfo) {
        return parkingInfoRepository.save(parkingInfo);
    }

    // 모든 주차장 정보 조회
    @Override
    public List<ParkingInfo> getAllParkingInfo() {
        return parkingInfoRepository.findAll();
    }

    // ID로 주차장 정보 조회
    @Override
    public Optional<ParkingInfo> getParkingInfoById(Long id) {
        return parkingInfoRepository.findById(id);
    }

    @Override
    public void deleteParkingInfo(Long id) {
        parkingInfoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM ParkingInfo").executeUpdate();
    }
}
