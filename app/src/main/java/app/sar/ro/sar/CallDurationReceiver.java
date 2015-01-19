package app.sar.ro.sar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adm_broker on 17/01/15.
 */
public class CallDurationReceiver extends BroadcastReceiver {

    static boolean flag = false;
    static long start_time = 0, end_time;
    String startDate, finishDate;
    long timeToSave;
    TelephonyManager Tel;
    Handler h;
    int SignalStrength_dBm;
    boolean inACall;
    MyPhoneStateListener MyListener;
    public static long callDuration = 0;


    @Override
    public void onReceive(Context arg0, Intent intent) {
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            start_time = System.currentTimeMillis();
            timeToSave = start_time;
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
            Date now = new Date();
            startDate = sdfDate.format(now);
            MyListener = new MyPhoneStateListener();
            Tel = (TelephonyManager) arg0.getSystemService(Context.TELEPHONY_SERVICE);
            Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
            MyInfoFragment.dbmValues = new ArrayList<>();
            MyInfoFragment.dbmValues.clear();
            inACall = true;
            Timer timer = new Timer();
            TimerTask hourlyTask = new TimerTask() {
                @Override
                public void run() {
                    Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
                }
            };

            timer.schedule(hourlyTask, 0l, 3000);


        }
        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_IDLE)) {
            inACall = false;
            end_time = System.currentTimeMillis();
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
            Date now = new Date();
            finishDate = sdfDate.format(now);
            //Total time talked =
            long total_time = end_time - start_time;
            if (start_time != 0)
                GetGsmSignalStrengthFragment.callDuration = total_time / 1000.0;
            //Store total_time somewhere or pass it to an Activity using intent
            System.out.print(total_time);
            //          timer.cancel();

        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        /* Get the Signal strength from the provider, each tiome there is an update */
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (inACall) {
                int SignalStrength_ASU = signalStrength.getGsmSignalStrength();
                SignalStrength_dBm = (2 * SignalStrength_ASU) - 113; // -> dBm
                if (System.currentTimeMillis() - start_time > 1000)
                    MyInfoFragment.dbmValues.add(SignalStrength_dBm);
            }

        }
    }

}

