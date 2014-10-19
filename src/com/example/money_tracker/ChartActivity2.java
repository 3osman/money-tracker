package com.example.money_tracker;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.money_tracker.dao.CategoryDao;
import com.money_tracker.dao.EntryDao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ChartActivity2 extends ActionBarActivity {

	private CategoryDao datasource;
	private EntryDao entrysource;
	private double amount;
	private float percentage;
	private double total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart_activity2);

		PieChart chart = (PieChart) findViewById(R.id.chart2);
        chart.setUsePercentValues(true);


		datasource = new CategoryDao(this);
		datasource.open();
		entrysource = new EntryDao(this);
		entrysource.open();

		ArrayList<Entry> yVals1 = new ArrayList<Entry>();

		total = datasource.getCategorySum(0) + datasource.getCategorySum(1);

		amount = datasource.getCategorySum(0);
		if (amount != 0.0) {
			percentage = (float) ((amount * 100) / total);
			Entry c1e1 = new Entry(percentage, 0); // 0 == quarter 1
			yVals1.add(c1e1);
		}
		amount = datasource.getCategorySum(1);
		if (amount != 0.0) {
			percentage = (float) ((amount * 100) / total);
			Entry c1e2 = new Entry(percentage, 1); // 1 == quarter 2 ...
			yVals1.add(c1e2);
		}

		ArrayList<String> xVals = new ArrayList<String>();
		xVals.add("Income");
		xVals.add("Other");

		PieDataSet set1 = new PieDataSet(yVals1, "Outcome Results");
		set1.setSliceSpace(3f);
		ArrayList<Integer> colors = new ArrayList<Integer>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		set1.setColors(colors);

		PieData data = new PieData(xVals, set1);
		chart.setData(data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart_activity2, menu);
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
