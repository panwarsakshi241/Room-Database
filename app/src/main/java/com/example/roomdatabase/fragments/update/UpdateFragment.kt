package com.example.roomdatabase.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.Model.User
import com.example.roomdatabase.R
import com.example.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update,container,false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updatefirstName.setText(args.currentUser.firstName)
        view.updatSecondName.setText(args.currentUser.lastName)
        view.updateAge.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        //add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstName = updatefirstName.text.toString()
        val lastName = updatSecondName.text.toString()
        val age = Integer.parseInt(updateAge.text.toString())

        if (inputCheck(firstName,lastName,updateAge.text)){
            //create user object
            val updateUser = User(args.currentUser.id,firstName,lastName,age)

            //update current user
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"Updated Successfully!",Toast.LENGTH_LONG).show()

            //navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all the required fields !",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String , lastName: String,age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                    requireContext(),
                    "Successfully Removed: ${args.currentUser.firstName}",
                    Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){_, _ ->

        }

        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}