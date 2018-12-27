package coder.adnan.tourmate.tourmateapps1;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import coder.adnan.tourmate.tourmateapps1.DataTimePOJO.DatePickerObj;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventCreateSetupFragment extends Fragment {

    String date;

    Context context;
    private Calendar calendar;
    private int year,month,day;
    private DatePickerObj dateObj;
    private  String CurrentDate;
    String googleMapType[]={"Select Start Location ","Dhaka","Ctg","Khulna"};
    DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               SimpleDateFormat simpleformat=new SimpleDateFormat("dd/mm/yyyy");
               Calendar calendar=Calendar.getInstance();
                 date=simpleformat.format(calendar.getTime());

        }
    };




    List<Events>eventsList=new ArrayList<>();

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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.createEventBacklist= (eventCreateInterface) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat sf=new SimpleDateFormat("dd/mm/yyyy");
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(year,month,year);

        date=sf.format(calendar1.getTime());
        dateObj=new DatePickerObj(year,month,day);

         CurrentDate= DateFormat.getDateInstance().format(calendar.getTime());
        Toast.makeText(context, "Current Date"+date, Toast.LENGTH_SHORT).show();
        // edate.setText(CurrentDate);



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
        Button createEventBTN,datePicker;

        // Inflate the layout for this fragment
        View eventSetview= inflater.inflate(R.layout.fragment_event_create_setup, container, false);


        //******** Date Picker Format ***********************************
        datePicker=eventSetview.findViewById(R.id.datePickerBTN);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker=new DatePickerDialog(context,dateListener,dateObj.getYear(),dateObj.getMonth(),dateObj.getDay());
                 datePicker.show();
            }
        });


       eName=eventSetview.findViewById(R.id.eventNameET);
        edate=eventSetview.findViewById(R.id.eventDateET);
        ebudget=eventSetview.findViewById(R.id.eventBudgetET);
        estartlocation=eventSetview.findViewById(R.id.eventStartLocationET);
        edestination=eventSetview.findViewById(R.id.eventDestinationET);
        createEventBTN=eventSetview.findViewById(R.id.eventCreateBTN);
        edate.setText(date);

        // edate.setText (dateObj.getDay()+"/"+dateObj.getMonth()+"/"+dateObj.getDay());



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
                Events events=new Events(keyId,eventName,eventlocation,eventdestination,evetnDate,Integer.valueOf(eventbudget) );
                userIDRef.child(keyId).setValue(events);
                createEventBacklist.eventLisenter();
               }
        });

        return eventSetview;
    }




    interface  eventCreateInterface{
         void eventLisenter();

    }
}
