package qlenlen.OneDesign;

import static de.robv.android.xposed.XposedBridge.log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import qlenlen.OneDesign.utils.Packages;

public class MainHook implements IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {

    // set phone condition to unrooted
    if (lpparam.packageName.equals(Packages.SETTINGS)) {
      try {
        log("Detected: " + Packages.SETTINGS);
        // SettingHook.doHook(lpparam.classLoader);
      } catch (Throwable e) {
        log(e);
      }
    }

    // remove ssrm warning if you have modified GPU frequencies
    if (lpparam.packageName.equals(Packages.SAMSUNG_SDHMS)) {
      try {
        log("Detected: " + Packages.SAMSUNG_SDHMS);
        SSRMHook.doHook(lpparam.classLoader);
      } catch (Throwable e) {
        log(e);
      }
    }

    // unlock better video quality for bilibili
    if (lpparam.packageName.equals(Packages.BILIBILI_GOOGLE)
        || lpparam.packageName.equals(Packages.BILIBILI)) {
      try {
        log("Detected: com.bilibili.app.in || tv.danmaku.bili");
        BiliHook.doHook(lpparam.classLoader);
      } catch (Throwable e) {
        log(e);
      }
    }

    // unlock premium
    if (lpparam.packageName.equals("org.telegram.messenger.web")) {
      try {
        log("Detected: " + Packages.TELEGRAM);
        TeleHook.doHook(lpparam.classLoader);
      } catch (Throwable e) {
        log(e);
      }
    }

    // fake sales_code to CHN however it's unnecessary to me
    if (lpparam.packageName.equals(Packages.SAMSUNG_DIALER)) {
      try {
        log("Detected: " + Packages.SAMSUNG_DIALER);
        // DialerHook.doHook(lpparam.classLoader);
      } catch (Throwable e) {
        log(e);
      }
    }
  }
}
