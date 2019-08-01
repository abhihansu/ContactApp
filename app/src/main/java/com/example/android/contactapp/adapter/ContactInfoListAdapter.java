package com.example.android.contactapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.contactapp.MainActivity;
import com.example.android.contactapp.R;
import com.example.android.contactapp.utils.Init;

import java.util.List;

public class ContactInfoListAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater;
    private List<String> properties;
    private int layoutResource;
    private Context mContext;

    public ContactInfoListAdapter(@NonNull Context context, int resource, @NonNull List<String> properties) {
        super(context, resource, properties);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        this.mContext = context;
        this.properties = properties;
//        arrayList = new ArrayList<>();
//        this.arrayList.addAll(contacts);
    }

    private static class ViewHolder{
        TextView property;
        ImageView rightIcon, leftIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(layoutResource, parent,false);
            holder = new ViewHolder();
            holder.property = (TextView) convertView.findViewById(R.id.tv_cardview_text);
            holder.leftIcon = (ImageView) convertView.findViewById(R.id.icon_left_cardview);
            holder.rightIcon = (ImageView) convertView.findViewById(R.id.icon_right_cardview);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        final String propertyText = getItem(position);
        holder.property.setText(propertyText);

        // check if the property is email or phone no
        if(propertyText.contains("@")){
            holder.leftIcon.setImageResource(R.drawable.ic_email_black);
            // set up the left icon for sending email
            holder.leftIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mailIntent = new Intent(Intent.ACTION_SEND);
                    mailIntent.setType("plain/text");
                    mailIntent.putExtra(Intent.EXTRA_EMAIL, propertyText);
                    mContext.startActivity(mailIntent);
                }
            });
        }else{
            holder.leftIcon.setImageResource(R.drawable.ic_call_black);
            // set up the left icon for make a call
            holder.leftIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // checking for the call permission
                    if(((MainActivity)mContext).checkPermission(Init.PHONE_PERMISSION)){
                        Toast.makeText(mContext, "Making phone Call", Toast.LENGTH_SHORT).show();
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel",propertyText,null));
                        mContext.startActivity(callIntent);
                    }else{
                        ((MainActivity)mContext).verifyPermission(Init.PHONE_PERMISSION);
                    }
                }
            });
            holder.rightIcon.setImageResource(R.drawable.ic_message_black);
            // set up the right icon for sending message
            holder.rightIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent messageIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",propertyText,null));
                    mContext.startActivity(messageIntent);
                }
            });
        }

        return convertView;
    }
}
