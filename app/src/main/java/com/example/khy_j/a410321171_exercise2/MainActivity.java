package com.example.khy_j.a410321171_exercise2;

        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.Spinner;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spnEdu;
    private Button btnView, btnClear;
    private EditText edtName;
    private RadioGroup radBlood;
    private RadioButton radResult;
    private ListView lstPrefer;

    String[] StrEdus= new String[] {""};
    String[] StrDatas=new String[] {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = (Button) findViewById(R.id.button_View);
        btnClear = (Button) findViewById(R.id.button_Clear);
        edtName = (EditText) findViewById(R.id.editText_Name);
        spnEdu = (Spinner) findViewById(R.id.spnEdu);
        radBlood = (RadioGroup) findViewById(R.id.radioGroup);

        btnClear.setWidth(btnView.getWidth());



        btnClear.setOnClickListener(btnClearListener);
        btnView.setOnClickListener(btnViewListener);
        spnEdu.setOnTouchListener(spnPreferListener);
    }

    private Button.OnClickListener btnClearListener = new Button.OnClickListener() {
        @Override
        public void  onClick(View v){
            edtName.setText("");
            radBlood.clearCheck();
            spnEdu.setAdapter(null);
            Toast toast = Toast.makeText(MainActivity.this,"Clear Success!",Toast.LENGTH_LONG);
            toast.show();
        }
    };

    private Button.OnClickListener btnViewListener = new Button.OnClickListener() {
        @Override
        public void  onClick(View v){
            if(edtName.length() > 0)
            {
                if(radBlood.getCheckedRadioButtonId() >= 0)
                {
                    if(spnEdu.getAdapter() != null)
                    {
                        AlertDialog.Builder adbView = new AlertDialog.Builder(MainActivity.this);
                        adbView.setTitle("Confirm ?")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage(
                                edtName.getText().toString() + " with blood "
                                + ((RadioButton) findViewById(radBlood.getCheckedRadioButtonId())).getText() + " and "
                                + spnEdu.getSelectedItem().toString() + " degree\n")
                            .setPositiveButton(
                                "Save",
                                new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        
                                        Toast toast = Toast.makeText(MainActivity.this,"Information Save Successful!",Toast.LENGTH_LONG);
                                        toast.show();
                                        edtName.setText("");
                                        radBlood.clearCheck();
                                        spnEdu.setAdapter(null);
                                    }
                                }
                            )
                            .setNegativeButton("Cancel", null)
                            .show();
                    }
                    else
                    {
                        Toast toast = Toast.makeText(MainActivity.this,"Please select your EDUCATION!",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(MainActivity.this,"Please select your BLOOD!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            else
            {
                Toast toast = Toast.makeText(MainActivity.this,"Please type your NAME!",Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };

    private Spinner.OnTouchListener spnPreferListener = new Spinner.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(spnEdu.getAdapter() == null){
                StrEdus= new String[] {"籃球","足球","棒球","其他"};
                ArrayAdapter<String> adapterBalls = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_item,StrEdus);
                adapterBalls.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnEdu.setAdapter(adapterBalls);
            }
            return false;
        }
    };
}
