package workplace.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridPager extends FragmentActivity implements OnItemClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);

		GridView gvGrid = (GridView) findViewById(R.id.gridView1);

		gvGrid.setOnItemClickListener(this);

		gvGrid.setAdapter(new ImageAdapter(this));

	}

	public class ImageAdapter extends BaseAdapter {
		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			return mThumbIds.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(mContext);
				// imageView.setLayoutParams(new
				// GridView.LayoutParams(LayoutParams.MATCH_PARENT,
				// LayoutParams.WRAP_CONTENT));

				Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.magus_lap_1);

				imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.FILL_PARENT, b.getHeight() * parent.getWidth()
						/ 3 / b.getWidth()));
				imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

			} else {
				imageView = (ImageView) convertView;
			}

			// LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
			// LayoutParams.WRAP_CONTENT);
			// imageView.setLayoutParams(new GridView.LayoutParams(params));

			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}

		// references to our images
		private Integer[] mThumbIds = { R.drawable.magus_lap_1, R.drawable.magus_lap_1, R.drawable.magus_lap_1,
				R.drawable.magus_lap_1, R.drawable.magus_lap_1, R.drawable.magus_lap_1 };
	}

	// Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
	// Intent intent = new Intent(mContext.getApplicationContext(),
	// GridImageActivity.class);
	// intent.putExtra("index", position);
	// mContext.startActivity(intent);

	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		// Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
		// Intent intent = new Intent(this, MAGUSActivity.class);
		// intent.putExtra("index", index);
		// this.startActivity(intent);

		// RETURN!!

		Intent data = new Intent();
		data.putExtra("index", index);
		if (getParent() == null) {
			setResult(Activity.RESULT_OK, data);
		} else {
			getParent().setResult(Activity.RESULT_OK, data);
		}
		finish();
	}

	// public class MyAdapter extends BaseAdapter {
	// ArrayList<Fragment> list;
	// private Context mContext;
	//
	// public MyAdapter(Context c) {
	// mContext = c;
	//
	// list = new ArrayList<Fragment>();
	// FirstFragment frag = new FirstFragment(false);
	// list.add(frag);
	// frag = new FirstFragment(false);
	// list.add(frag);
	//
	// }
	//
	// public int getCount() {
	// return list.size();
	// }
	//
	// public Object getItem(int position) {
	// return list.get(position);
	// }
	//
	// public long getItemId(int position) {
	// return list.get(position).getId();
	// }
	//
	// public View getView(int position, View convertView, ViewGroup parent) {
	// View view;
	// if (convertView == null) {
	// view = list.get(position).onCreateView(getLayoutInflater(), parent,
	// null);
	// // view = list.get(position).getView();
	// } else {
	// view = convertView;
	// }
	// return view;
	// }
	// }
}
