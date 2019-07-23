package com.yanxue.constants;

/**
 * 活动的状态：status
 * -1:活动删除
 * 0：活动未开始
 * 1：活动结束
 */
public enum LectureStatusEnum {

    DELETE(-1, "活动删除"),
    NOTSTARTED(0, "活动未开始"),
    END(1, "活动结束"),;

    private int value;
    private String desc;

    LectureStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static LectureStatusEnum get(int value) {
        for (LectureStatusEnum lectureStatusEnum : LectureStatusEnum.values()) {
            if (value == lectureStatusEnum.getValue()) {
                return lectureStatusEnum;
            }
        }
        throw new RuntimeException("Unknown lecture status");
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
