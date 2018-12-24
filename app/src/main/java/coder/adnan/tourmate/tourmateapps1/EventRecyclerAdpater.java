package coder.adnan.tourmate.tourmateapps1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EventRecyclerAdpater extends RecyclerView.Adapter<EventRecyclerAdpater.EventViewholder> {

    private Context context;
    private List<Events> eventsList;
    private menuInterfaceEvent eventlistener;

    public EventRecyclerAdpater(Context context, List<Events> eventsList,Fragment fragment) {
        this.context = context;
        this.eventsList = eventsList;
        eventlistener= (menuInterfaceEvent) fragment;
    }

    @NonNull
    @Override
    public EventViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context)
                .inflate(R.layout.eventlist_layout_design,parent,false);

         EventViewholder  myViewholder=new EventViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewholder holder, final int position) {
        holder.eName.setText(eventsList.get(position).getEventName());
        holder.eStartDate.setText(eventsList.get(position).getEventSDate());
        holder.eEndDate.setText(eventsList.get(position).getEventEDate());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Event each row line click the Action another Fragment
                final String id = eventsList.get(position).getEventId();
                //Intent eventBudget=new Intent(Context,EventExpandibleActivity.class);

                Intent EventBudget=new Intent(context,EventExpandibleActivity.class);
                context.startActivity(EventBudget);

                //eventlistener.EventInfo(id);
                Toast.makeText(context,"Row Position :"+position,Toast.LENGTH_SHORT).show();

            }
        });

        /*
       // holder.ebudget.setText(eventsList.get(position).getEventBudget());
        holder.Emenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // menu design

                PopupMenu popupMenu = new PopupMenu(context, view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.person_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        final String id = eventsList.get(position).getEventId();
                        Toast.makeText(context, "select number:"+eventsList.get(position).getEventId(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                eventlistener.EventEdit(id);
                                break;
                            case R.id.menu_delete:
                                eventlistener.EventDelete(id);
                                break;
                        }
                        return false;
                    }
                });
            }
        });

*/


        holder.Emenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.person_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        final String id =eventsList.get(position).getEventId();

                        Toast.makeText(context, "Postion :"+eventsList.get(position).getEventId(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                eventlistener.EventEdit(id);
                                break;
                            case R.id.menu_delete:
                                eventlistener.EventDelete(id);
                                break;
                        }
                        return false;
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    //Inner Class View Holder
    class EventViewholder extends RecyclerView.ViewHolder{
        TextView eName,eStartDate,eEndDate,Emenu,ebudget;
        CardView cardView;
        public EventViewholder(@NonNull View itemView) {
            super(itemView);
            eName=itemView.findViewById(R.id.eventNameTV);
            eStartDate=itemView.findViewById(R.id.eventSdateTV);
            eEndDate=itemView.findViewById(R.id.eventEdateTV);
            Emenu=itemView.findViewById(R.id.row_event_menu);
            ebudget=itemView.findViewById(R.id.eventBudgetET);
            cardView=itemView.findViewById(R.id.cardViewCV);
        }
    }

    public  void eventUpdate(List<Events> elist){
        this.eventsList=elist;
        notifyDataSetChanged();
    }

    interface  menuInterfaceEvent{
        void EventEdit(String eventId);
        void EventDelete(String eventId);
        void EventInfo(String eventId);
    }
}
