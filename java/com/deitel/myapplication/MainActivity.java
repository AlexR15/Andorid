package com.deitel.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.deitel.myapplication.adapter.UserAdapter;
import com.deitel.myapplication.api.ApiService;
import com.deitel.myapplication.api.RetroClient;
import com.deitel.myapplication.model.User;
import com.deitel.myapplication.model.UsersList;
import com.deitel.myapplication.utils.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private int page = 1;
    private int maxpage = 4;

    private ListView listView;
    private View parentView;
    private TextView textMessage;
    private Button buttonBack, buttonNext;

    private ArrayList<User> userList;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<>();

        parentView = findViewById(R.id.parentLayout);
        textMessage = findViewById(R.id.textViewMessage);
        buttonBack = findViewById(R.id.buttonBack);
        buttonNext = findViewById(R.id.buttonNext);

        listView = (ListView) findViewById(R.id.listView);

                /**
                 * Checking Internet Connection
                 */
                if (InternetConnection.checkConnection(getApplicationContext())) {

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiService();

                    /**
                     * Calling JSON
                     */
                    Call<UsersList> call = api.getMyJSON();

                    /**
                     * Enqueue Callback will be call when get response...
                     */
                    call.enqueue(new Callback<UsersList>() {
                        @Override
                        public void onResponse(Call<UsersList> call, Response<UsersList> response) {

                            if (response.isSuccessful()) {
                                /**
                                 * Got Successfully
                                 */
                                userList = (ArrayList<User>) response.body().getData();
                                maxpage = response.body().getTotalPages();

                                /**
                                 * Binding that List to Adapter
                                 */
                                adapter = new UserAdapter(MainActivity.this, userList);
                                listView.setAdapter(adapter);
                                textMessage.setText(R.string.string_getting_json_message);
                            } else {
                                textMessage.setText(R.string.string_getting_json_wrong);
                            }
                        }

                        @Override
                        public void onFailure(Call<UsersList> call, Throwable t) {
                            textMessage.setText(R.string.string_getting_json_wrong);
                        }
                    });

                } else {
                    textMessage.setText(R.string.string_internet_connection_not_available);
                }

                if(page == 1){buttonBack.setEnabled(false);}
                else {buttonBack.setEnabled(true);}

                if(page == maxpage){buttonNext.setEnabled(false);}
                else {buttonNext.setEnabled(true);}
    }


    public void onClickButtonNext(View view) {
        textMessage.setText(R.string.string_getting_json_download);
        page++;
    }

    public void onClickButtonDown(View view) {
        textMessage.setText(R.string.string_getting_json_download);
        page--;
    }
    }
