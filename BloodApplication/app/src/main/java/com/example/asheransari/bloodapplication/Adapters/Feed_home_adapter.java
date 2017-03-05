package com.example.asheransari.bloodapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asheransari.bloodapplication.R;
import com.example.asheransari.bloodapplication.variables_classes.Required_variable_blood;

import java.util.List;

/**
 * Created by asher.ansari on 3/4/2017.
 */

public class Feed_home_adapter extends RecyclerView.Adapter<Feed_home_adapter.MyViewHolder> {

    private Context context;
    private List<Required_variable_blood> variable_bloodList;
    public Feed_home_adapter(Context mContext,List<Required_variable_blood> variableBloods){
        this.context = mContext;
        this.variable_bloodList = variableBloods;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post_detail_blood,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Required_variable_blood variableBlood = variable_bloodList.get(position);
        holder.name.setText(variableBlood.getName());
//        3 units of A Positive Blood Required
        holder.unit_blood.setText(variableBlood.getUnit()+" units of "+variableBlood.getBlood()+" Blood Required");
        holder.additional.setText(variableBlood.getAdditionalInfo());
        holder.hospital_relation.setText("At "+variableBlood.getHospital()+" to my "+variableBlood.getRelationShip());
        holder.urgency.setText(variableBlood.getUrgent());
        holder.contact.setText(""+variableBlood.getContact());

//        At Indus Hospital to my Friend
//        holder.volunteer.setText(variableBlood.);
    }

    @Override
    public int getItemCount() {
        return variable_bloodList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name,unit_blood,hospital_relation, urgency,contact,additional,volunteer;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.list_post_name);
            unit_blood =(TextView)itemView.findViewById(R.id.list_post_unit);
            hospital_relation =(TextView)itemView.findViewById(R.id.list_post_hospital);
            urgency = (TextView)itemView.findViewById(R.id.list_post_urgency);
            contact = (TextView)itemView.findViewById(R.id.list_post_number);
            additional = (TextView)itemView.findViewById(R.id.list_post_additional);
            volunteer = (TextView)itemView.findViewById(R.id.list_post_volunteer);
        }
    }
}
