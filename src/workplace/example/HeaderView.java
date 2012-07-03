package workplace.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeaderView extends LinearLayout {
	View view;

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.header, this);

	}

	public void setTitle(String title) {
		((TextView) view.findViewById(R.id.textView1)).setText(title);
	}
}
