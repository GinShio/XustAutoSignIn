package api.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author bhj
 */
public class ResultUtil {

    private Integer code;

    private String message;

    private boolean isSuccess;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
