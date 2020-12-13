package cn.edu.xmu.oomall.model.enumeration;

public enum AdvertisementStatus {

    UNAUDITED(1,"待审核"),
    ONSHELVED(4,"已上架"),
    OFFSHELVED(3,"已下架");

    Integer code;
    String name;

    private AdvertisementStatus(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public static AdvertisementStatus parseCode(Integer code){
        for(AdvertisementStatus enumValue:AdvertisementStatus.class.getEnumConstants()){
            if(enumValue.getCode().equals(code)){
                return enumValue;
            }
        }
        return null;
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

}
