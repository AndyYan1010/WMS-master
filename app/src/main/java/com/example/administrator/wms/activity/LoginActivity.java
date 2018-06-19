package com.example.administrator.wms.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.User;
import com.example.administrator.wms.util.UserUtil;
import com.example.administrator.wms.view.CustomProgress;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {
    protected static final String TAG = "LoginActivity";
    private EditText et_user,et_pwd;//用户名,密码
    private Button btn_log;//登录按钮
    private LinearLayout mLoginLinearLayout; // 登录内容的容器
    private LinearLayout mUserIdLinearLayout; // 将下拉弹出窗口在此容器下方显示
    private Animation mTranslate; // 位移动画
    private ImageView mMoreUser; // 下拉图标
    private ImageView mLoginMoreUserView; // 弹出下拉弹出窗的按钮
    private String mIdString;
    private String mPwdString;
    private ArrayList<User> mUsers; // 用户列表
    private ListView mUserIdListView; // 下拉弹出窗显示的ListView对象
    private MyAapter mAdapter; // ListView的监听器
    private PopupWindow mPop; // 下拉弹出窗
    private CustomProgress dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
        setListeners();
        mLoginLinearLayout.startAnimation(mTranslate); // Y轴水平移动

		/* 获取已经保存好的用户密码 */
        mUsers = UserUtil.getUserList(LoginActivity.this);

        if (mUsers.size() > 0) {
			/* 将列表中的第一个user显示在编辑框 */
            et_user.setText(mUsers.get(0).getId());
            et_pwd.setText(mUsers.get(0).getPwd());
        }

        LinearLayout parent = (LinearLayout) getLayoutInflater().inflate(
                R.layout.userifo_listview, null);
        mUserIdListView = (ListView) parent.findViewById(android.R.id.list);
        parent.removeView(mUserIdListView); // 必须脱离父子关系,不然会报错
        mUserIdListView.setOnItemClickListener(this); // 设置点击事
        mAdapter = new MyAapter(mUsers);
        mUserIdListView.setAdapter(mAdapter);
    }

    public void setViews(){
        et_user = (EditText) findViewById(R.id.login_edtId);
        et_pwd = (EditText) findViewById(R.id.login_edtPwd);
        btn_log = (Button) findViewById(R.id.login_btnLogin);
        mMoreUser = (ImageView) findViewById(R.id.login_more_user);
        mLoginMoreUserView = (ImageView) findViewById(R.id.login_more_user);
        mLoginLinearLayout = (LinearLayout) findViewById(R.id.login_linearLayout);
        mUserIdLinearLayout = (LinearLayout) findViewById(R.id.userId_LinearLayout);
        mTranslate = AnimationUtils.loadAnimation(this, R.anim.my_translate); // 初始化动画对象
    }



    public void initPop() {
        int width = mUserIdLinearLayout.getWidth() - 4;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mPop = new PopupWindow(mUserIdListView, width, height, true);
        mPop.setOnDismissListener(this);// 设置弹出窗口消失时监听器

        // 注意要加这句代码，点击弹出窗口其它区域才会让窗口消失
        mPop.setBackgroundDrawable(new ColorDrawable(0xffffffff));

    }

    public void setListeners(){
        mMoreUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPop == null) {
                    initPop();
                }
                if (!mPop.isShowing() && mUsers.size() > 0) {
                    // Log.i(TAG, "切换为角向上图标");
                    mMoreUser.setImageResource(R.drawable.login_more_down); // 切换图标
                    mPop.showAsDropDown(mUserIdLinearLayout, 2, 1); // 显示弹出窗口
                }
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 启动登录
                Log.i(TAG, mIdString + "  " + mPwdString);
//                if (mIdString == null || mIdString.equals("")) { // 账号为空时
//                    Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT)
//                            .show();
//                } else if (mPwdString == null || mPwdString.equals("")) {// 密码为空时
//                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT)
//                            .show();
//                } else {// 账号和密码都不为空时
//                    dialog=CustomProgress.show(LoginActivity.this,"登录中...", true, null);
//                    boolean mIsSave = true;
//                    try {
//                        Log.i(TAG, "保存用户列表");
//                        for (User user : mUsers) { // 判断本地文档是否有此ID用户
//                            if (user.getId().equals(mIdString)) {
//                                mIsSave = false;
//                                break;
//                            }
//                        }
//                        if (mIsSave) { // 将新用户加入users
//                            User user = new User(mIdString, mPwdString);
//                            mUsers.add(user);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
//                }
            }
        });

        et_user.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mIdString = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
        et_pwd.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mPwdString = s.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        et_user.setText(mUsers.get(i).getId());
        et_pwd.setText(mUsers.get(i).getPwd());
        mPop.dismiss();
    }

    /* PopupWindow对象dismiss时的事件 */
    @Override
    public void onDismiss() {
        // Log.i(TAG, "切换为角向下图标");
        mMoreUser.setImageResource(R.drawable.login_more_up);
    }

    /* ListView的适配器 */
    class MyAapter extends ArrayAdapter<User> {

        public MyAapter(ArrayList<User> users) {
            super(LoginActivity.this, 0, users);
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.listview_item, null);
            }

            TextView userIdText = (TextView) convertView
                    .findViewById(R.id.listview_userid);
            userIdText.setText(getItem(position).getId());

            ImageView deleteUser = (ImageView) convertView
                    .findViewById(R.id.login_delete_user);
            deleteUser.setOnClickListener(new View.OnClickListener() {
                // 点击删除deleteUser时,在mUsers中删除选中的元素
                @Override
                public void onClick(View v) {

                    if (getItem(position).getId().equals(mIdString)) {
                        // 如果要删除的用户Id和Id编辑框当前值相等，则清空
                        mIdString = "";
                        mPwdString = "";
                        et_user.setText(mIdString);
                        et_pwd.setText(mPwdString);
                    }
                    mUsers.remove(getItem(position));
                    mAdapter.notifyDataSetChanged(); // 更新ListView
                }
            });
            return convertView;
        }

    }


    /* 退出此Activity时保存users */
    @Override
    public void onPause() {
        super.onPause();
        try {
            UserUtil.saveUserList(LoginActivity.this, mUsers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
