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
import android.widget.CheckBox;
import com.organiser.R;
import com.organiser.dialogs.dialogsCallbacks.SharedTableDeleteDialogCallback;


public class SharedTableDeleteDialog extends DialogFragment {

    private boolean ifFirstOwner,ifDropWholeTable;
    private String hiddenName;
    private SharedTableDeleteDialogCallback listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SharedTableDeleteDialogCallback)context;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("listener doesn't implemented");
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.sharedtable_delete_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);

        CheckBox checkBox = view.findViewById(R.id.if_delete);

        if(!ifFirstOwner){
            checkBox.setVisibility(View.INVISIBLE);
            checkBox.setClickable(false);
        }else
            checkBox.setOnClickListener((View v)-> ifDropWholeTable = checkBox.isChecked());

        AppCompatButton buttonCancel = view.findViewById(R.id.cancel_action);
        buttonCancel.setOnClickListener((View v)-> getDialog().cancel());

        AppCompatButton deleteButton = view.findViewById(R.id.confirm_action);
        deleteButton.setOnClickListener((View v)->{
            listener.deleteTable(hiddenName,ifDropWholeTable);
            getDialog().dismiss();
        });

        builder.setView(view);
        return builder.create();
    }

    public void setIfFirstOwner(boolean ifFirstOwner) {
        this.ifFirstOwner = ifFirstOwner;
    }

    public void setHiddenName(String hiddenName) {
        this.hiddenName = hiddenName;
    }
}
