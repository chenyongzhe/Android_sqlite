package com.example.a12763.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by 12763 on 2017/12/5.
 */

  @SuppressLint("ValidFragment") public class MyDialogFragment extends DialogFragment {
    private   AlertDialog.Builder  builder;
    private  String  in;
   public   MyDialogFragment(String information){
             super();
        this.in=information;

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("警告");
       builder.setMessage(in);
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
