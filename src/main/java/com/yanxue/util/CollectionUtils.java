package com.yanxue.util;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Collection col) {
        return !isNotEmpty(col);
    }

    public static boolean isNotEmpty(Collection col) {
        return col != null && col.size() > 0;
    }
}
