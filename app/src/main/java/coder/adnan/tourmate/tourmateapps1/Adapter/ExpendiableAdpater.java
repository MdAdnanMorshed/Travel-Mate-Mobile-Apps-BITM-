package coder.adnan.tourmate.tourmateapps1.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import coder.adnan.tourmate.tourmateapps1.R;

public class ExpendiableAdpater extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader= null;
    private HashMap<String,List<String>>listHashMap;

    public ExpendiableAdpater(Context context, List<String> listDataHeader, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(listDataHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle= (String) getGroup(i);

        if (view==null){

            LayoutInflater layoutInflater= (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.list_group_expandable_layout,null);

        }

        TextView listGroupTV=view.findViewById(R.id.list_GroupTV);
        listGroupTV.setTypeface(null, Typeface.BOLD);
        listGroupTV.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        String headerChild=(String)getChild(i,i1);

        if (view==null){

            LayoutInflater layoutInflater= (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.list_item_expandable_layout,null);
        }

        TextView listItemTV=view.findViewById(R.id.list_itemTV);
        listItemTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getId();

                Toast.makeText(context,"Item:"+i1,Toast.LENGTH_SHORT).show();
            }
        });
        listItemTV.setTypeface(null, Typeface.BOLD);
        listItemTV.setText(headerChild);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
