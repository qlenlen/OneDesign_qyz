package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;

import android.content.Context;
import android.os.Build;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class SSRMHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    SSRMHook.classLoader = classLoader;
    removeSSRM();
  }

  public static void _removeSSRM(String className, String methodName) {
    try {
      XposedHelpers.findAndHookMethod(className,
          SSRMHook.classLoader,
          methodName,
          Context.class, String.class,
          new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) {
              log("QyzDesign: stop ssrm");
              return null;
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }


  public static void removeSSRM() {
    String buildId = Build.ID;

    if (buildId.equals("TP1A.220624.014")) {
      _removeSSRM("I1.K", "u");
    } else if (buildId.equals("UP1A.231005.007")) {
      _removeSSRM("M1.P", "A");
    } else {
      try {
        _removeSSRM("I1.K", "u");
        _removeSSRM("M1.P", "A");
      } catch (Throwable ignored) {
      }
      log("ssrm: not support for this ROM yet");
    }
  }
}
