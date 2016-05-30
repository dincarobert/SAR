package app.sar.ro.sar;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adm_broker on 17/01/15.
 */



public class GetGsmSignalStrengthFragment extends Fragment {
    EditText textSignal, textASUValue, textDBMValue, textCallDuration;
    /* This variables need to be global, so we can used them onResume and onPause method to
       stop the listener */
    TelephonyManager Tel;
    MyPhoneStateListener MyListener;
    public static double callDuration = 0.0;
    public static ArrayList<Integer> dbmValues = new ArrayList<>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_signal_strength, container, false);
        super.onCreate(savedInstanceState);
        textSignal = (EditText) view.findViewById(R.id.text_signal);
        textASUValue = (EditText) view.findViewById(R.id.text_signal_asu);
        textDBMValue = (EditText) view.findViewById(R.id.text_signal_dbm);
        textCallDuration = (EditText) view.findViewById(R.id.text_call_duration);


        /* Update the listener, and start it */
        MyListener = new MyPhoneStateListener();
        Tel = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
            }
        };

        // schedule the task to run starting now and then every hour...
        timer.schedule(hourlyTask, 0l, 1000);

        return view;
    }

    /* Called when the application is minimized */
    @Override
    public void onPause() {
        super.onPause();
        Tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);
    }

    /* Called when the application resumes */
    @Override
    public void onResume() {
        super.onResume();
        Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }


    /* —————————– */
    /* Start the PhoneState listener */
   /* —————————– */
    private class MyPhoneStateListener extends PhoneStateListener {
        /* Get the Signal strength from the provider, each tiome there is an update */
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            textSignal.setText("GSM Cinr = "
                    + String.valueOf(signalStrength.getGsmSignalStrength()) + " %");
            int SignalStrength_ASU = signalStrength.getGsmSignalStrength();
            int SignalStrength_dBm = (2 * SignalStrength_ASU) - 113; // -> dBm
            textASUValue.setText("ASU VALUE : " + SignalStrength_ASU);
            textDBMValue.setText("DBM VALUE : " + SignalStrength_dBm);
            textCallDuration.setText("CALL DURATION : " + callDuration);

        }

    }

    ;/* End of private Class */

}/* GetGsmSignalStrength */

