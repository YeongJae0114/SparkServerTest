package spark.serverV2.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ParkingInfo {
    @Id // 엔티티 클래스의 필드를 테이블의 pk 칼럼에 매핑
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "parking_info_table_gen")
    @TableGenerator(name = "parking_info_table_gen", table = "sequence_table", pkColumnName = "seq_name", valueColumnName = "seq_value", pkColumnValue = "parking_info_seq", allocationSize = 1)

    private Long id;

    private Integer currentCar;
    private Integer emptySpace;
    private String parkingName;
    private Integer totalSpace;

    @Lob
    private byte[] image;
    }