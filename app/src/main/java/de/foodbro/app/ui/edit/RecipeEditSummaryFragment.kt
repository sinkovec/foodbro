package de.foodbro.app.ui.edit

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment

import de.foodbro.app.databinding.FragmentRecipeEditSummaryBinding
import de.foodbro.app.ui.EventObserver
import de.foodbro.app.ui.edit.dialogs.DurationPickerDialogFragment
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

class RecipeEditSummaryFragment : DaggerFragment() {

    companion object {
        fun newInstance() = RecipeEditSummaryFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RecipeEditViewModel by lazy {
        ViewModelProvider(requireParentFragment(), viewModelFactory)[RecipeEditViewModel::class.java]
    }

    private lateinit var viewDataBinding: FragmentRecipeEditSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            FragmentRecipeEditSummaryBinding.inflate(inflater, container, false).apply {
                viewModel = this@RecipeEditSummaryFragment.viewModel
                lifecycleOwner = this@RecipeEditSummaryFragment.viewLifecycleOwner
            }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openImageEvent.observe(viewLifecycleOwner, EventObserver {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                    val imageFile: File? = try {
                        createImageFile()
                    } catch (e: IOException) {
                        Log.e("=", "Error creating the image file", e)
                        null
                    }
                    // Continue only if the File was successfully created
                    imageFile?.also {
                        val imageUri: Uri = FileProvider.getUriForFile(
                            requireContext(),
                            "de.foodbro.app.fileprovider",
                            it
                        )
                        viewModel.updateImageUri(imageUri)
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                        startActivity(takePictureIntent)
                    }
                }
            }
        })

        viewModel.openTimePickerDialogEvent.observe(viewLifecycleOwner, EventObserver {
            DurationPickerDialogFragment.newInstance(viewModel).show(parentFragmentManager, "dialog")
        })
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

}
