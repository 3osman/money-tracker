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

		// Android tab
		Intent intentAndroid = new Intent().setClass(this, ChartActivity.class);
		TabSpec tabSpecAndroid = tabHost.newTabSpec("Android")
				.setIndicator("", ressources.getDrawable(R.drawable.in))
				.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, ChartActivity2.class);
		TabSpec tabSpecApple = tabHost.newTabSpec("Apple")
				.setIndicator("", ressources.getDrawable(R.drawable.out))
				.setContent(intentApple);

		// add all tabs
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);

		// set Windows tab as default (zero based)
		tabHost.setCurrentTab(2);
	}

}
