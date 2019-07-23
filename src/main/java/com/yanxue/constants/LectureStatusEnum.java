package com.yanxue.constants;

/**
 * 活动的状态：status
 * -1:活动删除
 * 0：活动未开始
 * 1：活动结束
 */
public enum LectureStatusEnum {

    DELETE(-1, "活动删除"),
    NOTSTARTED(0, "活动未开始"), //这里本应该还有一个状态为中间态，但是这里没有，所以一个活动如果点击开始的话，就直接变成活动结束的状态了
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
