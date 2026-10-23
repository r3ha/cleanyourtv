package com.gathin.tvhome;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/** The standard settings intent, or, when nothing usable answers it, the first vendor settings entry
 *  from settings_fallback_components that is installed (Xiaomi China, TCL / FFALCON, Fire OS). */
final class SystemSettings {
    private SystemSettings() {}

    static Intent intent(Context context, String action) {
        PackageManager pm = context.getPackageManager();

        // Vendor settings first (Xiaomi HyperOS, TCL, etc.) so custom vendor settings takes precedence over AOSP stub
        for (String value : context.getResources().getStringArray(R.array.settings_fallback_components)) {
            ComponentName component = ComponentName.unflattenFromString(value);
            if (component == null) continue;
            try {
                pm.getPackageInfo(component.getPackageName(), 0);
                return new Intent().setComponent(component);
            } catch (PackageManager.NameNotFoundException ignored) {
            }
        }

        Intent standard = new Intent(action);
        ResolveInfo resolved = pm.resolveActivity(standard, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolved != null && !resolved.activityInfo.name.contains("CTSDummy")) return standard;

        return standard;
    }
}
