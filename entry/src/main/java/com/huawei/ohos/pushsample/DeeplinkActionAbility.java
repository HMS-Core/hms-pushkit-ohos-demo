/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample;

import com.huawei.ohos.pushsample.slice.DeeplinkActionAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class DeeplinkActionAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(DeeplinkActionAbilitySlice.class.getName());
    }
}
