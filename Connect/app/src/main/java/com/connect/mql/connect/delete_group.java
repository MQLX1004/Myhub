package com.connect.mql.connect;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class delete_group extends DialogFragment {
    private Del_group mDel_group;
    private TextView delete;

    public delete_group(Del_group del_group) {
        mDel_group = del_group;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_delete_group,null);
        delete=(TextView) v.findViewById(R.id.delete_dia);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDel_group.delgroup();
                dismiss();
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }
}
