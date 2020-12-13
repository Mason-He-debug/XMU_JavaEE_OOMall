package cn.edu.xmu.oomall.model.enumeration;

import cn.edu.xmu.oomall.model.po.TimeSegmentPo;

public enum TimeSegmentType {

    UNDEFINED(0),
    ADVERTISEMENT(1),
    FLASHSALE(2);

    private int typeValueInPo;

    TimeSegmentType(int typeValueInPo) {
        this.typeValueInPo = typeValueInPo;
    }

    public static TimeSegmentType parseTypeEnumFromPoValue(TimeSegmentPo po){
        if(po.getType()==(byte)ADVERTISEMENT.typeValueInPo){
            return TimeSegmentType.ADVERTISEMENT;
        } else if(po.getType()==(byte)FLASHSALE.typeValueInPo){
            return TimeSegmentType.FLASHSALE;
        } else {
            return TimeSegmentType.UNDEFINED;
        }
    }

    public static Byte findTypeValueForPo(TimeSegmentType type) {
        return (byte)type.typeValueInPo;
    }

    public int getTypeValueInPo() {
        return typeValueInPo;
    }
}
