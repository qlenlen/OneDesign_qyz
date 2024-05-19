package qlenlen.OneDesign;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

public class Main extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    Button hellobtn = findViewById(R.id.button_hide_icon);

    hellobtn.setOnClickListener(v -> hideAppIcon());
  }

  private void hideAppIcon() {
    PackageManager pm = getPackageManager();
    ComponentName componentName = new ComponentName(this, Main.class);
    pm.setComponentEnabledSetting(
        componentName,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP);
  }
}
