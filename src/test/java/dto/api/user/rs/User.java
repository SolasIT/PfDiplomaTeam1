package dto.api.user.rs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
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
    private Integer money;
}
