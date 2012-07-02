package workplace.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AbilityFragment extends Fragment {

	private CharacterSheet cs;

	private TextView tvTitle;
	private TextView tvValue;

	public AbilityFragment(CharacterSheet cs) {
		this.cs = cs;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.ability, container, false);
		tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		tvValue = (TextView) view.findViewById(R.id.tvValue);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		tvValue.setText(String.valueOf(cs.getProperty(PropertyType.Erõ).getValue()));
	}
}
