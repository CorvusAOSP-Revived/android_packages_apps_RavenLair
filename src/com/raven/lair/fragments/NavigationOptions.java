/*
 * Copyright (C) 2018-2019 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raven.lair.fragments;

import static android.view.WindowManagerPolicyConstants.NAV_BAR_MODE_GESTURAL;
import android.content.res.Resources;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import androidx.preference.*;
import androidx.preference.Preference.OnPreferenceChangeListener;

import com.android.internal.logging.nano.MetricsProto;

import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import com.corvus.support.preferences.SystemSettingSwitchPreference;
import com.corvus.support.preferences.SystemSettingListPreference;
import com.corvus.support.preferences.SystemSettingSwitchPreference;

import java.util.ArrayList;
import java.util.List;

public class NavigationOptions extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener, Indexable {

    private static final String NAVBAR_INVERSE = "navigation_bar_inverse";
    private static final String NAVBAR_LAYOUT = "navbar_layout_views";


    private SystemSettingSwitchPreference mNavbarInverse;
    private SystemSettingListPreference mNavbarLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.navigation_options);
         final Resources res = getResources();

        final PreferenceScreen prefSet = getPreferenceScreen();

         mNavbarInverse = (SystemSettingSwitchPreference) findPreference(NAVBAR_INVERSE);
        mNavbarLayout = (SystemSettingListPreference) findPreference(NAVBAR_LAYOUT);
        int navMode = res.getInteger(
                com.android.internal.R.integer.config_navBarInteractionMode);
        if (navMode == NAV_BAR_MODE_GESTURAL) {
            mNavbarInverse.setEnabled(false);
            mNavbarInverse.setSummary(R.string.navbar_gesture_enabled);
            mNavbarLayout.setEnabled(false);
            mNavbarLayout.setSummary(R.string.navbar_gesture_enabled);
        }

    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CORVUS;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    final ArrayList<SearchIndexableResource> result = new ArrayList<>();
                    final SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.navigation_options;
                    result.add(sir);
                    return result;
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    final List<String> keys = super.getNonIndexableKeys(context);
                    return keys;
        }
    };
}
