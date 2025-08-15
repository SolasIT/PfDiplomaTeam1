package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor

public class Car {
    @SerializedName("id")
    @Builder.Default
    private Integer id = null;
    @SerializedName("engineType")
    @Expose
    private String engineType;
    @SerializedName("mark")
    @Expose
    private String mark;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("price")
    @Expose
    private Double price;
}