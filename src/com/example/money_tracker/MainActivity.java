package com.example.money_tracker;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Category test = (Category) findViewById(R.id.redCategory);
		Category test1 = (Category) findViewById(R.id.redCategory01);
		Category test2 = (Category) findViewById(R.id.redCategory02);
		/*Category test3 = (Category) findViewById(R.id.redCategory03);
		Category test4 = (Category) findViewById(R.id.redCategory04);
		Category test5 = (Category) findViewById(R.id.redCategory05);
		Category test6 = (Category) findViewById(R.id.redCategory06);
		Category test7 = (Category) findViewById(R.id.redCategory07);
		Category test8 = (Category) findViewById(R.id.redCategory08);*/ 
		
		test.setBackgroundColor(Color.RED);
		test1.setBackgroundColor(Color.GREEN);
		test2.setBackgroundColor(Color.BLUE);
		/*test3.setBackgroundColor(Color.WHITE);
		test4.setBackgroundColor(Color.YELLOW);
		test5.setBackgroundColor(Color.GREEN);
		test6.setBackgroundColor(Color.RED);
		test7.setBackgroundColor(Color.BLUE);
		test8.setBackgroundColor(Color.BLACK);*/ 

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
}
