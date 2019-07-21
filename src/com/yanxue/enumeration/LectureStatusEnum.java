package com.yanxue.enumeration;

/**
 * 活动的状态：status
 * -1:活动删除
 * 0：活动未开始
 * 1：活动结束
 */
public enum LectureStatusEnum {
    
    DELETE(-1),
    NOTSTARTED(0),
    END(1);
    
    private int value;
    
    private LectureStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
