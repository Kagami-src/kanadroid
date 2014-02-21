package com.kagami.kanadroid;

import com.kagami.kanadroid.format.KanaListAdapter;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private boolean isFullScreen = false;
	private int mThemeid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			if (savedInstanceState.getInt("theme", -1) != -1) {
				mThemeid = savedInstanceState.getInt("theme");
				this.setTheme(mThemeid);
			}
		}
		setContentView(R.layout.activity_view);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle("cl");
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("op");
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// List
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		KanaApplication app = (KanaApplication) getApplication();
		KanaListAdapter adapter = new KanaListAdapter(app.getData());
		mDrawerList.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.action_fullscreen);
		if (isFullScreen)
			item.setIcon(R.drawable.ic_action_return_from_full_screen);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		int fullop = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		switch (id) {
		case R.id.action_fullscreen:
			if (!isFullScreen) {
				int uiop = getWindow().getDecorView().getSystemUiVisibility();
				int newuiop = uiop | fullop;
				getWindow().getDecorView().setSystemUiVisibility(newuiop);
				item.setIcon(R.drawable.ic_action_return_from_full_screen);
			} else {
				int uiop = getWindow().getDecorView().getSystemUiVisibility();
				int newuiop = ~fullop & uiop;
				getWindow().getDecorView().setSystemUiVisibility(newuiop);
				item.setIcon(R.drawable.ic_action_full_screen);
			}

			Log.d("kagami", "full");
			isFullScreen = !isFullScreen;
			break;
		case R.id.action_theme:
			if (mThemeid == R.style.AppTheme_Dark)
				mThemeid = R.style.AppTheme_Light;
			else
				mThemeid = R.style.AppTheme_Dark;
			recreate();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("theme", mThemeid);
	}

}
