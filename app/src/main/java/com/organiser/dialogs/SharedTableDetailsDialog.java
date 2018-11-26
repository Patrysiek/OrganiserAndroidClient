package com.organiser.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.organiser.R;
import com.organiser.dialogs.dialogsCallbacks.SharedTableDetailsDialogCallback;
import com.organiser.sharedTable.SharedTable;


public class SharedTableDetailsDialog extends DialogFragment {

    private SharedTable table;
    private SharedTableDetailsDialogCallback listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SharedTableDetailsDialogCallback) context;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("listener doesn't implemented");
        }
    }

    public void setTable(SharedTable table){
        this.table = table;
    }
    @Override
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.sharedtable_details_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);

        TextView details = view.findViewById(R.id.details);

        AppCompatButton buttonOk = view.findViewById(R.id.button);
        buttonOk.setOnClickListener((View v)-> getDialog().cancel());

        AppCompatButton DeleteButton = view.findViewById(R.id.delete_button);
        DeleteButton.setOnClickListener((View v)-> {
            getDialog().cancel();
            listener.onDetailsDeleteClick(table.getHiddenName(),table.getFirstOwner());
        });

        details.setText(table.getDescription());
        builder.setView(view);

        return builder.create();
    }

}
