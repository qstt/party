package com.yanxue.enumeration;

/**
 * 活动类型：activity
 * 0：签到
 * 1：发言
 */
public enum LectureActivityEnum {

    CHECK(0),
    SPEAK(1);

    private int value;

    private LectureActivityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
