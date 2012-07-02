package workplace.example;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FirstFragment extends Fragment {

	// commment: if it false, then it HASN'T functionality
	private boolean isReal;
	private ArrayList<String> itemArray;
	private ArrayAdapter<String> itemAdapter;

	private Button btnAdd;
	private ListView lvList;

	public FirstFragment(boolean isReal) {
		this.isReal = isReal;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page01, container, false);
		lvList = (ListView) view.findViewById(R.id.lvList);
		btnAdd = (Button) view.findViewById(R.id.btnAdd);

		itemArray = new ArrayList<String>();
		itemArray.clear();

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		itemAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemArray);
		lvList.setAdapter(itemAdapter);
		
		//btnAdd.setOnClickListener((MAGUSActivity)getActivity());
	}

	public void myClickMethod(View v) {
		switch (v.getId()) {
		case R.id.btnAdd:
			itemArray.add("new element");
			itemAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
}
