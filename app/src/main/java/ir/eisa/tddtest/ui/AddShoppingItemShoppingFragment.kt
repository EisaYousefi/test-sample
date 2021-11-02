package ir.eisa.tddtest.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ir.eisa.tddtest.R
import ir.eisa.tddtest.ui.viewModel.ShoppingViewModel
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*

class AddShoppingItemShoppingFragment : Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel : ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)

        ivShoppingImage.setOnClickListener{
            findNavController().navigate(
                AddShoppingItemShoppingFragmentDirections.actionAddShoppingItemShoppingFragmentToImagePickFragment()
            )
        }

        val callBack = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}