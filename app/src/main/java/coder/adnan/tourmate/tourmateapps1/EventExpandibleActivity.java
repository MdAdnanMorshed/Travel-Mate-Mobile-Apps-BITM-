package coder.adnan.tourmate.tourmateapps1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import coder.adnan.tourmate.tourmateapps1.Adapter.ExpendiableAdpater;
import coder.adnan.tourmate.tourmateapps1.DataTimePOJO.EventExpIncome;


public class EventExpandibleActivity extends AppCompatActivity {

    ExpandableListView expandableLV;
    ExpendiableAdpater expendiableAdpater;
    ProgressBar progressBar;
    TextView eventbudget1,target;

    List<String> headerList;
    HashMap<String,List<String>> hashMapList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_expandible);
        progressBar=findViewById(R.id.progressBar);
        target=findViewById(R.id.targetbudgetTV);

        TargetEventTk();

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

    public void TargetEventTk(){
        Intent intent=getIntent();
        String ReciveTk=intent.getStringExtra("tkeventtarget");
        String eventBudgetId=intent.getStringExtra("eventtargetPostion");
        Toast.makeText(this, "eventBudgetId"+eventBudgetId, Toast.LENGTH_SHORT).show();
        int eventBudget=Integer.parseInt(ReciveTk);
        eventbudget1=findViewById(R.id.eventTkET);
        target.setText(String.valueOf(eventBudget)+"Tk");

        int exIncome=0;
        eventbudget1.setText(exIncome+"tk");
        int persentTk1=(exIncome*100)/eventBudget;
        int persentTk=persentTk1/100;
        progressBar.setProgress(persentTk);
    }
}

