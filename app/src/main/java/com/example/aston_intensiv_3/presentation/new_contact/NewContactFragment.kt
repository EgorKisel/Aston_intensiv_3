package com.example.aston_intensiv_3.presentation.new_contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_3.data.Contact
import com.example.aston_intensiv_3.databinding.NewContactFragmentBinding
import com.example.aston_intensiv_3.presentation.MainActivity
import com.example.aston_intensiv_3.presentation.contacts_fragment.ContactsViewModel

private const val ARG_PARAM_CONTACT_FIRSTNAME = "firstname"
private const val ARG_PARAM_CONTACT_LASTNAME = "lastname"
private const val ARG_PARAM_CONTACT_PHONE_NUMBER = "phone_number"

class NewContactFragment : Fragment() {

    private var _binding: NewContactFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: ContactsViewModel

    private var firstName: String? = null
    private var lastName: String? = null
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstName = it.getString(ARG_PARAM_CONTACT_FIRSTNAME)
            lastName = it.getString(ARG_PARAM_CONTACT_LASTNAME)
            phoneNumber = it.getString(ARG_PARAM_CONTACT_PHONE_NUMBER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = NewContactFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mContact: Contact? = arguments?.getParcelable("key")

        val activity = requireActivity() as MainActivity

        val firstNameChanged = binding.firstName.setText(firstName)
        val lastNameChanged = binding.lastName.setText(lastName)
        val phoneNumberChanged = binding.phoneNumber.setText(phoneNumber)

        val contacts = mutableListOf<Contact>()

        binding.buttonSave.setOnClickListener {
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            val contact = Contact(
                id = id,
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber
            )
            sharedViewModel = activity.getSharedViewModel()
            sharedViewModel.addContact(contact)
            sharedViewModel.setContacts(contacts)
            parentFragmentManager.popBackStack()
        }

        binding.buttonCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(firstName: String, lastName: String, phoneNumber: String) =
            NewContactFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_CONTACT_FIRSTNAME, firstName)
                    putString(ARG_PARAM_CONTACT_LASTNAME, lastName)
                    putString(ARG_PARAM_CONTACT_PHONE_NUMBER, phoneNumber)
                }
            }

    }
}