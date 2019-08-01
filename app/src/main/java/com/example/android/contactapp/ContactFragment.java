package com.example.android.contactapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.contactapp.adapter.ContactInfoListAdapter;
import com.example.android.contactapp.models.Contacts;
import com.example.android.contactapp.utils.UniversalImageLoaer;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ContactFragment extends Fragment {

    private Toolbar toolBar;
    private Contacts mContact;
    private TextView contactName;
    private CircleImageView contactImage;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        toolBar = (Toolbar) view.findViewById(R.id.contact_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolBar);
        setHasOptionsMenu(true);

        contactName = (TextView) view.findViewById(R.id.tv_contact_name);
        contactImage = (CircleImageView) view.findViewById(R.id.contact_image);
        listView = (ListView) view.findViewById(R.id.contact_information_list);

        // getting the selected contact
        mContact = getContactFromBundle();

        init();

        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow_icon);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // navigate to EditContactFragment to edit the contact
        ImageView editContact = (ImageView) view.findViewById(R.id.edit_icon);
        editContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditContactFragment fragment = new EditContactFragment();
                Bundle args = new Bundle();
                args.putParcelable(getString(R.string.contact), mContact);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment);
                fragmentTransaction.addToBackStack(getString(R.string.edit_contact_fragment));
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete :

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init(){
        contactName.setText(mContact.getName());
        UniversalImageLoaer.setImage(mContact.getProfileImage(),contactImage,null,"http://");

        ArrayList<String> properties = new ArrayList<>();
        properties.add(mContact.getEmail());
        properties.add(mContact.getPhoneNo());

        ContactInfoListAdapter adapter = new ContactInfoListAdapter(getActivity(),R.layout.layout_cardview,properties);
        listView.setAdapter(adapter);
        listView.setDivider(null);
    }

    private Contacts getContactFromBundle(){
       Bundle bundle = this.getArguments();
       if(bundle != null){
           return bundle.getParcelable(getString(R.string.contact));
        }else{
           return null;
       }
    }
}
