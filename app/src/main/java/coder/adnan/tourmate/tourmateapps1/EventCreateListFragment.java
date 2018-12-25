package coder.adnan.tourmate.tourmateapps1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import coder.adnan.tourmate.tourmateapps1.ForecastClasses.ForeCastWeather;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventCreateListFragment extends Fragment implements EventRecyclerAdpater.menuInterfaceEvent {

    private Button logout;
    private DatabaseReference userIDRef;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private EventRecyclerAdpater adapter;
    private List<Events> events = new ArrayList<>();
    private FloatingActionButton fab;
    private Context context;

    private  OnActiveFloatingAction fabAction;


    public EventCreateListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.fabAction= (OnActiveFloatingAction) context;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("UserInfo");
        userIDRef = userRef.child(user.getUid());


    //************  Database Data Show  ******************************
        userIDRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Events event = d.getValue(Events.class);
                    events.add(event);
                }
                    adapter.eventUpdate(events);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View eventView= inflater.inflate(R.layout.fragment_event_create_list, container, false);

        recyclerView = eventView.findViewById(R.id.recyclerRV);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EventRecyclerAdpater(getActivity(), events, this);
        recyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);
      // ****************   Create Event ***************************
        fab = eventView.findViewById(R.id.fabBTN);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              // event setup
              fabAction.evetnFabAdd();



              //Intent navigationIntent=new Intent(context,EventExpandibleActivity.class);
              //startActivity(navigationIntent);

             /*
              String keyId = userIDRef.push().getKey();
              //String eventId, String eventName, int eventSDate, int eventEDate
              Events event = new Events(keyId,"Dhaka","28/11/2018","30/11/2018");
              userIDRef.child(keyId).setValue(event);
           */
          }
      });

        return eventView;
     }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.logoutmenu,menu);
        Toast.makeText(context,"onCreateOptionsMenu",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.HomeBTN){
            Intent navigationIntent=new Intent(context,EventCreateListFragment.class);
            startActivity(navigationIntent);
            return true;
        }

        if (id == R.id.logout) {
            if(auth != null){
                auth.signOut();
                fabAction.Logout();
            }
            return true;
        }

        if (id == R.id.othersMenu) {
            Intent navigationIntent=new Intent(context,NavigationMainActivity.class);
            startActivity(navigationIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //*************************   Event Edit ****************************************
    @Override
    public void EventEdit(String eventId) {
        Toast.makeText(context, "Edit Event ID "+eventId, Toast.LENGTH_SHORT).show();
        Events event = new Events(eventId, "Bangladesh Tour","Dhaka","Ctg","25/2/2018",500);
        userIDRef.child(eventId).setValue(event);
        Toast.makeText(context, "Edit is Successfull..", Toast.LENGTH_SHORT).show();

       // fabAction.evetnFabAdd();  //70 % task baki ase
    }

    //******************** Event Delete **************************************
    @Override
    public void EventDelete(String eventId) {
        Toast.makeText(context, "Event ID"+eventId, Toast.LENGTH_SHORT).show();
        userIDRef.child(eventId).removeValue();
    }

    @Override
    public void EventInfo(String eventId) {
        fabAction.eventinfo();
    }
    interface  OnActiveFloatingAction{
        void  evetnFabAdd();
        void Logout();
        void eventinfo();
    }
}
