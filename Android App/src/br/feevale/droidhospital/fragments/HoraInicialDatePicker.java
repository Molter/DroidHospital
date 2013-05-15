package br.feevale.droidhospital.fragments;
import java.util.Calendar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.TimePicker;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.interfaces.OnDialogFinished;


public class HoraInicialDatePicker extends DialogFragment implements OnTimeSetListener{
	
	TimePickerDialog dialog;
	OnDialogFinished callback;
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        dialog = new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);
        return dialog;
    }

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			TextView myTextView = (TextView) getActivity().findViewById(R.id.hora_inicial_edit_text);
			myTextView.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
			
			if(callback != null) {
				callback.onDialogFinished(hourOfDay, minute);
			}
	}
	
	public void setCallback(OnDialogFinished callback) {
		this.callback = callback;
	}

}