package com.money_tracker.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.money_tracker.entities.Category;

public class CategoryDao {
	// Database fields
	private SQLiteDatabase database;
	private MoneyTrackerSqlInit dbHelper;
	private String[] allColumns = { MoneyTrackerSqlInit.COLUMN_ID,
			MoneyTrackerSqlInit.COLUMN_NAME };

	public CategoryDao(Context context) {
		dbHelper = new MoneyTrackerSqlInit(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Category createCategory(String name) {
		ContentValues values = new ContentValues();
		values.put(MoneyTrackerSqlInit.COLUMN_NAME, name);
		long insertId = database.insert(MoneyTrackerSqlInit.TABLE_CATEGORIES,
				null, values);
		Cursor cursor = database.query(MoneyTrackerSqlInit.TABLE_CATEGORIES,
				allColumns, MoneyTrackerSqlInit.COLUMN_ID + " = " + insertId,
				null, null, null, null);
		cursor.moveToFirst();
		Category newCategory = cursorToCategory(cursor);
		cursor.close();
		return newCategory;
	}

	public void deleteCategory(Category category) {
		long id = category.getId();
		System.out.println("Category deleted with id: " + id);
		database.delete(MoneyTrackerSqlInit.TABLE_CATEGORIES,
				MoneyTrackerSqlInit.COLUMN_ID + " = " + id, null);
	}

	public double getCategorySum(int id) {
		Cursor cursor = database.rawQuery(
				"SELECT SUM(amount) FROM entries where category_id = " + id
						+ ";", null);
		if (cursor.moveToFirst()) {
			return cursor.getInt(0);
		} else {
			return 0;
		}

	}

	public List<Category> getAllCategorys() {
		List<Category> categories = new ArrayList<Category>();

		Cursor cursor = database.query(MoneyTrackerSqlInit.TABLE_CATEGORIES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Category category = cursorToCategory(cursor);
			categories.add(category);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return categories;
	}

	private Category cursorToCategory(Cursor cursor) {
		Category category = new Category();
		category.setId(cursor.getLong(0));
		category.setName(cursor.getString(1));
		return category;
	}
}
