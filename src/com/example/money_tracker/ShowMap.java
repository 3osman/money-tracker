package com.example.money_tracker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.money_tracker.dao.CategoryDao;
import com.money_tracker.dao.EntryDao;
import com.money_tracker.dao.LocationDao;
import com.money_tracker.entities.Location;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ShowMap extends FragmentActivity {
	GoogleMap mMap;
	private CategoryDao datasource;
	private EntryDao entrysource;
	private LocationDao locationsource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_entries);
		datasource = new CategoryDao(this);
		datasource.open();
		entrysource = new EntryDao(this);
		entrysource.open();
		locationsource = new LocationDao(this);
		locationsource.open();
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mMap = fm.getMap();
		GPSLocationTracker mGpsLocationTracker = new GPSLocationTracker(
				ShowMap.this);

		/**
		 * Set GPS Location fetched address
		 */
		if (mGpsLocationTracker.canGetLocation()) {

			LatLng ll = new LatLng(mGpsLocationTracker.getLatitude(),
					mGpsLocationTracker.getLongitude());

			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
		}
		for (Location l : locationsource.getAllLocations()) {
			if (l.getEntryId() < 2) {
				mMap.addMarker(new MarkerOptions()
						.position(
								new LatLng(Double.parseDouble(l.getLat()),
										Double.parseDouble(l.getLng())))
						.title(entrysource.getCategoryById(l.getEntryId())
								+ " , "
								+ entrysource.getValfromId(l.getEntryId())
								+ "$")
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
			} else {
				mMap.addMarker(new MarkerOptions()
						.position(
								new LatLng(Double.parseDouble(l.getLat()),
										Double.parseDouble(l.getLng())))
						.title(entrysource.getCategoryById(l.getEntryId())
								+ " , "
								+ entrysource.getValfromId(l.getEntryId())
								+ "$")
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
			}
		}
	}
}