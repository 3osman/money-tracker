package com.money_tracker.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MoneyTrackerSqlInit extends SQLiteOpenHelper {
	public static final String TABLE_ENTRIES = "entries";
	public static final String TABLE_CATEGORIES = "categories";
	public static final String TABLE_LOCATIONS = "locations";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CATEGORYID = "category_id";
	public static final String COLUMN_ENTRYID = "entry_id";
	public static final String COLUMN_AMOUNT = "amount";
	public static final String FIELD_LAT = "lat";

	public static final String FIELD_LNG = "lng";

	public static final String FIELD_ZOOM = "zom";

	public static final String COLUMN_NAME = "name";

	private static final String DATABASE_NAME = "money_tracker.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String CATEGORIES_CREATE = "create table "
			+ TABLE_CATEGORIES + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null);";

	private static final String ENTRIES_CREATE = "create table "
			+ TABLE_ENTRIES + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_AMOUNT
			+ " REAL not null, " + COLUMN_CATEGORYID
			+ " int not null REFERENCES categories(_id) on UPDATE CASCADE);";

	private static final String LOCATIONS_CREATE = "create table "
			+ TABLE_LOCATIONS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + FIELD_LAT + " text, "
			+ FIELD_LNG + " text, " + FIELD_ZOOM + " text, " + COLUMN_ENTRYID
			+ " int not null REFERENCES entries(_id) on UPDATE CASCADE);";

	public MoneyTrackerSqlInit(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CATEGORIES_CREATE);
		database.execSQL(ENTRIES_CREATE);
		database.execSQL(LOCATIONS_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MoneyTrackerSqlInit.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
		onCreate(db);
	}
}
