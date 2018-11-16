package com.organiser.dialogs;

import android.annotation.SuppressLint;
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

import com.organiser.R;

@SuppressLint("ValidFragment")
public class TaskStatusDialog extends DialogFragment {
        private final String[] items = {"ToDo","inProgress","done" };
        private String choose = items[0];
        private TaskStatusDialogCallback mListener;
        private int ID;
        public TaskStatusDialog(int ID){
            this.ID = ID;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            // Verify that the host activity implements the callback interface
            try {
                // Instantiate the NoticeDialogListener so we can send events to the host
                mListener = (TaskStatusDialogCallback) context;
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
            final View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);

            builder.setView(view)
                    .setSingleChoiceItems(items, 0, (DialogInterface dialog, int which) ->{
                        setChoose(which);
                        getDialog().dismiss();
                            });
            return builder.create();
        }
        private void setChoose(int which) {
            this.choose = items[which];
            mListener.statusTaskDialogClick(choose,ID);
        }
    }
