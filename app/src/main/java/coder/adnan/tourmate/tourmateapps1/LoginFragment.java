package coder.adnan.tourmate.tourmateapps1;


import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    Button signInBTN,signOutBTN;
    EditText userMailET,UserpassET;
    String pass;
    TextView msgTV;
    private  Context context;
    private  UserInterfaceAuthListener listener;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

       userMailET=view.findViewById(R.id.mailLoginET);
       UserpassET=view.findViewById(R.id.passwordloginET);
       signInBTN=view.findViewById(R.id.logInBTN);
       signOutBTN=view.findViewById(R.id.regInBTN);
       msgTV=view.findViewById(R.id.statusTV);

       signInBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //sign
               SignInLogin();
           }
       });

       signOutBTN.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //Reg
              // SignInRegister();
               listener.onRegisterInterface();
              // reglistener.ontestingReg();
           }
       });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.listener= (UserInterfaceAuthListener) context;


    }



    public void SignInLogin(){

        final String mail=userMailET.getText().toString();
         pass=UserpassET.getText().toString();

        if(mail==null && pass==null){
            msgTV.setText("Must be Fill up..");
        }else {


        auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //successfull Login


                if(task.isSuccessful()) {

                    currentUser = auth.getCurrentUser();
                    listener.onAuthcomplete();
                    msgTV.setText("Login is successfull:" +currentUser.getDisplayName());
                }
                else{
                    msgTV.setText("Login isn't successfull:" +currentUser.getEmail());
                }
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //unsuccessfull Register
                msgTV.setText("Login Error :" +e);
            }
        });
        }
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

    interface  UserInterfaceAuthListener{
        void  onAuthcomplete();
        void onRegisterInterface();
    }

}
