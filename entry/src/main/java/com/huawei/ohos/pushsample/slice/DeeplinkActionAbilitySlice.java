/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample.slice;

import com.huawei.ohos.pushsample.utils.LogUtil;
import com.huawei.ohos.pushsample.ResourceTable;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Text;

public class DeeplinkActionAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_deeplink_action);

        LogUtil.setText((Text) findComponentById(ResourceTable.Id_text_action));
        LogUtil.i("DeeplinkActionAbilitySlice started...");
        MainAbilitySlice.parseIntent(intent);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
