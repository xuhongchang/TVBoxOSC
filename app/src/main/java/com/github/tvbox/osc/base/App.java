package com.github.tvbox.osc.base;

import androidx.multidex.MultiDexApplication;

import com.github.tvbox.osc.callback.EmptyCallback;
import com.github.tvbox.osc.callback.LoadingCallback;
import com.github.tvbox.osc.data.AppDataManager;
import com.github.tvbox.osc.server.ControlManager;
import com.github.tvbox.osc.util.HawkConfig;
import com.github.tvbox.osc.util.OkGoHelper;
import com.github.tvbox.osc.util.PlayerHelper;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * @author pj567
 * @date :2020/12/17
 * @description:
 */
public class App extends MultiDexApplication {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initParams();
        // OKGo
        OkGoHelper.init();
        // 初始化Web服务器
        ControlManager.init(this);
        //初始化数据库
        AppDataManager.init();
        LoadSir.beginBuilder()
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .commit();
        AutoSizeConfig.getInstance().setCustomFragment(true).getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);
        PlayerHelper.init();
    }

    private void initParams() {
        // Hawk
        Hawk.init(this).build();
        Hawk.put(HawkConfig.DEBUG_OPEN, false);
        if (!Hawk.contains(HawkConfig.PLAY_TYPE)) {
            Hawk.put(HawkConfig.PLAY_TYPE, 1);
            ArrayList<String> history = new ArrayList<>();
            history.add("https://tv.5ye.cc");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/ptest.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/j3zp.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/ikbb.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/ikbb.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/fj.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/xymc.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/g6qm.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/1pw8.json");
            history.add("https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/pnzh.json");
            Hawk.put(HawkConfig.API_HISTORY, history);
            Hawk.put(HawkConfig.API_URL, "https://ghproxy.com/https://raw.githubusercontent.com/tv-player/tvbox-line/main/tv/ptest.json");
        }
    }

    public static App getInstance() {
        return instance;
    }
}