package glen.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentRow0 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_row0, container, false);
	}
	
	public void updateDisplay(double number) {
		//not sure if getView will give the correct View to search from here
		TextView display = (TextView) getView().findViewById(R.id.display);
		display.setText(Double.toString(number));
	}

}
