package workplace.example;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AbilityCellView extends LinearLayout {
	View view;
	BaseProperty property;

	public AbilityCellView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.abilitycell, this);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AbilityCellView);

		String myText = a.getString(R.styleable.AbilityCellView_myText);
		if (myText != null) {
			((TextView) view.findViewById(R.id.tvTitle)).setText(myText);
		}

		a.recycle();
		
	}

	public void setProperty(BaseProperty property) {

		this.property = property;
		this.Refresh();
		// fel kéne iratkozni Refreh eseményre
	}

	public BaseProperty getProperty() {
		return property;
	}

	public void Refresh() {
		((TextView) view.findViewById(R.id.tvValue)).setText(String.valueOf(property.getValue()));
	}
}
