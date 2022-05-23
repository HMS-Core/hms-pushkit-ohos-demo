/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample.slice;

import com.huawei.ohos.pushsample.utils.LogUtil;
import com.huawei.ohos.pushsample.ResourceTable;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.IntentParams;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

import java.nio.charset.StandardCharsets;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        LogUtil.setText((Text) findComponentById(ResourceTable.Id_text_main));
        parseIntent(intent);
        Button button = (Button) findComponentById(ResourceTable.Id_token);
        if (button != null) {
            button.setClickedListener(this);
        }
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
        LogUtil.setText((Text) findComponentById(ResourceTable.Id_text_main));
    }

    public static void parseIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        LogUtil.i("action is: " + intent.getAction() + ", bundle name is: " + intent.getBundle());

        IntentParams intentParams = intent.getParams();
        if (intentParams == null) {
            return;
        }
        for (String key : intentParams.keySet()) {
            Object obj = intentParams.getParam(key);
            if (obj instanceof byte[]) {
                LogUtil.i("key:" + key + ", value:" + new String((byte[])obj, StandardCharsets.UTF_8));
            } else if (obj instanceof String) {
                LogUtil.i("key:" + key + ", value:" + obj);
            } else if (obj instanceof Integer) {
                LogUtil.i("key:" + key + ", Integer.value:" + obj);
            } else if (obj instanceof Boolean) {
                LogUtil.i("key:" + key + ", Boolean.value:" + obj);
            }
        }
    }

    @Override
    public void onClick(Component component) {
        int btnId = component.getId();
        switch (btnId) {
            case ResourceTable.Id_token:
                startTokenAbility();
                break;
            default:
                LogUtil.i("nothing to do.");
                break;
        }
    }

    private void startTokenAbility() {
        Intent secondIntent = new Intent();
        Operation operation = new Intent.OperationBuilder()
            .withDeviceId("")
            .withBundleName("com.huawei.ohos.pushsample")
            .withAbilityName("com.huawei.ohos.pushsample.TokenAbility")
            .build();
        secondIntent.setOperation(operation);
        startAbility(secondIntent);
        LogUtil.i("Token page ability has created.");
    }
}
