package app.sar.ro.sar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class MyInfoFragment extends Fragment {
    EditText edtMetImp, edtWeight, edtPhoneModel, edtPhoneSAR, edtCountry, edtExposure, edtSarSec;
    public static ArrayList<Integer> dbmValues = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);

        final Context context = container.getContext();
        edtPhoneModel = (EditText) view.findViewById(R.id.edt_phone_model);
        edtCountry = (EditText) view.findViewById(R.id.edt_country_sar_value);
        edtExposure = (EditText) view.findViewById(R.id.edt_exposure);
        edtMetImp = (EditText) view.findViewById(R.id.edt_kg_lbs);
        edtPhoneSAR = (EditText) view.findViewById(R.id.edt_sar_value);
        edtWeight = (EditText) view.findViewById(R.id.edt_weight);
        edtSarSec = (EditText) view.findViewById(R.id.edt_sar_sec);

        String device = Build.MANUFACTURER;
        // de sters
        //= SAR Limit / Kg / 360 * CELL SAR
        edtPhoneModel.setText(device.toUpperCase());
        edtWeight.setText("71");
        edtCountry.setText("2");
        edtExposure.setText("YES");
        edtPhoneSAR.setText("0.4");
        double sarpersec = 2.0/71.0/(360.0 * 0.4);
        double roundedsarpersec = (double) Math.round(sarpersec * 1000000000.0) / 1000000000.0;
        String str = "";
        for (Integer dbm : dbmValues)
            str+=dbm.intValue() + ", ";
            edtSarSec.setText(str+"");
        return view;

    }



}
