package demo.sharemer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private int code;
    private T t;
    private String message;

    public static <T> BaseResponse<T> success(T t) {
        return new BaseResponse<>(0, t, "success");
    }

    public static <T> BaseResponse<T> fail(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }
}
