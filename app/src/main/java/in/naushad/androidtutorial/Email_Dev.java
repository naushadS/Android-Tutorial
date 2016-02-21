package in.naushad.androidtutorial;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email_Dev extends AppCompatActivity {
    EditText etEmailAddressCC,etIntroduction,etImprovements,etNewFeature;
    Button btSendMail;
    String EmailAddressCC,Introduction,Improvements,NewFeature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__dev);
        bindXML();
        Toast.makeText(getApplicationContext(),"Tell the developer what you think about this app!",Toast.LENGTH_LONG).show();
        btSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertEditTextVarsToStrings();
                String RecipientEmailAddress="naushadshukoor@gmail.com";
                String EmailAddressCCs[]={EmailAddressCC};
                String Message="Hey Naushad,"
                        +"\n"+"I just wanted to say " +Introduction+"."
                        +"\n"+"Improvements:"
                        +"\n"+Improvements+"."
                        +"\n"+"New Features Ideas:"
                        +"\n"+NewFeature
                        +"\n P.S I like this app a lot"
                        ;
                Intent sendEmail=new Intent(Intent.ACTION_SENDTO);
                sendEmail.setType("text/plain");
                sendEmail.setData(Uri.parse("mailto:" + RecipientEmailAddress)); //lists out only the amail apps instead of all the apps
                sendEmail.putExtra(Intent.EXTRA_CC, EmailAddressCCs);
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "AndroidTutorials-App Fan-Mail");
                sendEmail.putExtra(Intent.EXTRA_TEXT, Message);
                sendEmail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //returns the client back to the email_dev activity instead of the email client main activity
                try {
                    startActivity(Intent.createChooser(sendEmail, "Send Mail using..."));
                }
                catch(ActivityNotFoundException e){
                    Toast.makeText(Email_Dev.this,"No Email Clients installed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindXML(){
        etEmailAddressCC = (EditText) findViewById(R.id.etEmailAddressCC);
        etIntroduction = (EditText) findViewById(R.id.etIntroduction);
        etImprovements = (EditText) findViewById(R.id.etImprovements);
        etNewFeature = (EditText) findViewById(R.id.etNewFeature);
        btSendMail = (Button) findViewById(R.id.btSendEmail);
    }

    private void convertEditTextVarsToStrings(){
        EmailAddressCC = etEmailAddressCC.getText().toString();
        Introduction = etIntroduction.getText().toString();
        Improvements = etImprovements.getText().toString();
        NewFeature = etNewFeature.getText().toString();
    }
}
