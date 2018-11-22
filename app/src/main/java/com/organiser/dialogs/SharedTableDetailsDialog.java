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

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.organiser.R;
import com.organiser.dialogs.dialogsCallbacks.AddTaskDialogCallback;


public class AddTaskDialog extends DialogFragment {
    private String[] items = {"ToDo","inProgress","done" };
    private String choose = items[0];
    private AddTaskDialogCallback mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddTaskDialogCallback) context;
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

        final View view = inflater.inflate(R.layout.add_task_dialog, null);
        final EditText description = view.findViewById(R.id.description);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        TextView tv = initTitleText();
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<>(view.getContext(),R.layout.add_task_adapter_layout,R.id.add_status_item,items);

        builder.setView(view)
                .setCustomTitle(tv)
                .setPositiveButton(R.string.add_task_button,(DialogInterface dialog, int id) ->
                        mListener.onAddTaskDialogPositiveClick(description.getText().toString(),choose)
                )
                .setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) ->
                        AddTaskDialog.this.getDialog().cancel()
                ).setSingleChoiceItems(itemAdapter, 0, (DialogInterface dialog, int which) ->
            setChoose(which));

        return builder.create();
    }

    @SuppressLint("ResourceAsColor")
    private TextView initTitleText() {
        TextView tv =  new TextView(getContext());
        tv.setGravity(Gravity.CENTER);
        tv.setText(R.string.add_task_dialog_title);
        tv.setTextSize(25);
        tv.setTextColor(Color.parseColor("#CCCCCC"));
        return tv;
    }

    private void setChoose(int which) {
        this.choose = items[which];
    }



}
