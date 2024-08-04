package qyz.OneDesign;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class BiliHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    BiliHook.classLoader = classLoader;
    unlockVip();
  }

  private static void unlockVip() {
    XposedHelpers.findAndHookMethod(
        "com.bilibili.lib.accountinfo.model.VipUserInfo",
        BiliHook.classLoader,
        "isEffectiveVip",
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            param.setResult(true);
          }
        });

    XposedHelpers.findAndHookMethod(
        "com.bilibili.lib.accountinfo.model.VipExtraUserInfo",
        BiliHook.classLoader,
        "isEffectiveVip",
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            param.setResult(true);
          }
        });

    XposedHelpers.findAndHookMethod(
        "tv.danmaku.bili.ui.main2.api.AccountMine",
        BiliHook.classLoader,
        "isEffectiveVip",
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            param.setResult(true);
          }
        });

    XposedHelpers.findAndHookMethod(
        "tv.danmaku.bili.ui.main2.api.AccountMine",
        BiliHook.classLoader,
        "isEffectiveYearVip",
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            param.setResult(true);
          }
        });
  }
}
