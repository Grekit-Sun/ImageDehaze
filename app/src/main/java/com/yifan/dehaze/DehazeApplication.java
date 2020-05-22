package com.yifan.dehaze;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.yifan.dehaze.util.StringUtil;

import java.util.List;

/**
 * @Author sun
 * @Date 2020-05-22
 * Description: applocation
 */
public class DehazeApplication extends Application {

    public static Context appCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        if(!isMainProcess()){
            return;
        }
        appCtx = getApplicationContext();
        initLeakCanary();
    }

    /**
     * 初始化LeakCanary
     */
    private void initLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * 获取是否是主进程
     *
     * @return 是否是主进程
     */
    private boolean isMainProcess() {
        return StringUtil.equals(getProcessName(), getPackageName());
    }

    /**
     * 获取当前进程的名称.
     */
    private String getProcessName() {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> rps;
        String name = "";
        if (am != null) {
            rps = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo rp : rps) {
                if (rp.pid == pid) {
                    name = rp.processName;
                    break;
                }
            }
        }
        return name;
    }

}
