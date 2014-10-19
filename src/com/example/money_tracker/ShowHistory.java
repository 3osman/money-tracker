package com.example.money_tracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.money_tracker.dao.CategoryDao;
import com.money_tracker.dao.EntryDao;
import com.money_tracker.entities.Entry;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowHistory extends ActionBarActivity {
	private CategoryDao datasource;
	private EntryDao entrysource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);

		datasource = new CategoryDao(this);
		datasource.open();
		entrysource = new EntryDao(this);
		entrysource.open();
		TableLayout tl = (TableLayout) findViewById(R.id.tvt_tableview);
	
		// Go through each item in the array
		for (Entry e : entrysource.getAllEntries()) {
			// Create a TableRow and give it an ID
			TableRow tr = new TableRow(this);

			TableLayout.LayoutParams rp = new TableLayout.LayoutParams(
					TableLayout.LayoutParams.MATCH_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT, 1f);
			rp.setMargins(0, 15, 5, 15);

			tr.setGravity(Gravity.CENTER_VERTICAL);
			tr.setPadding(6, 15, 6, 15);
			tr.setLayoutParams(rp);
			if (e.getCategoryId() < 2) {
				tr.setBackgroundResource(R.drawable.roundcornerg);
			} else {
				tr.setBackgroundResource(R.drawable.roundcorner);

			}

			RelativeLayout rl = new RelativeLayout(this);
			rl.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.WRAP_CONTENT,
					TableRow.LayoutParams.WRAP_CONTENT));

			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
					RelativeLayout.TRUE);
		//	params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

			// Create a TextView to house the name of the province
			TextView labelTV = new TextView(this);
			// labelTV.setId(200+current);
			labelTV.setText(String.valueOf(e.getAmount()));
			labelTV.setTypeface(null, Typeface.BOLD);

			labelTV.setTextColor(Color.BLACK);
			labelTV.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.FILL_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT));
			labelTV.setLayoutParams(params);
			rl.addView(labelTV);

			params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
			// Create a TextView to house the value of the after-tax income
			TextView valueTV = new TextView(this);
			// valueTV.setId(current);
			Date date = new Date(e.getDate());
			valueTV.setTypeface(null, Typeface.BOLD);

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String formatted = format.format(date);
			valueTV.setText(formatted);
			valueTV.setTextColor(Color.BLACK);
			valueTV.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.FILL_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT));
			valueTV.setLayoutParams(params);

			rl.addView(valueTV);
			params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
					RelativeLayout.TRUE);
			params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

			String cat = "";
			switch ((int) e.getCategoryId()) {
			case 0:
				cat = "Salary";
				break;
			case 1:
				cat = "Other-In";
				break;
			case 2:
				cat = "Food";
				break;
			case 3:
				cat = "House";
				break;
			case 4:
				cat = "Entertainment";
				break;
			case 5:
				cat = "Medical";
				break;
			case 6:
				cat = "Shopping";
				break;
			case 7:
				cat = "Transport";
				break;
			case 8:
				cat = "Other-Out";
				break;
			}

			TextView category = new TextView(this);
			// valueTV.setId(current);

			category.setText(cat);
			category.setTypeface(null, Typeface.BOLD);

			category.setTextColor(Color.BLACK);
			category.setLayoutParams(new TableRow.LayoutParams(
					TableRow.LayoutParams.FILL_PARENT,
					TableRow.LayoutParams.WRAP_CONTENT));
			category.setLayoutParams(params);

			rl.addView(category);
			tr.addView(rl);
			// Add the TableRow to the TableLayout
			tl.addView(tr, new TableLayout.LayoutParams(
					TableLayout.LayoutParams.FILL_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
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
