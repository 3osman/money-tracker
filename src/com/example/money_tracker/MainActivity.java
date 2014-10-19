package com.example.money_tracker;

import com.money_tracker.dao.CategoryDao;
import android.view.View.OnClickListener;

import com.money_tracker.dao.EntryDao;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {
	private CategoryDao datasource;
	private EntryDao entrysource;
	private TextView txtResult;
	private String amount;
	ImageButton imageButton;

	public void addListenerOnButtons() {

		Button button = (Button) findViewById(R.id.btnViewChart);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(getBaseContext(), TabBarActivity.class);
				startActivity(i);

			}

		});

		imageButton = (ImageButton) findViewById(R.id.zero);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);

				i.putExtra("new_variable_name", "0");
				startActivity(i);

			}

		});
		imageButton = (ImageButton) findViewById(R.id.one);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "1");
				startActivity(i);

			}

		});
		imageButton = (ImageButton) findViewById(R.id.two);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "2");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.three);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "3");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.four);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "4");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.five);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "5");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.six);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "6");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.seven);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "7");
				startActivity(i);
			}

		});
		imageButton = (ImageButton) findViewById(R.id.eight);

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getBaseContext(), AddEntryActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_CLEAR_TASK);
				i.putExtra("new_variable_name", "8");
				startActivity(i);
			}

		});

	}

	public void setValueCategories() {
		txtResult = (TextView) findViewById(R.id.salary_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(0) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.other_in_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(1) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.food_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(2) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.house_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(3) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.entertainment_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(4) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.medical_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(5) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.shopping_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(6) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.transport_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(7) + "$";
		txtResult.setText(amount);

		txtResult = (TextView) findViewById(R.id.other_txt);
		txtResult.setTypeface(null, Typeface.BOLD);
		amount = datasource.getCategorySum(8) + "$";
		txtResult.setText(amount);

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
		setValueCategories();
		double diff = datasource.getDifference();
		txtResult = (TextView) findViewById(R.id.details);
		txtResult.setTypeface(null, Typeface.BOLD);
		txtResult.setText(diff + "$");
		if (diff < 0) {
			txtResult.setBackgroundResource(R.drawable.roundcorner);
			//b.setBackground(getResources().getDrawable(R.drawable.roundcorner));

		//	em.setBackground(().getDrawable(R.drawable.roundcorner));

		} else {
			txtResult.setBackgroundResource(R.drawable.roundcornerg);
			//b.setBackground(getResources().getDrawable(R.drawable.roundcornerg));
			//em.setBackground(getResources().getDrawable(R.drawable.roundcornerg));


		}
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
