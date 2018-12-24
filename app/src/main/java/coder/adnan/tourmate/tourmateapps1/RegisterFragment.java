package coder.adnan.tourmate.tourmateapps1;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    Button signBTN,regBTN;
    EditText userMailET,UserpassET;
    TextView msgTV;
    private  Context context;
    private UserInterfaceRegister registerlistener;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view= inflater.inflate(R.layout.fragment_register, container, false);

         userMailET=view.findViewById(R.id.mailET);
         UserpassET=view.findViewById(R.id.passET);
        signBTN=view.findViewById(R.id.signInBTN);
        regBTN=view.findViewById(R.id.registerBTN);
         msgTV=view.findViewById(R.id.statusTV);

        signBTN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 registerlistener.onUserRegisterBack();
             }
         });
        regBTN.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SignInRegister();
             }
         });

        // Inflate the layout for this fragment
      return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.registerlistener= (RegisterFragment.UserInterfaceRegister) context;
    }

    public void SignInRegister(){
        String mailReg=userMailET.getText().toString();
        String passReg=UserpassET.getText().toString();

        auth.createUserWithEmailAndPassword(mailReg,passReg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //successfull Register
                currentUser=auth.getCurrentUser();
                msgTV.setText("Register is successfull!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                msgTV.setText("error Reg:"+e.getMessage());
            }
        });
    }

    interface  UserInterfaceRegister{
        void  onUserRegisterBack();

    }

}
