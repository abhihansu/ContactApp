package com.example.android.contactapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.contactapp.adapter.ContactListAdapter;
import com.example.android.contactapp.models.Contacts;

import java.util.ArrayList;

public class ViewContactFragment extends Fragment {

    private static final String TAG = "ViewContactFragment";
    private String testImageURL = "mymodernmet.com/wp/wp-content/uploads/2019/07/russian-blue-cats-17.jpg";

    public interface OnContactSelectedListener{
        public void onContactSelected(Contacts contact);
    }
    private OnContactSelectedListener mContactListener;

    private FloatingActionButton addContactsButton;
    private ImageView searchIcon, backArrow;
    private AppBarLayout viewContactsBar, searchBar;
    private ListView contactListView;
    private ContactListAdapter mAdapter;

    public static final int STANDARD_TOOLBAR = 0;
    public static final int SEARCH_APPBAR = 1;
    private int mAppBarState;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_contact, container, false);
        // Inflate the layout for this fragment

        viewContactsBar = (AppBarLayout) view.findViewById(R.id.view_contacts_toolbar);
        searchBar = (AppBarLayout) view.findViewById(R.id.search_toolbar);
        contactListView = (ListView) view.findViewById(R.id.contacts_list);

        addContactsButton = view.findViewById(R.id.fab_add_contacts);
        searchIcon = view.findViewById(R.id.search_icon);
        backArrow = view.findViewById(R.id.back_arrow_icon);

        setAppBarState(STANDARD_TOOLBAR);
        setUpContactList();

        addContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleToolBarState();
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleToolBarState();
            }
        });

        return view;
    }

    //https://
    private void setUpContactList(){
        final ArrayList<Contacts> contacts = new ArrayList<>();
        contacts.add(new Contacts("Abhinav Kumar", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Bhupendra Singh", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Rohit Kumar", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Shubham Kumar", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Ashwini Kumar", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Mayank Rajpoot", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Kiara Adwani", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));
        contacts.add(new Contacts("Abhinav", "9862741442","Mobile","roy.abhinav@gmail.com",testImageURL));

        mAdapter = new ContactListAdapter(getActivity(),R.layout.layout_contact_list_item,contacts,"https://");
        contactListView.setAdapter(mAdapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // pass the contact to interface and send it to MainActivity
                mContactListener.onContactSelected(contacts.get(position));
            }
        });
    }

    /**
     * toggle the app bar if we click on search or back button respectively
     */
    private void toggleToolBarState() {
        if(mAppBarState == STANDARD_TOOLBAR){
            setAppBarState(SEARCH_APPBAR);
        }else{
            setAppBarState(STANDARD_TOOLBAR);
        }
    }

    /**
     * set the AppBar tp either searchBar or ViewContactBar
     * @param state current AppBar state
     */
    private void setAppBarState(int state) {
        mAppBarState = state;
        if(mAppBarState == STANDARD_TOOLBAR){
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);

            // Hide the keyboard
            View view = getView();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try{
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }catch (NullPointerException e){
                Log.d(TAG,"setAppBar: NullPointerException" + e);
            }
        }else{
            searchBar.setVisibility(View.VISIBLE);
            viewContactsBar.setVisibility(View.GONE);

            // open the keyboard
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_TOOLBAR);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mContactListener = (OnContactSelectedListener)getActivity();
        }catch (ClassCastException e){
            Log.d(TAG,"onAttach :ClassCastException", e);
        }
    }
}
