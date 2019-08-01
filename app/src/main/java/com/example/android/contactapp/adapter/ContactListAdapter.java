package com.example.android.contactapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.contactapp.R;
import com.example.android.contactapp.models.Contacts;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactListAdapter extends ArrayAdapter<Contacts> {
    private LayoutInflater mInflater;
    private List<Contacts> contactsList;
    private ArrayList<Contacts> arrayList; // used or search bar
    private int layoutResource;
    private Context mContext;
    private String mAppend;

    public ContactListAdapter(@NonNull Context context, int resource, @NonNull List<Contacts> contacts, String append) {
        super(context, resource, contacts);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        mAppend = append;
        this.mContext = context;
        contactsList = contacts;
        arrayList = new ArrayList<>();
        this.arrayList.addAll(contacts);
    }

    private static class ViewHolder{
        TextView contactName;
        CircleImageView contactImage;
        ProgressBar progressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(layoutResource, parent,false);
            holder = new ViewHolder();
            holder.contactName = (TextView) convertView.findViewById(R.id.tv_contact_name);
            holder.contactImage = (CircleImageView) convertView.findViewById(R.id.civ_contact_image);
            holder.progressBar = (ProgressBar) convertView.findViewById(R.id.pb_contact_loading);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        String name = getItem(position).getName();
        String imagePath = getItem(position).getProfileImage();
        holder.contactName.setText(name);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imagePath, holder.contactImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.progressBar.setVisibility(View.GONE);
            }
        });

        return convertView;
    }
}
