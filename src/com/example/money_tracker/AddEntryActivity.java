package com.example.money_tracker;

import com.money_tracker.dao.CategoryDao;
import com.money_tracker.dao.EntryDao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AddEntryActivity extends ActionBarActivity {
	private CategoryDao datasource;
	private EntryDao entrysource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		datasource = new CategoryDao(this);
		datasource.open();
		entrysource = new EntryDao(this);
		entrysource.open();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_entry);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
