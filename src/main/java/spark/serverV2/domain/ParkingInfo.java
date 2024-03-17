package spark.serverV2.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ParkingInfo {
    @Id // 엔티티 클래스의 필드를 테이블의 pk 칼럼에 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private Long id;
    private Integer currentCar;
    private Integer emptySpace;
    private String parkingName;
    private Integer totalSpace;

    @Lob
    private byte[] image;
    }