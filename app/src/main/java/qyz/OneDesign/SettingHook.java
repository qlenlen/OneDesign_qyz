package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;

public class SettingHook {

  private static final String TARGET_CLASS =
      "com.samsung.android.settings.deviceinfo.SecDeviceInfoUtils";

  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    SettingHook.classLoader = classLoader;
    makeOfficial();
//    dark();
  }

  public static void dark() {
    try {
      XposedHelpers.callMethod(
          XposedHelpers.findClass(
              "com.android.settings.development.ForceDarkPreferenceController", classLoader),
          "onPreferenceChange",
          XposedHelpers.findClassIfExists("androidx.preference.Preference", SettingHook.classLoader)
              .newInstance(),
          true);
      log("ForceDarkPreferenceController onPerferenceChange");
    } catch (IllegalAccessException | InstantiationException e) {
      log("ForceDarkPreferenceController onPerferenceChange");
    }

    try {
      findAndHookMethod(
          "com.android.settings.development.ForceDarkPreferenceController",
          SettingHook.classLoader,
          "onPreferenceChange",
          XposedHelpers.findClass("androidx.preference.Preference", SettingHook.classLoader),
          Object.class,
          new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
              Object preference = param.args[0];
              log(preference.toString());
              log("ForceDarkPreferenceController onPerferenceChange");
              param.args[1] = true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      findAndHookMethod(
          "com.android.settings.development.ForceDarkPreferenceController",
          SettingHook.classLoader,
          "updateState",
          // use xp method to find the correct param class
          XposedHelpers.findClass("androidx.preference.Preference", SettingHook.classLoader),
          new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param)
                throws IllegalAccessException, InstantiationException {
              log("ForceDarkPreferenceController updateState");
              Object preference = param.args[0];
              // call method spontaneously
              XposedHelpers.callMethod(preference, "setChecked", true);
              return null;
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }

  private static void makeOfficial() {
    try {
      findAndHookMethod(
          TARGET_CLASS,
          SettingHook.classLoader,
          "checkRootingCondition",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              log("rewrite checkRootingCondition to false");
              return false;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      findAndHookMethod(
          TARGET_CLASS,
          SettingHook.classLoader,
          "isAlterModel",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              log("rewrite isAlterModel to false");
              return false;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      findAndHookMethod(
          TARGET_CLASS,
          SettingHook.classLoader,
          "isPhoneStatusUnlocked",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              log("rewrite isPhoneStatusUnlocked to true");
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }
}
