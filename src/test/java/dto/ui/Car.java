package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Builder.Default
    private String engineType = null;
    @Builder.Default
    private String mark = null;
    @Builder.Default
    private String model = null;
    @Builder.Default
    private double price = 0.0;
    @Builder.Default
    private String status = null;
    @Builder.Default
    private Integer id = null;
}