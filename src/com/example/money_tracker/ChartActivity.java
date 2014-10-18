package com.example.money_tracker;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ChartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);

		PieChart chart = (PieChart) findViewById(R.id.chart);

		ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
		ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

		Entry c1e1 = new Entry(100.000f, 0); // 0 == quarter 1
		valsComp1.add(c1e1);
		Entry c1e2 = new Entry(50.000f, 1); // 1 == quarter 2 ...
		valsComp1.add(c1e2);
		// and so on ...

		Entry c2e1 = new Entry(120.000f, 0); // 0 == quarter 1
		valsComp2.add(c2e1);
		Entry c2e2 = new Entry(110.000f, 1); // 1 == quarter 2 ...
		valsComp2.add(c2e2);
		// ...

		PieDataSet setComp1 = new PieDataSet(valsComp1, "Company 1");
		PieDataSet setComp2 = new PieDataSet(valsComp2, "Company 2");

		ArrayList<PieDataSet> dataSets = new ArrayList<PieDataSet>();
		dataSets.add(setComp1);
		dataSets.add(setComp2);

		ArrayList<String> xVals = new ArrayList<String>();
		xVals.add("20");
		xVals.add("30");
		xVals.add("10");
		xVals.add("40");

		PieData data = new PieData(xVals);
		chart.setData(data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chart, menu);
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