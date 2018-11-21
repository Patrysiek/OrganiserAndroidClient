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

import com.organiser.R;
import com.organiser.dialogs.dialogsCallbacks.AddSharedTableDialogCallback;


public class AddSharedTableDialog extends DialogFragment {
    private AddSharedTableDialogCallback mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddSharedTableDialogCallback) context;
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
        final EditText hiddenName = view.findViewById(R.id.hidden_name);
        hiddenName.setHint(R.string.add_table_title);
        final EditText password = view.findViewById(R.id.password);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        TextView tv = initTitleText();
        builder.setView(view)
                .setCustomTitle(tv)
                .setPositiveButton(R.string.add_table_title,(DialogInterface dialog, int id) ->
                        mListener.addSharedTableDialogClick(hiddenName.getText().toString(),password.getText().toString())
                )
                .setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) ->
                        getDialog().cancel()
                );


        return builder.create();
    }

    @SuppressLint("ResourceAsColor")
    private TextView initTitleText() {
        TextView tv =  new TextView(getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setText(R.string.add_table_title);
        tv.setTextSize(25);
        tv.setTextColor(Color.parseColor("#CCCCCC"));
        return tv;
    }

}
