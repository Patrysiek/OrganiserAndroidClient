package com.organiser.dialogs;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.organiser.R;
import com.organiser.dialogs.dialogsCallbacks.CreateSharedTableDialogCallback;

import java.util.regex.Pattern;

public class CreateSharedTableDialog extends DialogFragment {
    private CreateSharedTableDialogCallback mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (CreateSharedTableDialogCallback) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.add_shared_table_dialog, null);
        final EditText tableName = view.findViewById(R.id.hidden_name);
        tableName.setHint(R.string.name);
        final EditText password = view.findViewById(R.id.password);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        TextView tv = initTitleText();
        builder.setView(view)
                .setCustomTitle(tv)
                .setPositiveButton(R.string.create_table,(DialogInterface dialog, int id) -> {

                    String name = tableName.getText().toString();

                    if(checkedIfCorrect(name)) mListener.createSharedTableDialogClick(name, password.getText().toString());
                    else Toast.makeText(view.getContext(),"Table name has to contain letter only !",Toast.LENGTH_LONG).show();
                        }
                )
                .setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) ->
                        getDialog().cancel()
                );

        return builder.create();
    }

    private boolean checkedIfCorrect(String tableName) {
        return Pattern.matches("[a-zA-z]+",tableName);
    }

    @SuppressLint("ResourceAsColor")
    private TextView initTitleText() {
        TextView tv =  new TextView(getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setText(R.string.create_table);
        tv.setTextSize(25);
        tv.setTextColor(Color.parseColor("#CCCCCC"));
        return tv;
    }

}