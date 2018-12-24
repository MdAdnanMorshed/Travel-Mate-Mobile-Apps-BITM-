package coder.adnan.tourmate.tourmateapps1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class EachEventInfoFragment extends Fragment {

    ProgressBar bar;
    TextView budgett;
    Button expantitureEvent;
    private Context context;
    private  AlertDialog.Builder mbuilder;
    private View mview;

    public EachEventInfoFragment() {
        // Required empty public constructor
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
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
                                expense_Add();
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
         double budget=(150*50)/100;
         bar.setProgress((int) budget);
         budgett.setText(String.valueOf(budget)+"%");
        return view;
     }

     void expense_Add(){
         Toast.makeText(context," Add Expense ",Toast.LENGTH_SHORT).show();

         //Expanse Alart Dialog

         mbuilder=new AlertDialog.Builder(context);
         mview=getLayoutInflater().inflate(R.layout.add_new_expense_alartdialog,null);
/*
                                add_BTN1=mview.findViewById(R.id.Add_BTN);
                                add_BTN1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mbuilder.setView(mview);
                                        EditText userInput = (EditText) mview.findViewById(R.id.menuET);
                                        String  itemName= userInput.getText().toString();
                                        selectList.add(itemName);

                                    }
                                });
*/
         mbuilder.setView(mview);
         AlertDialog dialog=mbuilder.create();
         dialog.show();

         //eventlistener.EventEdit(id);


     }

}

