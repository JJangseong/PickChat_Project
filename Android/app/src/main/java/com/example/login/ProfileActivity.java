package com.example.login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class ProfileActivity extends AppCompatActivity {
    private static final int CALL_CAMERA = 0;
    private static final int CALL_GALLERY = 1;
    ImageView image1, image2;
    Retrofit retrofit;
    String img, code,img1, username,userid;
    Uri uriImage,uriImage2;
    Bitmap bitmap;
    RemoteService rs;
    List<User_listVO> array;
    User_listVO vo;
    int indexSelect = 0;
    int num = 0;
    RadioGroup Radio;
    TextView localcode, passtext, passTestText, userjob, nicknameText, nicknameText1;
    EditText userheight;
    Button insertbtn,btnCheck;
    RadioButton gendercode1, gendercode2;
    EditText  userpassword,
            usernickname, userage, usercomment, userpasswordtest;
    boolean GenderisChecked;
    String[] lcode;
    String[] lname;

    String[] hcode;
    String[] hname;

    String[] jcode;
    String[] jname;
    String[] all;

    File file,file1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        vo = new User_listVO();

        Intent intent=getIntent();
        //userid= intent.getStringExtra("userid");
        SharedPreferences sharedPreferences= getSharedPreferences("userid",MODE_PRIVATE);
        userid = sharedPreferences.getString("userid","");
        username=intent.getStringExtra("username");

        userpassword = findViewById(R.id.userpassword);
        usernickname = findViewById(R.id.usernickname);
        userage = findViewById(R.id.userage);
        userjob = findViewById(R.id.userjob);
        userheight = findViewById(R.id.userheight);
        usercomment = findViewById(R.id.usercomment);
        gendercode1 = findViewById(R.id.gendercode1);
        gendercode2 = findViewById(R.id.gendercode2);
        btnCheck=findViewById(R.id.btnCheck);

        Radio = findViewById(R.id.Radio);
        passtext = findViewById(R.id.passtext);
        userpasswordtest = findViewById(R.id.userpasswordtest);
        passTestText = findViewById(R.id.passTestText);
        nicknameText = findViewById(R.id.nicknameText);
        nicknameText1 = findViewById(R.id.nicknameText1);


        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        localcode = findViewById(R.id.localcode);


        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        /*사진추가하고 확인 버튼 클릭시*/
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody mFile = RequestBody.create(MediaType.parse("file/*"), file);
                final MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("uploadfile", file.getName(), mFile);
                RequestBody mFile1 = RequestBody.create(MediaType.parse("file/*"), file1);
                final MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("uploadfile1", file1.getName(), mFile1);

                Call<ResponseBody> image = rs.uploadImg(fileToUpload,fileToUpload1);
                image.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //response가 성공했을떄 결과vo로????????????????????????????, 경로 실패
                        String location="";
                        System.out.println("성공?????????????????????????");
                        try {
                            location = "http://sungjin5891.cafe24.com/img/";
                            System.out.println("location.................."+location.toString());
                            vo.setImgpath(location);
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                  @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error",t.getMessage());
                    }
                });
            }
        });


        //지역 선택
        localcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all = ProfileActivity.this.getResources().getStringArray(R.array.home);
                lcode = new String[all.length];
                lname = new String[all.length];
                for (int i = 0; i < all.length; i++) {
                    String[] g = all[i].split(",");
                    lcode[i] = g[0];
                    lname[i] = g[1];

                }

                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("지역을 선택해주세요.")
                        .setSingleChoiceItems(lname, indexSelect,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        indexSelect = which;
                                    }
                                })

                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                TextView text = (TextView) findViewById(R.id.localcode);
                                text.setText("" + lname[indexSelect]);

                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

        //키선택
