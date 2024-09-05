package com.rahul.mvvmexample;

import android.app.AlertDialog;
import android.app.Dialog;import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ServerErrorDialogFragment extends DialogFragment {


    public static ServerErrorDialogFragment newInstance() {
        return new ServerErrorDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Server Error");
        builder.setMessage("Something went wrong. Pleasetry again");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        return builder.create(); // Return the created AlertDialog
    }
}