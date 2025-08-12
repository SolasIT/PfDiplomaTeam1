package dto.api.user.rq;

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
public class User {

    @SerializedName("id")
    @Expose
    @Builder.Default
    private Integer id = null;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("secondName")
    @Expose
    private String secondName;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("money")
    @Expose
    private Double money;
}
