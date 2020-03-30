package de.foodbro.app.ui.edit.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerDialogFragment
import de.foodbro.app.ui.edit.RecipeEditViewModel
import java.util.*
import javax.inject.Inject

class DurationPickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    companion object {
        private const val DEFAULT_HOURS = 0
        private const val DEFAULT_MINUTES = 30

        fun newInstance(vm: RecipeEditViewModel) = DurationPickerDialogFragment().apply {
            viewModel = vm
        }
    }

    private lateinit var viewModel: RecipeEditViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(activity, this, DEFAULT_HOURS, DEFAULT_MINUTES,
            DateFormat.is24HourFormat(activity))
    }


    override fun onTimeSet(view: TimePicker?, hours: Int, minutes: Int) {
        viewModel.updatePreparationTime(hours, minutes)
    }
}