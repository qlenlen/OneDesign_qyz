package qlenlen.OneDesign;

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
              log("stop ssrm");
              return null;
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }


  public static void removeSSRM() {
    // for myself
    if (Build.ID.equals("TP1A.220624.014")) {
      _removeSSRM("I1.K", "u");
    } else {
      _removeSSRM("M1.P", "A");
    }
  }
}
