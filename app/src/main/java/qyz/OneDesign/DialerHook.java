package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class DialerHook {
  private static ClassLoader classLoader;
  private static final String hookClassName = "com.samsung.android.dialtacts.util.CscFeatureUtil";

  public static void doHook(ClassLoader classLoader) {
    DialerHook.classLoader = classLoader;
    showLocInfo();
  }

  public static void showLocInfo() {
    try {
      XposedHelpers.findAndHookMethod(
          DialerHook.hookClassName,
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
          DialerHook.hookClassName,
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
          DialerHook.hookClassName,
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
          DialerHook.hookClassName,
          DialerHook.classLoader,
          "getEnableYellowPageImpl",
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
          DialerHook.hookClassName,
          DialerHook.classLoader,
          "getShowLocalInfoDuringDial",
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
          DialerHook.hookClassName,
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

    try {
      XposedHelpers.findAndHookMethod(
          DialerHook.hookClassName,
          DialerHook.classLoader,
          "getOpStyleVariationImpl",
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
