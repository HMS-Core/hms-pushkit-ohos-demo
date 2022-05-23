/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample.slice;

import com.huawei.ohos.pushsample.ResourceTable;
import com.huawei.ohos.pushsample.utils.LogUtil;
import com.huawei.hms.push.common.ApiException;
import com.huawei.hms.push.ohos.HmsInstanceId;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.app.dispatcher.TaskDispatcher;

public class TokenAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private TaskDispatcher uiTaskDispatcher;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_token);
        LogUtil.setText((Text) findComponentById(ResourceTable.Id_text_token));
        uiTaskDispatcher = getUITaskDispatcher();

        Button getIdBtn = (Button) findComponentById(ResourceTable.Id_getId);
        getIdBtn.setClickedListener(this);

        Button delIdBtn = (Button) findComponentById(ResourceTable.Id_deleteId);
        delIdBtn.setClickedListener(this);

        Button getCreationTimeBtn = (Button) findComponentById(ResourceTable.Id_getCreationTime);
        getCreationTimeBtn.setClickedListener(this);

        Button getTokenBtn = (Button) findComponentById(ResourceTable.Id_getToken);
        getTokenBtn.setClickedListener(this);

        Button deleteTokenBtn = (Button) findComponentById(ResourceTable.Id_deleteToken);
        deleteTokenBtn.setClickedListener(this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
        LogUtil.setText((Text) findComponentById(ResourceTable.Id_text_token));
    }

    @Override
    public void onClick(Component component) {
        int btnId = component.getId();
        switch (btnId) {
            case ResourceTable.Id_getId:
                getAaid();
                break;
            case ResourceTable.Id_deleteId:
                deleteAaid();
                break;
            case ResourceTable.Id_getToken:
                getToken();
                break;
            case ResourceTable.Id_deleteToken:
                deleteToken();
                break;
            case ResourceTable.Id_getCreationTime:
                getCreateTime();
                break;
            default:
                LogUtil.i("nothing to do.");
                break;
        }
    }

    private void getAaid() {
        try {
            String aaid = HmsInstanceId.getInstance(getAbility().getAbilityPackage(), this).getId();
            LogUtil.i("get aaid success, aaid:" + aaid);
        } catch (Exception e) {
            LogUtil.e("get aaid failed, cause:" + e.getMessage(), null);
        }
    }

    private void deleteAaid() {
        new Thread("deleteAaid") {
            @Override
            public void run() {
                try {
                    HmsInstanceId.getInstance(getAbility().getAbilityPackage(),
                        TokenAbilitySlice.this).deleteId();
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.i("delete aaid success.");
                        }
                    });
                } catch (ApiException e) {
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("delete aaid failed, err code:" + e.getStatusCode(), null);
                        }
                    });
                } catch (Exception e) {
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("delete aaid failed, cause:" + e.getMessage(), null);
                        }
                    });
                }
            }
        }.start();
    }

    private void getToken() {
        new Thread("getToken") {
            @Override
            public void run() {
                try {
                    String token = HmsInstanceId.getInstance(getAbility().getAbilityPackage(),
                        TokenAbilitySlice.this).getToken("", null);
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.i("get token success,token: " + token);
                        }
                    });
                } catch (ApiException e) {
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("get token failed, err code:" + e.getStatusCode(), null);
                        }
                    });
                } catch (Exception e) {
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("get token failed, cause:" + e.getMessage(), null);
                        }
                    });
                }
            }
        }.start();
    }

    private void deleteToken() {
        new Thread("deleteToken") {
            @Override
            public void run() {
                try {
                    HmsInstanceId.getInstance(getAbility().getAbilityPackage(),
                        TokenAbilitySlice.this).deleteToken("", null);
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.i("delete token success");
                        }
                    });
                } catch (ApiException e) {
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("delete token failed, err code:" + e.getStatusCode(), null);
                        }
                    });
                } catch (Exception e) {
                    uiTaskDispatcher.asyncDispatch(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.e("delete token failed, cause:" + e.getMessage(), null);
                        }
                    });
                }
            }
        }.start();
    }

    private void getCreateTime() {
        try {
            long time = HmsInstanceId.getInstance(getAbility().getAbilityPackage(), this).getCreationTime();
            LogUtil.i("aaid creation time:" + time);
        } catch (Exception e) {
            LogUtil.e("get aaid creation time failed, cause:" + e.getMessage(), null);
        }
    }
}
