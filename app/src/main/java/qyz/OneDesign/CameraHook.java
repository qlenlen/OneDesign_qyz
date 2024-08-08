package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class CameraHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    CameraHook.classLoader = classLoader;
    shutter();
  }

  public static void shutter() {

    XposedHelpers.findAndHookMethod(
        "z2.d",
        CameraHook.classLoader,
        "e",
        XposedHelpers.findClass("z2.b", CameraHook.classLoader),
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            if (param.args[0].toString().equals("SUPPORT_SHUTTER_SOUND_MENU")) {
              // log("CameraHook: SUPPORT_SHUTTER_SOUND_MENU");
              param.setResult(true);
            }

            if (param.args[0].toString().equals("IS_COUNTRY_CHINA")) {
              // log("CameraHook: IS_COUNTRY_CHINA");
              param.setResult(true);
            }
          }
        });

    XposedHelpers.findAndHookMethod(
        "android.os.SystemProperties",
        CameraHook.classLoader,
        "get",
        String.class,
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            String key = (String) param.args[0];
            if (key.equals("ro.csc.sales_code")) {
              param.setResult("CHC");
              // log("SystemProperties: ro.csc.sales_code");
            }
          }
        });

    XposedHelpers.findAndHookMethod(
        "android.os.SemSystemProperties",
        CameraHook.classLoader,
        "get",
        String.class,
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            String key = (String) param.args[0];
            // log("SemSystemProperties: " + key);
            if (key.equals("ro.csc.countryiso_code")) {
              param.setResult("CN");
              // log("ro.csc.countryiso_code");
            }
            if (key.equals("ro.csc.country_code")) {
              param.setResult("China");
              // log("ro.csc.country_code");
            }
            if (key.equals("ro.csc.sales_code")) {
              param.setResult("CHC");
              // log("SemSystemProperties: ro.csc.sales_code");
            }
          }
        });
  }
}
