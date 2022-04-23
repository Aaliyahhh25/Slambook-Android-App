package com.example.loginscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    int layout;
    ArrayList<EntryClass> entryClasses;
    OnConvertViewLongClickListener listener; OnClickListener onClickListener; // first step pag gagawa ng sariling listener sa custom adapter
    OnClickListener delete;
    public CustomAdapter(Context context, int layout, ArrayList<EntryClass> entryClasses) {
        this.context = context;
        this.layout = layout;
        this.entryClasses = entryClasses;
    }

    // eto ung second step pag gagawa ng custom listener sa adapter
    public interface OnConvertViewLongClickListener{
        void convertViewLongClickListener(int position);
    }
    public void setOnConvertViewLongClickListener(OnConvertViewLongClickListener listenerNaGalingSaActivity) {
        listener = listenerNaGalingSaActivity;
    }
    public interface OnClickListener { //gumawa ako ng sariling click listener dito
        void clickListener(int position,int palatandaan);
    }//int palatandaan// 0 for whole entry // 1 editbtn entry//2 deletebtn entry
    public void setOnClickListener(OnClickListener clickFromActivity){
        onClickListener =  clickFromActivity;
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(convertView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int position) {
        EntryClass eachEntryClass = entryClasses.get(position);
        if(eachEntryClass.getImage()!=0){vh.entryImage.setImageResource(eachEntryClass.getImage());}
        else{vh.entryImage.setImageResource(R.drawable.ic_baseline_account_circle_24);}//handy pag walang picture sa database na naretireve
        vh.entryName.setText(eachEntryClass.getName());
        vh.entryRemark.setText(eachEntryClass.getRemark());
        vh.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//custom made na listener para sa button inside each entry
                if(onClickListener!=null){
                    onClickListener.clickListener(position,1);
                }
            }
        });
        vh.btndelete.setOnClickListener(new View.OnClickListener() {//custom made na listener para sa button inside each entry
            @Override
            public void onClick(View v) {
                if(onClickListener!=null){
                    onClickListener.clickListener(position,2);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return entryClasses.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView entryImage;
        TextView entryName;
        TextView entryRemark;
        ImageButton btnedit;
        ImageButton btndelete;
        public ViewHolder(View convertView) {
            super(convertView);
            this.entryImage = convertView.findViewById(R.id.entryPic);
            this.entryName = convertView.findViewById(R.id.entryName);
            this.entryRemark = convertView.findViewById(R.id.entryRemarks);
            this.btnedit = convertView.findViewById(R.id.editEntrybtn);
            this.btndelete = convertView.findViewById(R.id.deleteEntrybtn);

            //3rd step when creating custom listener for custom adapter ito ung ibabalik sa main activity
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.convertViewLongClickListener(position);
                        }
                    }

                    return true;
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onClickListener.clickListener(position,0);

                        }
                    }

                    /*int position = getAdapterPosition();
                    Toast.makeText(context, "You clicked "+ position, Toast.LENGTH_SHORT).show();
                    Intent fromEntryListToIndiv = new Intent(context,IndividialEntryActivity.class);
                    fromEntryListToIndiv.putExtra("Entry", (Parcelable)entryClasses.get(position));*/

                }
            });

        }
    }
}

