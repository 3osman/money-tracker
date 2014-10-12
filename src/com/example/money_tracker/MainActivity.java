package com.example.money_tracker;

import com.money_tracker.dao.CategoryDao;
import android.view.View.OnClickListener;

import com.money_tracker.dao.EntryDao;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {
	private CategoryDao datasource;
	private EntryDao entrysource;
	ImageButton imageButton;

	public void addListenerOnButtons() {

		imageButton = (ImageButton) findViewById(R.id.zero);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

				i.putExtra("new_variable_name","0");
				startActivity(i);

			}

		});
		imageButton = (ImageButton) findViewById(R.id.one);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","1");
				startActivity(i);

			}

		});
		imageButton = (ImageButton) findViewById(R.id.two);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","2");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.three);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","3");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.four);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","4");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.five);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","5");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.six);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","6");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.seven);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","7");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.eight);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name","8");
				startActivity(i);
			}

		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButtons();
		datasource = new CategoryDao(this);
		datasource.open();
		entrysource = new EntryDao(this);
		entrysource.open();
		// Category test = (Category) findViewById(R.id.redCategory);
		// Category test1 = (Category) findViewById(R.id.redCategory01);
		// Category test2 = (Category) findViewById(R.id.redCategory02);
		/*
		 * Category test3 = (Category) findViewById(R.id.redCategory03);
		 * Category test4 = (Category) findViewById(R.id.redCategory04);
		 * Category test5 = (Category) findViewById(R.id.redCategory05);
		 * Category test6 = (Category) findViewById(R.id.redCategory06);
		 * Category test7 = (Category) findViewById(R.id.redCategory07);
		 * Category test8 = (Category) findViewById(R.id.redCategory08);
		 */

		// test.setBackgroundColor(Color.RED);
		// test1.setBackgroundColor(Color.GREEN);
		// test2.setBackgroundColor(Color.BLUE);
		/*
		 * test3.setBackgroundColor(Color.WHITE);
		 * test4.setBackgroundColor(Color.YELLOW);
		 * test5.setBackgroundColor(Color.GREEN);
		 * test6.setBackgroundColor(Color.RED);
		 * test7.setBackgroundColor(Color.BLUE);
		 * test8.setBackgroundColor(Color.BLACK);
		 */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
