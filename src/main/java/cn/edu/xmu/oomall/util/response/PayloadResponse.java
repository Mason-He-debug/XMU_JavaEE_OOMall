package cn.edu.xmu.oomall.util.response;

import lombok.Getter;

import cn.edu.xmu.oomall.util.ResponseCode;

/**
 * 返回对象
 * @author Ming Qiu
 **/
@Getter
public class PayloadResponse<T> extends Response {

    ResponseCode errno = ResponseCode.OK;
    String errmsg = null;
    private T data = null;

    public PayloadResponse() {
        this.errmsg = "成功";
    }

    public PayloadResponse(T data) {
        this.data = data;
        this.errmsg = "成功";
    }

    public PayloadResponse(ResponseCode errorCode) {
        this.errno = errorCode;
    }

    public PayloadResponse(ResponseCode errorCode, String errorMessage) {
        this.errno = errorCode;
        this.errmsg = errorMessage;
    }

    @Override
    public String toString() {
        return "PayloadResponse{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}