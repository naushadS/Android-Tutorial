package in.naushad.androidtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class bookDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        initialize();
/*
        //bookdetails fragment
        fragmentBookDetails1 myFrag1=new fragmentBookDetails1();
        fragmentBookDetails2 myFrag2=new fragmentBookDetails2();

        //fragment manager & fragment transaction
        FragmentManager myFragManager=getFragmentManager();
        FragmentTransaction myFragTransaction=myFragManager.beginTransaction();

        //add & commit
        myFragTransaction.add(R.id.rlBookDetails,myFrag1,"BookDetails1");
        myFragTransaction.add(R.id.rlBookDetails,myFrag2,"BookDetails2");
        myFragTransaction.commit();
        */

    }

    private void initialize(){
        getSupportActionBar().setTitle("Book Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
