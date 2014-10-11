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
			MoneyTrackerSqlInit.COLUMN_CATEGORYID + "",
			MoneyTrackerSqlInit.COLUMN_AMOUNT + "" };

	public EntryDao(Context context) {
		dbHelper = new MoneyTrackerSqlInit(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Entry createEntry(double amount, int category_id) {
		ContentValues values = new ContentValues();
		values.put(MoneyTrackerSqlInit.COLUMN_CATEGORYID + "", category_id + "");
		values.put(MoneyTrackerSqlInit.COLUMN_AMOUNT + "", amount + "");
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
		return entry;
	}
}
