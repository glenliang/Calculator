package glen.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	double val0, val1;
	char operator;
	boolean decimal, edit0, edit1;
	int decimalPlace;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Remove the action bar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        
        //instantiate fields
        val0 = 0;
        val1 = 0;
        clearFields();

        /* I'm not sure what this does yet, but probably won't need it for this simple app.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    // Handles the case when an operation OP is pressed.
    private void operation(char op) {
    	if (edit1) { // if we're editing val1, then there must be a stored operator already
    		val0 = calculate();
    		val1 = 0;
    		clearFields();
    		operator = op;
    		edit1 = true;
    		display(val0);
    	} else {
    		clearFields();
    		operator = op;
    		edit1 = true;
    		display(val0);
    	}
    }
    
    // Calculates the result of val0 and val1 acted by operator.
    private double calculate() {
    	switch (operator) {
    	case '+':
    		return val0+val1;
    	case '-':
    		return val0-val1;
    	case '*':
    		return val0*val1;
    	case '/':
    		return val0/val1;
    	default:
    		return 0;
    	}
    }
    
    private void onNumber(int num){
		if (edit0) { // we are currently editing val0
			if (decimal) {
				val0 += num * Math.pow(0.1, decimalPlace);
				decimalPlace++;
			} else {
				val0 = val0*10 + num;
			}
			display(val0);
		} else if (edit1) { // we are currently editing val1
			if (decimal) {
				val1 += num * Math.pow(0.1, decimalPlace);
				decimalPlace++;
			} else {
				val1 = val1*10 + num;
			}
			display(val1);
		} else { // we are going to begin editing val0
			edit0 = true;
			val0 = num;
			display(val0);
		}
    }
    
    // Push VAL to the TextView object to be displayed.
    private void display(double val){
    	TextView mView = (TextView) findViewById(R.id.display);
    	mView.setText(Double.toString(val));
    }
    
    // Clears the fields operator, decimal, decimalPlace, edit0, and edit1
    private void clearFields() {
    	operator = ' ';
    	decimal = false;
    	edit0 = false;
    	edit1 = false;
    	decimalPlace = 0;
    }
    
    public void onZero(View view) {
    	onNumber(0);
    }
    public void onOne(View view) {
    	onNumber(1);
    }
    public void onTwo(View view) {
    	onNumber(2);
    }
    public void onThree(View view) {
    	onNumber(3);
    }
    public void onFour(View view) {
    	onNumber(4);
    }
    public void onFive(View view) {
    	onNumber(5);
    }
    public void onSix(View view) {
    	onNumber(6);
    }
    public void onSeven(View view) {
    	onNumber(7);
    }
    public void onEight(View view) {
    	onNumber(8);
    }
    public void onNine(View view) {
    	onNumber(9);
    }
    public void onPlus(View view) {
    	operation('+');
    }
    public void onMinus(View view) {
    	operation('-');
    }
    public void onTimes(View view) {
    	operation('*');
    }
    public void onDivide(View view) {
    	operation('/');
    }
    public void onEqual(View view) {
		if (operator != ' ') {
			val0 = calculate();
			val1 = 0;
			clearFields();
		}
		display(val0);
    }
    public void onDecimal(View view) {
		if (!decimal)
			decimalPlace++;
		decimal = true;
		if (!edit0 && !edit1)
			edit0 = true;
    }
    public void onClear(View view) {
		if (edit1) {
			val1 = 0;
		} else {
			val0 = 0;
		}
		decimal = false;
		decimalPlace = 0;
		display(0);
    }    
    public void onDelete(View view) {
		if (edit0) {
			if (decimal && decimalPlace!=1) {
				decimalPlace--;
				int digit = ( (int) (val0 * Math.pow(10, decimalPlace)) ) % 10;
				val0 -= digit * Math.pow(0.1, decimalPlace);
			} else {
				decimal = false;
				val0 = (double) ( (int) (val0/10) );
			}
			display(val0);
		} else if (edit1) {
			if (decimal && decimalPlace!=1) {
				decimalPlace--;
				int digit = ( (int) (val1 * Math.pow(10, decimalPlace)) ) % 10;
				val1 -= digit * Math.pow(0.1, decimalPlace);
			} else {
				decimal = false;
				val1 = (double) ( (int) (val1/10) );
			}
			display(val1);
		}
    }
   
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
