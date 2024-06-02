package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class CameraHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    CameraHook.classLoader = classLoader;
    shutter();
  }

  public static void shutter() {

    try {
      findAndHookMethod(
          XposedHelpers.findClass(
              "com.samsung.android.feature.SemCscFeature", CameraHook.classLoader),
          "getBoolean",
          String.class,
          new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) {
              if (param.args[0].equals("CscFeature_Camera_ShutterSoundMenu")) {
                log("CscFeature_Camera_EnableCameraShutterSoundMenu");
                param.setResult(true);
              }
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }
}
