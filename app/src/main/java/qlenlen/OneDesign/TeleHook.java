package qlenlen.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class TeleHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    TeleHook.classLoader = classLoader;
    unlock();
  }

  public static void unlock() {
    try {
      findAndHookMethod(
          "org.telegram.messenger.UserConfig",
          TeleHook.classLoader,
          "isPremium",
          new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) {
              // log("isPremium");
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      findAndHookMethod(
          "org.telegram.ui.PremiumPreviewFragment",
          TeleHook.classLoader,
          "acess$3000",
          XposedHelpers.findClass("org.telegram.ui.PremiumPreviewFragment", TeleHook.classLoader),
          new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) {
              // log("isSupport");
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }
}
