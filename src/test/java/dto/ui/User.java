package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class User {
    @Builder.Default
    private int id = 0;
    private String firstname;
    private String lastname;
    @Builder.Default
    private int age = 0;
    private String sex;
    @Builder.Default
    private double money = 0;
    private String status;
}