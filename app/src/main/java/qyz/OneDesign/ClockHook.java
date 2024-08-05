package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class ClockHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    ClockHook.classLoader = classLoader;
    holiday();
  }

  public static void holiday() {
    XposedHelpers.findAndHookMethod("android.os.SemSystemProperties", ClockHook.classLoader, "getSalesCode",
        new XC_MethodReplacement() {
          @Override
          protected Object replaceHookedMethod(MethodHookParam param) {
            log("QyzDesign: rewrite getSalesCode to CHC");
            return "CHC";
          }
        }
    );

    XposedHelpers.findAndHookMethod("android.os.SemSystemProperties", ClockHook.classLoader, "getCountryIso",
        new XC_MethodReplacement() {
          @Override
          protected Object replaceHookedMethod(MethodHookParam param) {
            log("QyzDesign: rewrite getCountryIso to CN");
            return "CN";
          }
        }
    );
  }
}
