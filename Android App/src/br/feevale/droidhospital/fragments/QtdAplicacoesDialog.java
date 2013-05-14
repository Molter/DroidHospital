package br.feevale.droidhospital.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.NumberPicker;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.interfaces.OnDialogFinished;

public class QtdAplicacoesDialog extends DialogFragment {
	OnDialogFinished callback;
	NumberPicker mNumberPicker;
	
	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
		 
		 mNumberPicker = new NumberPicker(getActivity());
		 mNumberPicker.setMinValue(1);
		 mNumberPicker.setMaxValue(99);
		 
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setTitle(R.string.qtd_aplicacoes)
	        
	               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   TextView number = (TextView) getActivity().findViewById(R.id.qtd_aplicacoes_picker);
	                	   number.setText(String.valueOf(mNumberPicker.getValue()));
	                	   
	                	   
	                	   if(callback != null) {
	           					callback.onDialogFinished(mNumberPicker.getValue(), 0);
	           				}
	                   }
	               })
	               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       
	                   }
	               });
	        
	        builder.setView(mNumberPicker);
	        
	        return builder.create();
	    }

	public void setCallback(OnDialogFinished callback) {
		this.callback = callback;
	}
	

}
