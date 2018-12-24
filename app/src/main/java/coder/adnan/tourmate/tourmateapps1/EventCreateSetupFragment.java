package coder.adnan.tourmate.tourmateapps1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventCreateSetupFragment extends Fragment {

    List<Events>eventsList=new ArrayList<>();

    private  Context context;
     private  FirebaseAuth auth;
     private FirebaseUser user;
    private DatabaseReference userIDRef;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;

    private  eventCreateInterface createEventBacklist;

    public EventCreateSetupFragment() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference("UserInfo");
       // userRef = rootRef.child("Events");
        userIDRef = rootRef.child(user.getUid());



    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final EditText eName,edate,ebudget,estartlocation,edestination;
        Button createEventBTN;
        // Inflate the layout for this fragment
        View eventSetview= inflater.inflate(R.layout.fragment_event_create_setup, container, false);

       eName=eventSetview.findViewById(R.id.eventNameET);
        edate=eventSetview.findViewById(R.id.eventDateET);
        ebudget=eventSetview.findViewById(R.id.eventBudgetET);
        estartlocation=eventSetview.findViewById(R.id.eventStartLocationET);
        edestination=eventSetview.findViewById(R.id.eventDestinationET);
        createEventBTN=eventSetview.findViewById(R.id.eventCreateBTN);

        createEventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventName=eName.getText().toString();
                String eventbudget=ebudget.getText().toString();
                String eventlocation=estartlocation.getText().toString();
                String eventdestination=edestination.getText().toString();
                String evetnDate=edate.getText().toString();
                // Event Object Create



                String keyId = userIDRef.push().getKey();
                Events events=new Events(keyId,eventName,eventlocation,eventdestination,evetnDate,Double.valueOf(eventbudget) );
                userIDRef.child(keyId).setValue(events);
                createEventBacklist.eventLisenter();
               }
        });

        return eventSetview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.createEventBacklist= (eventCreateInterface) context;
    }


    interface  eventCreateInterface{
         void eventLisenter();

    }
}
