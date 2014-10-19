package com.money_tracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.money_tracker.entities.Entry;

public class EntryDao {
	// Database fields
	private SQLiteDatabase database;
	private MoneyTrackerSqlInit dbHelper;
	private String[] allColumns = { MoneyTrackerSqlInit.COLUMN_ID,
			MoneyTrackerSqlInit.COLUMN_CATEGORYID,
			MoneyTrackerSqlInit.COLUMN_AMOUNT, MoneyTrackerSqlInit.COLUMN_DATE };

	public EntryDao(Context context) {
		dbHelper = new MoneyTrackerSqlInit(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Entry createEntry(double amount, int category_id, int date) {
		ContentValues values = new ContentValues();
		values.put(MoneyTrackerSqlInit.COLUMN_CATEGORYID, category_id + "");
		values.put(MoneyTrackerSqlInit.COLUMN_AMOUNT, amount + "");
		values.put(MoneyTrackerSqlInit.COLUMN_DATE, date);

		long insertId = database.insert(MoneyTrackerSqlInit.TABLE_ENTRIES,
				null, values);
		Cursor cursor = database.query(MoneyTrackerSqlInit.TABLE_ENTRIES,
				allColumns, MoneyTrackerSqlInit.COLUMN_ID + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		Entry newEntry = cursorToEntry(cursor);
		cursor.close();
		return newEntry;
	}

	public String getValfromId(long id){
		Cursor cursor = database
				.rawQuery("SELECT amount FROM entries where _id = " + id
						+ ";", null);
		if (cursor.moveToFirst()) {
			return String.valueOf(cursor.getDouble(0));
		} else {
			return "";
		}
	}
	public String getCategoryById(long id) {
		int cat_id = -1;
		
		Cursor cursor = database
				.rawQuery("SELECT category_id FROM entries where _id = " + id
						+ ";", null);
		if (cursor.moveToFirst()) {
			cat_id = cursor.getInt(0);
		} else {
			cat_id = -1;
		}
		switch(cat_id){
		case 0:
			return "Salary";
			
		case 1:
			return "Other-In";
		case 2:
			return "Food";
		case 3:
			return "House";
		case 4:
			return "Entertainment";
		case 5:
			return "Medical";
		case 6:
			return "Shopping";
		case 7:
			return "Transport";
		case 8:
			return "Other-Out";
		case -1:
			return "";
		}
		return "";
	}

	public void deleteEntry(Entry entry) {
		long id = entry.getId();
		System.out.println("Entry deleted with id: " + id);
		database.delete(MoneyTrackerSqlInit.TABLE_ENTRIES,
				MoneyTrackerSqlInit.COLUMN_ID + " = " + id, null);
	}

	public List<Entry> getAllEntries() {
		List<Entry> entries = new ArrayList<Entry>();

		Cursor cursor = database.query(MoneyTrackerSqlInit.TABLE_ENTRIES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Entry entry = cursorToEntry(cursor);
			entries.add(entry);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return entries;
	}

	private Entry cursorToEntry(Cursor cursor) {
		Entry entry = new Entry();
		entry.setId(cursor.getLong(0));
		entry.setCategoryId(Integer.parseInt(cursor.getString(1)));
		entry.setAmount(Double.parseDouble(cursor.getString(2)));
		entry.setDate(Integer.parseInt(cursor.getString(2)));

		return entry;
	}
}
