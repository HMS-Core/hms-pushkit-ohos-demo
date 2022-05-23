/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample;

import com.huawei.ohos.pushsample.slice.TokenAbilitySlice;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TokenAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TokenAbilitySlice.class.getName());
    }
}
