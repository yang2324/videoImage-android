package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yang
 * date: 2020/8/19
 * Describe:
 */
public class Application extends android.app.Application {
    private static Application INSTANCE;
    private List<Activity> activityList = new LinkedList<Activity>();

    public static int width;
    public static int height;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());



    }


    public static Application getInstance() {
        return INSTANCE;
    }

    // 添加Activity 到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity 并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}