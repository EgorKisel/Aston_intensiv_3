package com.example.aston_intensiv_3.presentation.contacts_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aston_intensiv_3.R
import com.example.aston_intensiv_3.data.Contact
import com.example.aston_intensiv_3.databinding.StartFragmentBinding
import com.example.aston_intensiv_3.presentation.MainActivity
import com.example.aston_intensiv_3.presentation.adapter.ContactsAdapter
import com.example.aston_intensiv_3.presentation.new_contact.NewContactFragment

class StartFragment : Fragment() {

    private var _binding: StartFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: ContactsViewModel
    private lateinit var contactsAdapter: ContactsAdapter

    private var contac: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = StartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.inflateMenu(R.menu.menu_main)

        val activity = requireActivity() as MainActivity
        sharedViewModel = activity.getSharedViewModel()

        arguments?.let {
            contac = it.getParcelable("key")

        }

        activity.setSupportActionBar(binding.toolbar)

        contactsAdapter = ContactsAdapter() {
            parentFragmentManager.beginTransaction().replace(
                R.id.container,
                NewContactFragment.newInstance(
                    it.firstName.toString(),
                    it.lastName.toString(),
                    it.phoneNumber.toString()
                )
            ).addToBackStack("").commit()
        }
        binding.recyclerContacts.adapter = contactsAdapter

        sharedViewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            contactsAdapter.submitList(contacts)
        }

        activity.supportActionBar?.title = getString(R.string.contacts)

        binding.fab.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, NewContactFragment(), null).addToBackStack("").commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear -> {
                sharedViewModel.deleteAll()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(contact: Contact) =
            StartFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("key", contact)
                }
            }

    }
}