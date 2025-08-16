package dto.api.houses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class House {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("floorCount")
    @Expose
    private Integer floorCount;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("parkingPlaces")
    @Expose
    private List<ParkingPlace> parkingPlaces;

    @SerializedName("lodgers")
    @Expose
    private List<Lodger> lodgers;
}