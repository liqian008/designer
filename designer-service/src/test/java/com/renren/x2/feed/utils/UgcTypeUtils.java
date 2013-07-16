/**
 * $Id: UgcTypeUtils.java 122472 2012-12-11 10:02:58Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.utils;

import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.NativeUgcType;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-3 下午12:57:05
 */
public class UgcTypeUtils {

    public static final int TYPE_POST = 1;

    public static final int TYPE_ALBUM = 2;

    public static final int TYPE_LOVESTATUS = 3;

    public static final int TYPE_PERSONALVOICE = 4;

    public static NativeUgcType convert2NativeType(int type) {
        switch (type) {
            case TYPE_POST:
                return NativeUgcType.Posts;
            case TYPE_ALBUM:
                return NativeUgcType.Album;
            case TYPE_PERSONALVOICE:
                return NativeUgcType.PersonalVoice;
            case TYPE_LOVESTATUS:
                return NativeUgcType.LoveStatus;
        }
        return null;
    }

    public static int convert2Int(NativeUgcType type) {
        switch (type) {
            case Posts:
                return TYPE_POST;
            case Album:
                return TYPE_ALBUM;
            case PersonalVoice:
                return TYPE_PERSONALVOICE;
            case LoveStatus:
                return TYPE_LOVESTATUS;
        }
        return 0;
    }

    public static boolean needDispatch2School(NativeUgcType ugcType) {
        switch (ugcType) {
            case Posts:
                return true;
            default:
                return false;
        }
    }

    public static boolean needDispatch2Follower(NativeUgcType ugcType) {
        switch (ugcType) {
            case Posts:
            case Album:
            case LoveStatus:
            case PersonalVoice:
                return true;
            default:
                return false;
        }
    }

    public static ExtUgcType getLikeTypeByGender(int gender) {
        if (gender == 0) return ExtUgcType.FLike;
        else return ExtUgcType.MLike;
    }
}
