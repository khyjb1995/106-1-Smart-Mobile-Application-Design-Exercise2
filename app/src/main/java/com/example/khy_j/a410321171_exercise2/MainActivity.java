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
    private ListView lstData;

    String[] StrEdus= new String[] {""};
    String[] StrDatas=new String[100];
    String[] strSelect = new String[]{"Select"};
    int intCount=0;
    int remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = (Button) findViewById(R.id.button_View);
        btnClear = (Button) findViewById(R.id.button_Clear);
        edtName = (EditText) findViewById(R.id.editText_Name);
        spnEdu = (Spinner) findViewById(R.id.spnEdu);
        radBlood = (RadioGroup) findViewById(R.id.radioGroup);
        lstData = (ListView) findViewById(R.id.listview_Data);

        btnClear.setOnClickListener(btnClearListener);
        btnView.setOnClickListener(btnViewListener);
        spnEdu.setOnTouchListener(spnPreferListener);
        lstData.setOnItemLongClickListener(lstPreferListener);

        ArrayAdapter<String> adapterSelects = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_item,strSelect);
        adapterSelects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEdu.setAdapter(adapterSelects);
        lstData.setAdapter(null);
    }

    private Button.OnClickListener btnClearListener = new Button.OnClickListener() {
        @Override
        public void  onClick(View v){
            edtName.setText("");
            radBlood.clearCheck();
            ArrayAdapter<String> adapterSelects = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_item,strSelect);
            adapterSelects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnEdu.setAdapter(adapterSelects);

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
                        adbView.setTitle("Confirm to save?")
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

                                                String[] strTemp = new String[++intCount];
                                                strTemp[0] = "Entry " + intCount + ": "
                                                        + edtName.getText().toString() + " , "
                                                        + ((RadioButton) findViewById(radBlood.getCheckedRadioButtonId())).getText() + " , "
                                                        + spnEdu.getSelectedItem().toString();
                                                for(int i1 = 1; i1 < intCount; i1++)
                                                {
                                                    strTemp[i1] = StrDatas[i1-1];
                                                }
                                                StrDatas = new String[intCount];
                                                StrDatas = strTemp;

                                                ArrayAdapter<String> adapterDatas = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,StrDatas);
                                                lstData.setAdapter(adapterDatas);

                                                Toast toast = Toast.makeText(MainActivity.this,"Information Save Successful!",Toast.LENGTH_LONG);
                                                toast.show();

                                                edtName.setText("");
                                                radBlood.clearCheck();
                                                ArrayAdapter<String> adapterSelects = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_item,strSelect);
                                                adapterSelects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spnEdu.setAdapter(adapterSelects);
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
            if(spnEdu.getItemAtPosition(0).toString() == "Select"){
                StrEdus= new String[] {"Doctor","Master","Bachelor","Other"};
                ArrayAdapter<String> adapterEdus = new ArrayAdapter<String> (MainActivity.this,android.R.layout.simple_spinner_item,StrEdus);
                adapterEdus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnEdu.setAdapter(adapterEdus);
            }
            return false;
        }
    };
    private ListView.OnItemLongClickListener lstPreferListener = new ListView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder adbView = new AlertDialog.Builder(MainActivity.this);
            remove = i;
            adbView.setTitle("Confirm to delete?")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage(StrDatas[i])
                    .setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String[] strTemp = new String[intCount-1];
                                    for(int i1 = 0; i1 < intCount-1; i1++)
                                    {
                                        if(i1 < remove) strTemp[i1] = StrDatas[i1];
                                        else strTemp[i1] = StrDatas[i1+1];
                                    }

                                    StrDatas = new String[--intCount];
                                    StrDatas = strTemp;

                                    ArrayAdapter<String> adapterDatas = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,StrDatas);
                                    lstData.setAdapter(adapterDatas);

                                    Toast toast = Toast.makeText(MainActivity.this,"Delete Successful!",Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            }
                    )
                    .setNegativeButton("Cancel", null)
                    .show();
            return false;
        }
    };
}
