package qyz.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.content.Context;

import de.robv.android.xposed.XC_MethodReplacement;

public class CameraHook {
  private static ClassLoader classLoader;

  public static void doHook(ClassLoader classLoader) {
    CameraHook.classLoader = classLoader;
    shutter();
  }

  public static void shutter() {

    try {
      findAndHookMethod(
          "com.sec.android.app.camera.audio.CameraAudioManagerImpl",
          CameraHook.classLoader,
          "isShutterSoundEnabled",
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              log("QyzDesign: rewrite isShutterSoundEnabled to true");
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }

    try {
      findAndHookMethod(
          "com.sec.android.app.camera.util.AudioUtil",
          CameraHook.classLoader,
          "isForceShutterSoundRequired",
          Context.class,
          new XC_MethodReplacement() {
            @Override
            protected Boolean replaceHookedMethod(MethodHookParam param) {
              log("QyzDesign: rewrite isForceShutterSoundRequired to true");
              return true;
            }
          });
    } catch (Throwable e) {
      log(e);
    }
  }
}
