package com.luna.toggledemo;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum MyFeatures implements Feature {

    @EnabledByDefault
    @Label("顯示歡迎訊息")
    HELLO_WORLD,

    @Label("顯示折扣活動")
    DISCOUNT_BANNER,

    @Label("啟用新版演算法")
    ADVANCED_LOGIC,

    @Label("彩蛋訊息 (User-specific)")
    EASTER_EGG;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
