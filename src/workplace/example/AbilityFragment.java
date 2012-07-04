package workplace.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;

public class AbilityFragment extends Fragment {

	private CharacterSheet cs;

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

		OnLongClickListener listener = new OnLongClickListener() {

			public boolean onLongClick(View v) {
				Property prop = (Property) ((AbilityCellView) v).getProperty();
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle(prop.getName());
				builder.setMessage(prop.getSubProperties());
				builder.setNeutralButton("OK", new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
				return true;
			}
		};

		AbilityCellView acv = (AbilityCellView) view.findViewById(R.id.acvEro);
		acv.setProperty(cs.getProperty(PropertyType.Erõ));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvGy);
		acv.setProperty(cs.getProperty(PropertyType.Gyorsaság));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvU);
		acv.setProperty(cs.getProperty(PropertyType.Ügyesség));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvAll);
		acv.setProperty(cs.getProperty(PropertyType.Állóképesség));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvE);
		acv.setProperty(cs.getProperty(PropertyType.Egészség));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvSzep);
		acv.setProperty(cs.getProperty(PropertyType.Szépség));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvInt);
		acv.setProperty(cs.getProperty(PropertyType.Intelligencia));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvAk);
		acv.setProperty(cs.getProperty(PropertyType.Akaraterõ));
		acv.setOnLongClickListener(listener);

		acv = (AbilityCellView) view.findViewById(R.id.acvAszt);
		acv.setProperty(cs.getProperty(PropertyType.Asztrál));
		acv.setOnLongClickListener(listener);

		

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// tvValue.setText(String.valueOf(cs.getProperty(PropertyType.Erõ).getValue()));

	}
}
