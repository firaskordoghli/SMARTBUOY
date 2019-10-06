package com.example.smartbuoy.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.smartbuoy.R;

public class AddPlanDialog extends AppCompatDialogFragment {

    private TextView addPlanPlageTv ;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builer = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_add_plan, null);

        addPlanPlageTv = view.findViewById(R.id.addPlanPlageTv);
        builer.setView(view);
        return builer.create();
    }
}