//        userheight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String[] all2 = ProfileActivity.this.getResources().getStringArray(R.array.height);
//                hcode = new String[all2.length];
//                hname = new String[all2.length];
//                for (int i = 0; i < all2.length; i++) {
//                    String[] g = all2[i].split(",");
//                    hcode[i] = g[0];
//                    hname[i] = g[1];
//                }
//                ;
//                new AlertDialog.Builder(ProfileActivity.this)
//                        .setTitle("키를 선택해주세요.")
//                        .setSingleChoiceItems(hname, indexSelect,
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        indexSelect = which;
//                                    }
//                                })
//
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                TextView text = (TextView) findViewById(R.id.userheight);
//                                text.setText("" + hname[indexSelect]);
//
//                            }
//                        })
//                        .setNegativeButton("취소", null)
//                        .show();
//
//
//            }
//        });

        //직업선택
        userjob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] all = ProfileActivity.this.getResources().getStringArray(R.array.job);
                jcode = new String[all.length];
                jname = new String[all.length];
                for (int i = 0; i < all.length; i++) {
                    String[] g = all[i].split(",");
                    jcode[i] = g[0];
                    jname[i] = g[1];
                }
                ;
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("직업을 선택해주세요.")
                        .setSingleChoiceItems(jname, indexSelect,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        indexSelect = which;
                                    }
                                })

                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                TextView text = (TextView) findViewById(R.id.userjob);
                                text.setText("" + jname[indexSelect]);

                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

        //닉네임중복체크
        usernickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Call<Integer> call = rs.checknickname(usernickname.getText().toString());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Integer check;
                        if (response.body() == null) {
                            check = -1;
                            nicknameText1.setVisibility(View.GONE);

                            return;
                        } else {
                            check = Integer.parseInt(response.body().toString());
                            // System.out.println("check..................." + check + usernickname.getText().toString());
                        }

                        if (check == 0) {
                            nicknameText1.setVisibility(View.VISIBLE);
                            nicknameText.setVisibility(View.GONE);
                            Toast.makeText(ProfileActivity.this,"회원가입 가능한 닉네임 입니다",Toast.LENGTH_SHORT).show();
                        } else {
                            nicknameText1.setVisibility(View.GONE);
                            nicknameText.setVisibility(View.VISIBLE);
                            Toast.makeText(ProfileActivity.this, "이미 사용중인 닉네임 입니다.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                    }
                });

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        int degree = getExifOrientation(img);
        bitmap = BitmapFactory.decodeFile(img);
        bitmap = getRotatedBitmap(bitmap, degree);
        permissionCheck();

        //라디오 버튼 남여
        Radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                GenderisChecked = checkedRadioButton.isChecked();
                if (checkedRadioButton == group.findViewById(R.id.gendercode1)) {
                    vo.setGendercode(0);
                } else {
                    vo.setGendercode(1);
                }

            }
        });

        //비밀번호 체크
        userpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 4) {
                    passtext.setVisibility(View.VISIBLE);
                } else {
                    passtext.setVisibility(View.GONE);
                }
            }
        });

        //비밀번호재입력 체크
        userpasswordtest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userpassword.getText().toString().equals(userpasswordtest.getText().toString())) {
                    passTestText.setVisibility(View.GONE);
                } else {
                    passTestText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        //정보 입력 후 저장
        insertbtn = findViewById(R.id.insertbtn);
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strUserpassword = userpassword.getText().toString();
                final String strUsernickname = usernickname.getText().toString();
                final int strUserage = Integer.parseInt(userage.getText().toString());
                final int strUserheight=Integer.parseInt(userheight.getText().toString());
                final String strUserjob = userjob.getText().toString();
                final String strUsercomment = usercomment.getText().toString();

                for (int i = 0; i < all.length; i++) {
                    String name = localcode.getText().toString();
                    if (lname[i].equals(name)) {
                        code = lcode[i];
                    }
                }

                final String strLocalcode = code;
                vo.setUserpassword(strUserpassword);
                vo.setUsernickname(strUsernickname);
                vo.setUserage(strUserage);
                vo.setUserjob(strUserjob);
                vo.setUserheight(strUserheight);
                vo.setUsercomment(strUsercomment);
                vo.setLocalcode(strLocalcode);
                vo.setUserid(userid);
                vo.setUsername(username);

                System.out.println("....................." + vo.toString());

                Call<Void> call = rs.userinsert(vo);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println(vo.toString());
                        onPostResume();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("오류", t.getMessage());
                    }
                });
                Intent intent = new Intent(ProfileActivity.this, DetailsActivity.class);
                startActivity(intent);
            }


        });
    }

//사진
    public void mClick(View view) {
        switch (view.getId()) {
            case R.id.image:
            case R.id.image1:
                AlertDialog.Builder box = new AlertDialog.Builder(ProfileActivity.this);
                box.setTitle("이미지 선택 방법을 결정하세요!");
                box.setPositiveButton("사진촬영", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        num = 0;
                        img = Environment.getExternalStorageDirectory().getAbsolutePath() + "/attachimage.jpg";
                        file=new File(img);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                            uriImage = FileProvider.getUriForFile(ProfileActivity.this,
                                    getApplicationContext().getPackageName() + ".provider", file);
                        }
                        // API 24 미만 일경우..
                        else {
                            uriImage = Uri.fromFile(file);
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                        System.out.println("카메라 이미지 경로............"+uriImage);
                        startActivityForResult(intent, CALL_CAMERA);
                    }
                });
                box.show();
                break;
            case R.id.image2:
                box = new AlertDialog.Builder(ProfileActivity.this);
                num = 1;
                box.setTitle("이미지 선택 방법을 결정하세요!");
                box.setPositiveButton("사진촬영", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        img1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/attachimage2.jpg";
                        file1=new File(img1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {  // API 24 이상 일경우..
                            uriImage2 = FileProvider.getUriForFile(ProfileActivity.this,
                                    getApplicationContext().getPackageName() + ".provider", file1);
                        }
                        // API 24 미만 일경우..
                        else {
                            uriImage2 = Uri.fromFile(file1);
                        }
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage2);
                        startActivityForResult(intent, CALL_CAMERA);
                    }
                });
                box.setNeutralButton("앨범선택", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, CALL_GALLERY);
                    }
                });
                box.show();
                break;
        }

    }

    private int getExifOrientation(String file) {
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;
                }
            }
        }

        return 0;
    }

    private Bitmap getRotatedBitmap(Bitmap bitmap, int degree) {
        if (degree != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(degree, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try {
                Bitmap tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                if (bitmap != tmpBitmap) {
                    bitmap.recycle();
                    bitmap = tmpBitmap;
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }
//화면에 사진 출력
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CALL_CAMERA:
                    if (num == 0) {
                        image1.setImageBitmap(getRotatedBitmap(BitmapFactory.decodeFile(img), getExifOrientation(img)));
                    } else {
                        image2.setImageBitmap(getRotatedBitmap(BitmapFactory.decodeFile(img1), getExifOrientation(img1)));
                    }
                    break;
                case CALL_GALLERY:
                    try {
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(data.getData(), projection, null, null, null);
                        cursor.moveToFirst();
                        img1 = cursor.getString(cursor.getColumnIndex(projection[0]));
                        cursor.close();
                         image2.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
                        image2.setImageBitmap(getRotatedBitmap(BitmapFactory.decodeFile(img1), getExifOrientation(img1)));
                        System.out.println("갤러리 이미지 경로..................."+img1);
                    } catch (Exception e) {
                    }
                    break;

            }
        }
    }




    public void permissionCheck() {
        if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }
}

