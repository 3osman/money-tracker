package com.example.money_tracker;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabBarActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		Resources ressources = getResources();
		TabHost tabHost = getTabHost();

		// Chart Tab
		Intent intentAndroid = new Intent().setClass(this, ChartActivity.class);
		TabSpec tabSpecAndroid = tabHost.newTabSpec("Chartin")
				.setIndicator("In")
				.setContent(intentAndroid);

		// chart Tab
		Intent intentApple = new Intent().setClass(this, ChartActivity2.class);
		TabSpec tabSpecApple = tabHost.newTabSpec("Chartout")
				.setIndicator("Out")
				.setContent(intentApple);

		// Date Tab
		Intent intentDate = new Intent().setClass(this, ShowHistory.class);
		TabSpec tabSpecDate = tabHost.newTabSpec("Date")
				.setIndicator("History")
				.setContent(intentDate);

		// Map Tab
		Intent intentMaps = new Intent().setClass(this, ShowMap.class);
		TabSpec tabSpecMap = tabHost.newTabSpec("Map")
				.setIndicator("Map")
				.setContent(intentMaps);

		// add all tabs
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		tabHost.addTab(tabSpecDate);
		tabHost.addTab(tabSpecMap);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

}
