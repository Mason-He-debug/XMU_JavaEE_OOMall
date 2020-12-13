package cn.edu.xmu.oomall.model.vo;

import cn.edu.xmu.oomall.model.enumeration.AdvertisementStatus;

public class AdvertisementStatusEnumReturnVo {

    Integer code;
    String name;

    public AdvertisementStatusEnumReturnVo() {
    }

    public AdvertisementStatusEnumReturnVo(AdvertisementStatus statusEnum){
        this.code = statusEnum.getCode();
        this.name = statusEnum.getName();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdvertisementStatusEnumReturnVo{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
