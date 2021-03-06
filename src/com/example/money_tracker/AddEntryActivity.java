package com.example.money_tracker;

import java.util.Date;

import com.money_tracker.dao.CategoryDao;
import com.money_tracker.dao.EntryDao;
import com.money_tracker.dao.LocationDao;
import com.money_tracker.entities.Entry;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class AddEntryActivity extends ActionBarActivity {

	private CategoryDao datasource;
	private EntryDao entrysource;
	private LocationDao locationsource;
	private int value;
	private TextView txtResult; // Reference to EditText of result
	private int result = 0; // Result of computation
	private String inStr = "0"; // Current input string
	// Previous operator: '+', '-', '*', '/', '=' or ' ' (no operator)
	private char lastOperator = ' ';
	double currentlat;
	private CheckBox chkIos;
	double currentlong;
	private boolean locationEnabled = false;

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(AddEntryActivity.this, MainActivity.class));
		finish();

	}

	public void addListenerOnChkIos() {

		chkIos = (CheckBox) findViewById(R.id.chkIos);

		chkIos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// is chkIos checked?
				if (((CheckBox) v).isChecked()) {
					locationEnabled = true;
				} else {
					locationEnabled = false;
				}

			}
		});

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		datasource = new CategoryDao(this);
		datasource.open();
		entrysource = new EntryDao(this);
		entrysource.open();
		locationsource = new LocationDao(this);
		locationsource.open();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_entry);
		addListenerOnChkIos();
		// Retrieve a reference to the EditText field for displaying the result.
		txtResult = (TextView) findViewById(R.id.txtResultId);
		txtResult.setText("0");
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			value = Integer.parseInt(extras.getString("new_variable_name"));
		}

		// Register listener (this class) for all the buttons
		BtnListener listener = new BtnListener();

		((Button) findViewById(R.id.btnNum0Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum1Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum2Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum3Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum4Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum5Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum6Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum7Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum8Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnNum9Id)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnAddId)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnDoteId)).setOnClickListener(listener);
		((Button) findViewById(R.id.btnClearId)).setOnClickListener(listener);

	}

	private class BtnListener implements OnClickListener {
		// On-click event handler for all the buttons
		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			// Number buttons: '0' to '9'
			case R.id.btnNum0Id:
			case R.id.btnNum1Id:
			case R.id.btnNum2Id:
			case R.id.btnNum3Id:
			case R.id.btnNum4Id:
			case R.id.btnNum5Id:
			case R.id.btnNum6Id:
			case R.id.btnNum7Id:
			case R.id.btnNum8Id:
			case R.id.btnNum9Id:
			case R.id.btnDoteId:
				String inDigit = ((Button) view).getText().toString();
				if (txtResult.getText().toString().contains(".")
						&& inDigit.equals("."))
					break;

				if (txtResult.length() == 11)
					break;

				if (inStr.equals("0")) {
					inStr = inDigit; // no leading zero
				} else {
					inStr += inDigit; // accumulate input digit
				}

				txtResult.setText(inStr);
				break;

			// inStr = ".";
			// txtResult.setText(inStr);
			// break;

			// Operator buttons: '+', '-', '*', '/' and '='
			case R.id.btnAddId:

				if (locationEnabled) {

					GPSLocationTracker mGpsLocationTracker = new GPSLocationTracker(
							AddEntryActivity.this);
					if (mGpsLocationTracker.canGetLocation()) {
						// if marked true
						Entry added = compute(Double.parseDouble(txtResult
								.getText().toString()), value);
						/**
						 * Set GPS Location fetched address
						 */

						currentlat = mGpsLocationTracker.getLatitude();
						currentlong = mGpsLocationTracker.getLongitude();
						locationsource.createLocation(
								String.valueOf(currentlat),
								String.valueOf(currentlong), added.getId());
						if (added != null) {
							Toast.makeText(AddEntryActivity.this,
									"Entry Added", Toast.LENGTH_SHORT).show();
							Intent i = new Intent(getBaseContext(),
									MainActivity.class);
							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
									| Intent.FLAG_ACTIVITY_CLEAR_TASK);

							startActivity(i);
						} else {
							Toast.makeText(AddEntryActivity.this,
									"Entry couldn't be added, try again",
									Toast.LENGTH_SHORT).show();
						}

					}

					else {
						mGpsLocationTracker.showSettingsAlert();
					}
				} else {
					Entry added = compute(
							Double.parseDouble(txtResult.getText().toString()),
							value);
					if (added != null) {
						Toast.makeText(AddEntryActivity.this,
								"Entry Added", Toast.LENGTH_SHORT).show();
						Intent i = new Intent(getBaseContext(),
								MainActivity.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TASK);

						startActivity(i);
					} else {
						Toast.makeText(AddEntryActivity.this,
								"Entry couldn't be added, try again",
								Toast.LENGTH_SHORT).show();
					}

				}

				lastOperator = '+';

				break;
			// Clear button
			case R.id.btnClearId:
				result = 0;
				inStr = "0";
				lastOperator = ' ';
				txtResult.setText("0");
				break;
			}
		}

		// User pushes '+', '-', '*', '/' or '=' button.
		// Perform computation on the previous result and the current input
		// number,
		// based on the previous operator.
		private Entry compute(double amount, int category_id) {

			// save the new comment to the database
			Date now = new Date();

			Entry entry = entrysource.createEntry(amount, category_id,
					String.valueOf(now.getTime()));

			return (entry);

		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_entry, menu);
		return true;
	}

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
