/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample;

import com.huawei.hms.push.ohos.HmsMessageService;
import com.huawei.hms.push.ohos.ZBaseException;
import com.huawei.hms.push.ohos.ZRemoteMessage;
import com.huawei.ohos.pushsample.utils.LogUtil;

public class DemoHmsMessageServiceAbility extends HmsMessageService {
    @Override
    public void onMessageReceived(ZRemoteMessage message) {
        LogUtil.i("::onMessageReceived,  data: " + message.getData());
    }

    @Override
    public void onNewToken(String token) {
        LogUtil.i( "DemoHmsMessageServiceAbility::onNewToken token:" + token);
    }

    @Override
    public void onTokenError(Exception exception) {
        if (exception instanceof ZBaseException) {
            LogUtil.i("DemoHmsMessageServiceAbility::onTokenError errCode:" +
                ((ZBaseException) exception).getErrorCode());
        }
    }
}