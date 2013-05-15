package br.feevale.droidhospital.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.interfaces.OnDialogFinished;

public class IntervaloDialog extends DialogFragment {
	OnDialogFinished callback;
	
	NumberPicker mHourPicker;
	NumberPicker mMinutePicker;
	
	
	 @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
		 
		 LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
		 View layout = inflater.inflate(R.layout.intervalo_dialog, null);
		 
		 mHourPicker = (NumberPicker) layout.findViewById(R.id.intervalo_dialog_hour_number_picker);
		 mHourPicker.setMaxValue(36);
		 
		 
		 mMinutePicker = (NumberPicker) layout.findViewById(R.id.intervalo_dialog_minute_number_picker);
		 mMinutePicker.setMaxValue(59);
		 mMinutePicker.setMinValue(0);
		 
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        builder.setTitle(R.string.intervalo)
	        
	               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   TextView number = (TextView) getActivity().findViewById(R.id.intervalo_edit_text);
	                	   
	                	   
	                	   //monta String ex. 1 hora e 25 minutos 
	                	   StringBuilder text = new StringBuilder();
	                	   
	                	   if(mHourPicker.getValue() > 1) {
	                		   text.append(String.valueOf(mHourPicker.getValue()));
	                		   text.append(" ") ;
	                		   text.append(getString(R.string.hours_lower));
	                	   }else if(mHourPicker.getValue() == 1) {
	                		   text.append(String.valueOf(mHourPicker.getValue()));
	                		   text.append(" ") ;
	                		   text.append(getString(R.string.hour_lower));
	                	   }
	                	   
	                	   if(mMinutePicker.getValue() > 1) {
	                		   
	                		   if(mHourPicker.getValue() > 1) {
	                			   text.append(" ") ;
	                			   text.append(getString(R.string.and));
	                			   text.append(" ") ;
	                		   }
	                		   
	                		   text.append(String.valueOf(mMinutePicker.getValue()));
	                		   text.append(" " + getString(R.string.minutes_lower));
	                		   
	                	   }else if(mMinutePicker.getValue() == 1) {
	                		   if(mHourPicker.getValue() >= 1) {
	                			   text.append(" ") ;
	                			   text.append(getString(R.string.and));
	                			   text.append(" ") ;
	                		   }
	                		   text.append(String.valueOf(mMinutePicker.getValue()));
	                		   text.append(" " + getString(R.string.minute_lower));
	                	   }
	                	   
	                	   number.setText(text.toString());
	                	   
	                	   if(callback != null) {
	           					callback.onDialogFinished(mHourPicker.getValue(), mMinutePicker.getValue());
	           				}
	                	   
	                   }
	               })
	               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       
	                   }
	               });
	        
	        builder.setView(layout);
	        
	        return builder.create();
	    }
	 
	 public void setCallback(OnDialogFinished callback) {
			this.callback = callback;
		}
	

}
