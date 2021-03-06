package com.jackrutorial.test1.Post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jackrutorial.test1.Profile.MyProfileFragment;
import com.jackrutorial.test1.Profile.OtherProfileFragment;
import com.jackrutorial.test1.R;
import com.jackrutorial.test1.BulletinBoard.BullentinBoardFragment;

public class BoardActivity extends AppCompatActivity {

    // 사용할 변수와 객체 선언
    private BottomNavigationView bottomNavigationView;
    String resultId, nickname;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    /////////////////////////////////
    // BoardActivity 에서 불러올 fragmen
    MyProfileFragment myProfileFragment;
    OtherProfileFragment otherProfileFragment;
    PostFragment postFragment;
    BullentinBoardFragment bullentinBoardFragment;
    WritingPostFragment writingPostFragment;
    DetailPostFragment detailPostFragment;
    EditPostFragment editPostFragment;

    ////////////////////////////////////
    //gridView
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        // Example에서 intent 넘겨받기
        Intent intent = getIntent();
//        resultId = intent.getStringExtra("resultId");
        nickname = intent.getStringExtra("nickname");


        // 하단 navigation - 탭 간 이동
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch(item.getItemId())
                {
                    case R.id.MyProfile: // 하단 nav에서 profile 선택 시
                        setFrag(0);
                        break;
                    case R.id.Post:
                        setFrag(1);
                        break;
                    case R.id.Temp:
                        setFrag(2);
                        break;
                }
                return true;

            }
        });
        //////////////////// 불러올 fragment 들에 값 넣어주기!!!
        myProfileFragment = new MyProfileFragment();
        otherProfileFragment = new OtherProfileFragment();
        postFragment = new PostFragment(resultId, nickname); // resultId, nickname
        bullentinBoardFragment = new BullentinBoardFragment();
        writingPostFragment = new WritingPostFragment(nickname);
        detailPostFragment = new DetailPostFragment();
        editPostFragment = new EditPostFragment();


        // defualt fragment = Post
        setFrag(1);

        gridView = (GridView) findViewById(R.id.addPhoto);

    }

    /////////////////////////////// 분기 나누기!!!
    // Replace Fragment
    public void setFrag(int n){
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch(n){
            case 0 :    // My profile
                fragmentTransaction.replace(R.id.main_frame, myProfileFragment); // replace
                fragmentTransaction.commit();
                break;

            case 1 :    // 게시판 Fragment
                fragmentTransaction.replace(R.id.main_frame, postFragment);
                fragmentTransaction.commit();
                break;

            case 2 :    ///////////////////////// 미정 !
                fragmentTransaction.replace(R.id.main_frame, bullentinBoardFragment);
                fragmentTransaction.commit();
                break;

            case 3 :    // 글 등록 구현
                fragmentTransaction.replace(R.id.main_frame, writingPostFragment);
                fragmentTransaction.commit();
                break;

            case 4 :    // 글 세부 정보 보여주는 fragment
                fragmentTransaction.replace(R.id.main_frame, detailPostFragment);
                fragmentTransaction.commit();
                break;

            case 5 :    // Other profile
                fragmentTransaction.replace(R.id.main_frame, otherProfileFragment); // replace
                fragmentTransaction.commit();
                break;

            case 6 :    // Edit post
                fragmentTransaction.replace(R.id.main_frame, editPostFragment); // replace
                fragmentTransaction.commit();
                break;

        }
    }
}