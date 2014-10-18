package com.money_tracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.money_tracker.entities.Location;

public class LocationDao {
	// Database fields
	private SQLiteDatabase database;
	private MoneyTrackerSqlInit dbHelper;
	private String[] allColumns = { MoneyTrackerSqlInit.COLUMN_ID,
			MoneyTrackerSqlInit.FIELD_LAT, MoneyTrackerSqlInit.FIELD_LAT,
			MoneyTrackerSqlInit.FIELD_ZOOM };

	public LocationDao(Context context) {
		dbHelper = new MoneyTrackerSqlInit(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Location createLocation(String lat, String lng, String zoom,
			long entry_id) {
		ContentValues values = new ContentValues();
		values.put(MoneyTrackerSqlInit.COLUMN_ENTRYID, entry_id + "");
		values.put(MoneyTrackerSqlInit.FIELD_LAT, lat);
		values.put(MoneyTrackerSqlInit.FIELD_LNG, lng);
		values.put(MoneyTrackerSqlInit.FIELD_ZOOM, zoom);
		long insertId = database.insert(MoneyTrackerSqlInit.TABLE_LOCATIONS,
				null, values);
		Cursor cursor = database.query(MoneyTrackerSqlInit.TABLE_LOCATIONS,
				allColumns, MoneyTrackerSqlInit.COLUMN_ID + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		Location newEntry = cursorToLocation(cursor);
		cursor.close();
		return newEntry;
	}

	public void deleteLocation(Location entry) {
		long id = entry.getId();
		System.out.println("Entry deleted with id: " + id);
		database.delete(MoneyTrackerSqlInit.TABLE_LOCATIONS,
				MoneyTrackerSqlInit.COLUMN_ID + " = " + id, null);
	}

	public List<Location> getAllLocations() {
		List<Location> entries = new ArrayList<Location>();

		Cursor cursor = database.query(MoneyTrackerSqlInit.TABLE_LOCATIONS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Location entry = cursorToLocation(cursor);
			entries.add(entry);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return entries;
	}

	private Location cursorToLocation(Cursor cursor) {
		Location entry = new Location();
		entry.setId(cursor.getLong(0));
		entry.setEntryId(Integer.parseInt(cursor.getString(1)));
		entry.setLat(cursor.getString(2));
		entry.setLng(cursor.getString(3));
		entry.setZoom(cursor.getString(4));
		return entry;
	}
}
