/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample;

import com.huawei.ohos.pushsample.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.app.Context;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
    @Override
    public void onActive() {
        super.onActive();
    }
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
