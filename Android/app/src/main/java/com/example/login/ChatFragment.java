package com.example.login;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login.RemoteService.BASE_URL;

public class ChatFragment extends Fragment {
    Retrofit retrofit;
    RemoteService rs;
    GridView list;
    List<UserProfileVO> users;
    MyAdapter myadapter;
    String userid;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_chat,container,false);

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        list = view.findViewById(R.id.list);

        //로그인부터 유저값 받아오기
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");

        //채팅 가능한 사람들의 리스트
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        rs = retrofit.create(RemoteService.class);
        Call<List<UserProfileVO>> call=rs.eachlikeUser(userid);
        call.enqueue(new Callback<List<UserProfileVO>>() {
            @Override
            public void onResponse(Call<List<UserProfileVO>> call, Response<List<UserProfileVO>> response) {
                users = response.body();
                myadapter = new ChatFragment.MyAdapter(getContext(),R.layout.item_chat_user,users);
                list.setAdapter(myadapter);
            }

            @Override
            public void onFailure(Call<List<UserProfileVO>> call, Throwable t) {

            }
        });

    }
    class MyAdapter extends BaseAdapter {
        Context context;
        List<UserProfileVO> array;
        int layout;
        Bitmap bitmap;
        TextView btnchat;

        public MyAdapter(Context context, int layout, List<UserProfileVO> array) {
            this.context = context;
            this.array = array;
            this.layout = layout;
        }

        @Override
        public int getCount() {
            return array.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView =getLayoutInflater().inflate(R.layout.item_chat_user,parent,false);

                ImageView img=convertView.findViewById(R.id.img);
                TextView name=convertView.findViewById(R.id.name);
                TextView age=convertView.findViewById(R.id.age);
                TextView local=convertView.findViewById(R.id.local);
                TextView comment=convertView.findViewById(R.id.comment);
                TextView btnchat=convertView.findViewById(R.id.btnchat);

                //채팅할 사람의 프로필
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getContext(),HWJ_OtherActivity.class);
                        intent.putExtra("id",array.get(position).getUserid());
                        startActivity(intent);
                    }
                });

                //채팅하기
                btnchat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String chatwith=array.get(position).getUserid();
                        Intent intent=new Intent(getContext(),ChatActivity.class);
                        intent.putExtra("userid",userid);
                        intent.putExtra("chatwith",chatwith);
                        startActivity(intent);
                    }
                });

                name.setText(array.get(position).getUsername());
                age.setText(String.valueOf(array.get(position).getUserage()));
                local.setText(array.get(position).getLocalname());
                Thread thread=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try{
                            URL url=new URL(array.get(position).getSoloimg());
                            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                            conn.setDoInput(true);
                            conn.connect();

                            InputStream is=conn.getInputStream();
                            bitmap= BitmapFactory.decodeStream(is);

                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try{
                    thread.join();
                    img.setImageBitmap(bitmap);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            return convertView;
        }
    }
}