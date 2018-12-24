package coder.adnan.tourmate.tourmateapps1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import coder.adnan.tourmate.tourmateapps1.Adapter.ExpendiableAdpater;


public class EventExpandibleActivity extends AppCompatActivity {

    ExpandableListView expandableLV;
    ExpendiableAdpater expendiableAdpater;

    List<String> headerList;
    HashMap<String,List<String>> hashMapList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_expandible);

        expandableLV=findViewById(R.id.expandableLV);
        intData();
        expendiableAdpater=new ExpendiableAdpater(this,headerList,hashMapList);
        expandableLV.setAdapter(expendiableAdpater);
    }
    public void intData(){
        headerList=new ArrayList<>();
        hashMapList=new HashMap<>();
        headerList.add("Expenditure");
        headerList.add("Moments");
        headerList.add("More on Events");

        List<String>listExpense=new ArrayList<>();
        listExpense.add("Add New Expense");
        listExpense.add("View All Expense");
        listExpense.add("Add More Budget");


        List<String>listpic=new ArrayList<>();
        listpic.add("Take A Photo");
        listpic.add("View Gallary");
        listpic.add("View All Gallary");


        List<String>listEvents=new ArrayList<>();
        listEvents.add("Edit Event");
        listEvents.add("Delete Event");

        hashMapList.put(headerList.get(0),listExpense);
        hashMapList.put(headerList.get(1),listpic);
        hashMapList.put(headerList.get(2),listEvents);

    }

    /*

    private DatabaseReference userIDRef;
    private DatabaseReference rootRef;
    private DatabaseReference userRef;
    private DatabaseReference userRefexpense;
    private DatabaseReference userRefexpense1;
    private FirebaseAuth auth;
    private FirebaseUser user;

    List<ExpenseEvent>expenseList=null;
    List<ExpenseEvent>expenseList_Show=null;


    ProgressBar bar;
    TextView budgett;
    Button expantitureEvent;
    private Context context;
    private  AlertDialog.Builder mbuilder;
    private View mview=null;
    ExpenseEvent event=null;

    public EachEventInfoFragment() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("UserInfo");
        userIDRef = userRef.child(user.getUid());

         userRefexpense=userIDRef.child(userIDRef.getKey());
*/
    /*

        rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("UserInfo");
        userIDRef = userRef.child(user.getUid());
        userRefexpense=userRef.child("Expense");
        userRefexpense1 = userRefexpense.child(user.getUid());
*/
    /*
        userRefexpense.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            //events.clear();
            for(DataSnapshot d : dataSnapshot.getChildren()){
                ExpenseEvent event = d.getValue(ExpenseEvent.class);

                expenseList_Show.add(event);
            }
            //adapter.eventUpdate(events);
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
        View view= inflater.inflate(R.layout.fragment_each_event_info, container, false);
        expantitureEvent=view.findViewById(R.id.expantitureEventBTN);
        expantitureEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Menu add
                PopupMenu popupMenu = new PopupMenu(context, view);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.eventexpentiture,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //final String id = eventsList.get(position).getEventId();

                        switch (item.getItemId()){
                            case R.id.expense_Add:
                                expense_Add();
                                break;
                            case R.id.expense_View:
                                expsense_View();
                                Toast.makeText(context," expense_View ",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.expense_budget_Add:
                                expense_Add();
                                Toast.makeText(context," expense_budget_Add ",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });

        budgett=view.findViewById(R.id.budgettesting);
        bar=view.findViewById(R.id.progressBar);

        bar.setProgress(50);
        budgett.setText(String.valueOf(2000)+"%");
        return view;
    }

    void expsense_View(){}
    void expense_Add(){
        Toast.makeText(context," Add Expense ",Toast.LENGTH_SHORT).show();

        //Expanse Alart Dialog

        mbuilder=new AlertDialog.Builder(context);
        mview=getLayoutInflater().inflate(R.layout.add_new_expense_alartdialog,null);

        final EditText expenseDesET,expenseamountET;
        Button expenseAddBTN;

        expenseDesET=mview.findViewById(R.id.expense_DescriptionET);
        expenseamountET=mview.findViewById(R.id.expense_AmountET);
        expenseAddBTN=mview.findViewById(R.id.expnseBTN);

        expenseAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbuilder.setView(mview);
                String  expense_Amount= expenseamountET.getText().toString();
                String  expense_Title= expenseamountET.getText().toString();

                String keyId = userIDRef.push().getKey();
                ExpenseEvent expenseEvents=new ExpenseEvent(keyId,Double.valueOf(expense_Amount),expense_Title);
                expenseList.add(expenseEvents);
                // Events events=new Events(eventName,eventlocation,eventdestination,evetnDate,Double.valueOf(eventbudget) );
                userIDRef.child(keyId).setValue(expenseEvents);
                //eventlisener.eventSetup();
                //selectList.add(itemName);
            }
        });
        mbuilder.setView(mview);
        AlertDialog dialog=mbuilder.create();
        dialog.show();
        //eventlistener.EventEdit(id);

     */


}

