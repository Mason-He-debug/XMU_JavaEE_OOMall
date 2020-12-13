package cn.edu.xmu.oomall.util.response;

import cn.edu.xmu.oomall.util.ResponseCode;

public class SimpleResponse extends Response {

    Integer errno;
    String errmsg;

    public SimpleResponse() {
        this(ResponseCode.OK);
    }

    public SimpleResponse(ResponseCode responseCode) {
        this.errno = responseCode.getCode();
        this.errmsg = responseCode.getMessage();
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
