package faithapp.com.faithapp.activities;

import android.app.Activity;
import android.os.Bundle;


import com.google.android.gms.plus.PlusOneButton;

import faithapp.com.faithapp.R;

/**
 * Created by rimon on 10/6/16.
 */
public class User_Login extends Activity {

    PlusOneButton mPlusOneButton;
    private static final int PLUS_ONE_REQUEST_CODE = 0;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.faith_user_login);
        mPlusOneButton = (PlusOneButton) findViewById(R.id.plus_one_button);

    }

    protected void onResume() {
        super.onResume();
        // Refresh the state of the +1 button each time the activity receives focus.
        mPlusOneButton.initialize("https://plus.google.com/", PLUS_ONE_REQUEST_CODE);
    }

}
