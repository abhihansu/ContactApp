package com.example.android.contactapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.android.contactapp.models.Contacts;
import com.example.android.contactapp.utils.UniversalImageLoaer;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditContactFragment extends Fragment {

    private EditText contactName,contactPhone, contactEmail;
    private CircleImageView contactImage;
    private Spinner selectdevice;

    private Contacts mContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_contact, container, false);
        initializeField(view);
        mContact = getContact();
        setField();

        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow_icon);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    /**
     * initializing the widgets
     * @param view
     */
    private void initializeField(View view){
        contactName = (EditText) view.findViewById(R.id.et_contact_name);
        contactImage = (CircleImageView) view.findViewById(R.id.contact_image);
        contactEmail = (EditText) view.findViewById(R.id.et_email_id);
        contactPhone = (EditText) view.findViewById(R.id.et_phone_no);
        selectdevice = (Spinner) view.findViewById(R.id.spinner_select_device);
    }

    /**
     * setting up the widget
     */
    private void setField(){
        contactName.setText(mContact.getName());
        contactEmail.setText(mContact.getEmail());
        contactPhone.setText(mContact.getPhoneNo());
        UniversalImageLoaer.setImage(mContact.getProfileImage(),contactImage,null,"https://");
    }

    private Contacts getContact(){
        Bundle args = getArguments();
        if(args != null) {
            return args.getParcelable(getString(R.string.contact));
        }else{
            return null;
        }
    }
}
