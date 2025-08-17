package dto.api.houses;

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
public class ParkingPlace {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("isWarm")
    @Expose
    private Boolean isWarm;

    @SerializedName("isCovered")
    @Expose
    private Boolean isCovered;

    @SerializedName("placesCount")
    @Expose
    private Integer placesCount;
}
