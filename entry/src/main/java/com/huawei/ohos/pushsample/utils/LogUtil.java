/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.huawei.ohos.pushsample.utils;

import ohos.agp.components.Text;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public final class LogUtil {
    private static Text textView;
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP, 0xD002200, "OHosPushDemo");

    public static void setText(Text text) {
        textView = text;
    }

    private static void println(int level, String msg, Throwable tr) {
        if (textView != null) {
            StringBuilder sb = new StringBuilder("\n");
            sb.append(msg);
            if (tr != null) {
                sb.append(tr.getMessage());
            }
            textView.append(sb.toString());
        }

        switch (level) {
            case HiLog.INFO:
                HiLog.info(LABEL_LOG, msg);
                break;
            case HiLog.WARN:
                HiLog.warn(LABEL_LOG, msg);
                break;
            case HiLog.ERROR:
                HiLog.error(LABEL_LOG, msg);
                break;
            case HiLog.FATAL:
                HiLog.fatal(LABEL_LOG, msg);
                break;
            default:
                HiLog.debug(LABEL_LOG, msg);
                break;
        }
    }

    public static void i(String msg) {
        println(HiLog.INFO, msg, null);
    }

    public static void d(String msg) {
        println(HiLog.DEBUG, msg, null);
    }

    public static void w(String msg) {
        println(HiLog.WARN, msg, null);
    }

    public static void e(String msg, Throwable tr) {
        println(HiLog.ERROR, msg, tr);
    }

    public static void f(String msg) {
        println(HiLog.FATAL, msg, null);
    }
}
