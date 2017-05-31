package com.connect.mql.connect;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by MQL on 2017/5/8.
 */

public class Dialog_del extends DialogFragment {
    private Button queding;
    private Button quxiao;
    private DialogDelete mDialogDelete;

    public Dialog_del(DialogDelete dialogDelete) {
        mDialogDelete = dialogDelete;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_del_layout,null);
        queding=(Button)v.findViewById(R.id.bt_queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogDelete.delete();
                dismiss();
            }
        });
        quxiao=(Button)v.findViewById(R.id.bt_quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogDelete.quxiaodelete();
                dismiss();
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
