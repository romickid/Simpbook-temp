package nkucs1416.simpbook.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.support.design.widget.FloatingActionButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nkucs1416.simpbook.R;
import nkucs1416.simpbook.account.AccountActivity;
import nkucs1416.simpbook.record.RecordActivity;
import nkucs1416.simpbook.setting.SettingActivity;
import nkucs1416.simpbook.statement.StatementActivity;
import nkucs1416.simpbook.util.MyDate;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonRecord;
    private Button buttonAccount;
    private Button buttonCollection;
    private Button buttonStatement;
    private ImageView buttonSetting;

    private ImageView imageViewDay;
    private ImageView imageViewWeek;
    private ImageView imageViewMonth;
    private ImageView imageViewYear;
    private ImageView imageViewBasicInfo;

    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFindById();
        initButton();
        initImageView();
        updateData();
    }

    private void initFindById() {
        buttonRecord = findViewById(R.id.main_button_record);
        buttonAccount = findViewById(R.id.main_button_account);
        buttonStatement = findViewById(R.id.main_button_statement);
        buttonCollection = findViewById(R.id.main_button_collection);
        buttonSetting = findViewById(R.id.main_button_setting);

        imageViewDay = findViewById(R.id.main_imageview_info1);
        imageViewWeek = findViewById(R.id.main_imageview_info2);
        imageViewMonth = findViewById(R.id.main_imageview_info3);
        imageViewYear = findViewById(R.id.main_imageview_info4);
        imageViewBasicInfo = findViewById(R.id.main_tpimageview_basicinfo);

        textViewDate = findViewById(R.id.main_textview_date);
    }

    private void initButton() {
        initButtonRecord();
        initButtonAccount();
        initButtonStatement();
        initButtonCollection();
        initButtonSetting();
    }

    private void initButtonRecord() {
        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                intent.putExtra("tabID","1");
                startActivity(intent);
            }
        });
    }

    private void initButtonAccount() {
        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButtonStatement() {
        buttonStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, StatementActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButtonCollection() {
        buttonCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                intent.putExtra("tabID","0");
                startActivity(intent);
            }
        });
    }

    private void initButtonSetting() {
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initImageView() {
        initImageViewBasicInfo();
        initImageViewDay();
        initImageViewWeek();
        initImageViewMonth();
        initImageViewYear();
    }

    private void initImageViewBasicInfo() {
        imageViewBasicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, StatementActivity.class);
                intent.putExtra("filter","main_month");
                startActivity(intent);
            }
        });
    }

    private void initImageViewDay() {
        imageViewDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, StatementActivity.class);
                intent.putExtra("filter","main_day");
                startActivity(intent);
            }
        });
    }

    private void initImageViewWeek() {
        imageViewWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, StatementActivity.class);
                intent.putExtra("filter","main_week");
                startActivity(intent);
            }
        });
    }

    private void initImageViewMonth() {
        imageViewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, StatementActivity.class);
                intent.putExtra("filter","main_month");
                startActivity(intent);
            }
        });
    }

    private void initImageViewYear() {
        imageViewYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, StatementActivity.class);
                intent.putExtra("filter","main_year");
                startActivity(intent);
            }
        });
    }

    private void updateData() {
        updateDate();
    }

    private void updateDate() {
        MyDate today = new MyDate();
        String strToday = today.getYear() + "/" + today.getMonth() + "/" + today.getDay() + "  " + today.getWeekOfDate();
        textViewDate.setText(strToday);
    }

    private void updateBasicInfo() {
        // TODO: 12/5/18
    }
    
    private void updateToday() {
        // TODO: 12/5/18
    }
    
    private void updateWeek(){
        // TODO: 12/5/18 
    }
    
    private void updateMonth() {
        // TODO: 12/5/18  
    }
    
    private void updateYear() {
        // TODO: 12/5/18
    }


}
