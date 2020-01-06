package com.yanxue.constants;

public enum AccountTypeEnum {

    ADMIN("管理员", 1),
    USER("用户", 2);


    private String desc;
    private Integer code;

    AccountTypeEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public static AccountTypeEnum get(int code) {
        for (AccountTypeEnum accountTypeEnum : AccountTypeEnum.values()) {
            if (code == accountTypeEnum.code) {
                return accountTypeEnum;
            }
        }
        throw new RuntimeException("Unkonwn account type");
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }
}
