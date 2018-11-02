package com.organiser.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


import com.organiser.R;

public class AddTaskDialog extends DialogFragment {

    public interface TaskDialogListener {
        void onDialogPositiveClick(String dialog);
    }


    TaskDialogListener mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (TaskDialogListener) context;

        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.add_task_dialog, null);
        final EditText description = view.findViewById(R.id.description);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setView(view)
                .setPositiveButton(R.string.add_task_button,(DialogInterface dialog, int id) ->
                    mListener.onDialogPositiveClick(description.getText().toString())
                )
                .setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) ->
                        AddTaskDialog.this.getDialog().cancel()
                );

        return builder.create();
    }

}
