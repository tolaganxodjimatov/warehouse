package tg.teasy.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String message="Defult message";
    private boolean success;
    private Object object;

//    public Result(String message, boolean success) {
//        this.message = message;
//        this.success = success;
//    }
}
