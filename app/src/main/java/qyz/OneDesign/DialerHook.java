package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class DialerHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    DialerHook.classLoader = classLoader;
    show();
  }

  public static void show() {
    try {
      XposedHelpers.findAndHookMethod(
          "com.samsung.android.dialtacts.util.CscFeatureUtil",
          DialerHook.classLoader,
          "isOpStyleCHN",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      XposedHelpers.findAndHookMethod(
          "com.samsung.android.dialtacts.util.CscFeatureUtil",
          DialerHook.classLoader,
          "isOpStyleCHNImpl",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      XposedHelpers.findAndHookMethod(
          "com.samsung.android.dialtacts.util.CscFeatureUtil",
          DialerHook.classLoader,
          "isOpStyleHKTW",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              return false;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      XposedHelpers.findAndHookMethod(
          "com.samsung.android.dialtacts.util.CscFeatureUtil",
          DialerHook.classLoader,
          "getOpStyleVariation",
          new XC_MethodReplacement() {
            @Override
            protected String replaceHookedMethod(MethodHookParam param) {
              return "CHN";
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }
}
