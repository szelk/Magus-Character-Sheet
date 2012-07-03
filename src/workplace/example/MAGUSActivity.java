package workplace.example;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

public class MAGUSActivity extends FragmentActivity { // OnClickListener

	CharacterSheet cs;

	ViewPager mPager;
	MyPagerAdapter adapter;
	boolean isJustStarted;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		cs = new CharacterSheet(1, 0, new int[] { 12, 12, 12, 12, 12, 12, 12, 12, 12 }, getResources());
		cs.setName("Canon");
		cs.setAge(23);

		HeaderView header = (HeaderView) findViewById(R.id.header);
		header.setTitle(cs.toString());

		isJustStarted = true;

		adapter = new MyPagerAdapter(getSupportFragmentManager());

		adapter.AddFragment(new AbilityFragment(cs));
		adapter.AddFragment(new FirstFragment(false));
		adapter.AddFragment(new AbilityFragment(cs));

		mPager = (ViewPager) findViewById(R.id.pager);

		mPager.setAdapter(adapter);
		mPager.setCurrentItem(0);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (isJustStarted) {
			isJustStarted = false;
			// Intent intent = new Intent(this, GridPager.class);
			// startActivityForResult(intent, 0);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		if (resultCode == Activity.RESULT_OK) {
			int index = intent.getIntExtra("index", 0);
			mPager.setCurrentItem(index);
		}

	}

	// public void onClick(View v) {
	// ((FirstFragment)
	// adapter.getItem(mPager.getCurrentItem())).myClickMethod(v);
	// }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			Intent intent = new Intent(this, GridPager.class);
			startActivityForResult(intent, 0);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	class MyPagerAdapter extends FragmentPagerAdapter {

		ArrayList<Fragment> fragments = new ArrayList<Fragment>();

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public void AddFragment(Fragment fragment) {
			fragments.add(fragment);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public Fragment getItem(int index) {
			return fragments.get(index);
		}
	}
}