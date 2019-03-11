package com.ghroosk.practice.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;

/**
 * 文件路径工具类
 * Created by zhx on 2019/3/6.
 */
public class FilePathUtils {

    private String getResourcesUri(Context context, @DrawableRes int id) {
        Resources resources = context.getResources();
        return ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
    }


}
