package in.naushad.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class camera extends AppCompatActivity {


    static final int CAM_REQUEST=1;
    private Button button;
    private EditText editText;
    private TextView tvListOfFiles;
    public String fileName;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        bindXML();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = editText.getText().toString();
                if (editText.getText().toString().trim().length() != 0) {
                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = getFile();
                    camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(camera_intent, CAM_REQUEST);
                } else {
                    Toast.makeText(camera.this, "Enter the Image Name", Toast.LENGTH_SHORT).show();
                }

            }
        });
        updateListOfImages();

    }

    private void bindXML() {
        button = (Button) findViewById(R.id.btn_capture_image);
        imageView = (ImageView) findViewById(R.id.iv_captured_image);
        editText = (EditText) findViewById(R.id.et_fileName);
        tvListOfFiles = (TextView) findViewById(R.id.tvListOfFiles);
    }

    public File getFile(){
        File folder = new File("sdcard/village_empowerment");
        if(!folder.exists()){
            folder.mkdir();
        }
        File img_file=new File(folder,fileName + ".jpg");
        return img_file;
    }
    public void updateListOfImages()
    {
        try{
        File folder = new File("sdcard/village_empowerment");
        String[] listOfFiles = folder.list();
        tvListOfFiles.setText("List of Images:");
        for (String f : listOfFiles) {
            tvListOfFiles.append("\n" + f);
        }
        }
        catch(NullPointerException ns){
            ns.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path="/sdcard/village_empowerment/"+fileName+".jpg";
        switch (resultCode){
            case Activity.RESULT_OK:
                if(getFile().exists()){
                    Toast.makeText(camera.this,"Image was stored at "+getFile().getAbsolutePath(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(camera.this,"There was error saving the file",Toast.LENGTH_LONG).show();
                }
                break;
            case Activity.RESULT_CANCELED:
                Toast.makeText(camera.this,"Image capture was cancelled",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        imageView.setImageDrawable(Drawable.createFromPath(path));
        updateListOfImages();
    }
}

