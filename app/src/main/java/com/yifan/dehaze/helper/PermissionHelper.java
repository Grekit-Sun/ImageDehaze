package com.yifan.dehaze.helper;

import android.content.Context;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

/**
 * 权限封装类
 */
public class PermissionHelper {

    public static void requestMultiPermission(Context context, Action grantedAction,
                                              Action deniedAction, String... permissions) {
        requestMultiPermission(context, grantedAction, deniedAction, null, permissions);
    }

    /**
     * 请求权限组.
     *
     * @param context       Context
     * @param grantedAction 权限允许监听
     * @param deniedAction  权限拒绝监听
     * @param rationale     请求失败的回调，用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框
     * @param permissions   权限组
     */
    public static void requestMultiPermission(Context context, Action grantedAction,
                                              Action deniedAction, Rationale rationale, String... permissions) {
        AndPermission.with(context)
                .permission(permissions)
                .onGranted(grantedAction)
                .onDenied(deniedAction)
                .rationale(rationale)
                .start();
    }


    public static void requestSinglePermission(Context context, Action grantedAction,
                                               Action deniedAction, String permission) {
        requestSinglePermission(context, grantedAction, deniedAction, null, permission);
    }

    /**
     * 请求单个权限.
     *
     * @param context       Context
     * @param grantedAction 权限允许监听
     * @param deniedAction  权限拒绝监听
     * @param rationale     请求失败的回调，用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框
     * @param permission    权限组
     */
    public static void requestSinglePermission(Context context, Action grantedAction,
                                               Action deniedAction, Rationale rationale, String permission) {

        AndPermission.with(context)
                .permission(permission)
                .onGranted(grantedAction)
                .onDenied(deniedAction)
                .rationale(rationale)
                .start();

    }


}
