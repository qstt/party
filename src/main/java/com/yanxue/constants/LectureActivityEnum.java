package com.yanxue.constants;

/**
 * 活动类型：activity
 * 0：签到
 * 1：发言
 */
public enum LectureActivityEnum {

    CHECK(0, "签到"),
    SPEAK(1, "发言");

    private int value;
    private String desc;

    LectureActivityEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static LectureActivityEnum get(int value) {
        for (LectureActivityEnum lectureActivityEnum : LectureActivityEnum.values()) {
            if (value == lectureActivityEnum.getValue()) {
                return lectureActivityEnum;
            }
        }
        throw new RuntimeException("Unknown lecture activity type");
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
