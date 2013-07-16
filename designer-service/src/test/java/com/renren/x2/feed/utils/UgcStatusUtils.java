/**
 * $Id: UgcStatusUtils.java 122472 2012-12-11 10:02:58Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.utils;

import com.renren.x2.feedapi.constants.UgcStatus;


/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-3 上午11:43:59
 */
public class UgcStatusUtils {

    public static final int STATUS_NORMAL = 0;

    public static final int STATUS_DELETE = 1;

    public static UgcStatus conver2Enum(int statue) {
        if (statue == STATUS_DELETE) {
            return UgcStatus.Deleted;
        }
        return UgcStatus.Normal;
    }

    public static int conver2Int(UgcStatus statue) {
        if (statue == UgcStatus.Deleted) {
            return STATUS_DELETE;
        }
        return STATUS_NORMAL;
    }
}
