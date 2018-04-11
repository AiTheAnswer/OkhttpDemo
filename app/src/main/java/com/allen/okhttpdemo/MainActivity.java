package com.allen.okhttpdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.allen.okhttpdemo.datatrasfer.IDataCallBack;
import com.allen.okhttpdemo.model.RespMenu;
import com.allen.okhttpdemo.model.RespMenuList;
import com.allen.okhttpdemo.model.StoreList;
import com.allen.okhttpdemo.net.CommonRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnGetInspectedStores;
    private Button mBtnPostInspectedStores;
    private Button mBtnGetMenuList;
    private Button mBtnFileDownload;
    private Button mBtnFileUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //初始化WinnerZhike的SDk
        initWinnerZhike();
        initListener();


    }


    private void initListener() {
        mBtnGetInspectedStores.setOnClickListener(this);
        mBtnPostInspectedStores.setOnClickListener(this);
        mBtnGetMenuList.setOnClickListener(this);
    }

    /**
     * 通过get方式获取场所列表
     */
    private void getUserSiteListByTypeForTodayInspected() {
        Map<String, String> specificParams = new HashMap<>();
        specificParams.put("token", "6c251cbe77f58097163deb172664979");
        specificParams.put("lang", "zh_CN");
        specificParams.put("uid", "dd65768b-935f-464c-b4f1-d1421e408c0d");
        specificParams.put("action", "getUserSiteListByTypeForTodayInspected");
        specificParams.put("siteType", "300");
        IDataCallBack<StoreList> callback = new IDataCallBack<StoreList>() {
            @Override
            public void onSuccess(StoreList result) {
                List<StoreList.SiteListBean> siteList = result.getSiteList();
                Toast.makeText(MainActivity.this, result.getStatus(), Toast.LENGTH_SHORT).show();
                StringBuffer sb = new StringBuffer("   接口返回的结果：");
                for (StoreList.SiteListBean store : siteList) {
                    Log.e("tog", store.toString());
                    sb.append(store.toString());
                }
                showAlertDialog(sb.toString());
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Log.e("tog", "errorCode = " + errorCode + "--- errorMessage = " + errorMessage);
                Toast.makeText(MainActivity.this, "errorCode = " + errorCode + "--- errorMessage = " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        };
        CommonRequest.getUserSiteListByTypeForTodayInspected(specificParams, callback);
    }

    private void postUserSiteListByTypeForTodayInspected() {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "dd65768b-935f-464c-b4f1-d1421e408c0d");
        params.put("lang", "zh_CN");
        params.put("siteType", "300");
        params.put("token", "6c251cbe77f58097163deb172664979");
        IDataCallBack<StoreList> callback = new IDataCallBack<StoreList>() {
            @Override
            public void onSuccess(StoreList result) {
                String status = result.getStatus();
                List<StoreList.SiteListBean> siteList = result.getSiteList();
                StringBuffer stringBuffer = new StringBuffer("     返回结果：   ");
                for (StoreList.SiteListBean response : siteList) {
                    stringBuffer.append(response.toString());
                    Log.e("tog", "   response = " + response.getSiteName() + "   " + response.getSiteKey());
                }
                showAlertDialog(stringBuffer.toString());
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Log.e("tog", "errorCode = " + errorCode + "  errorMessage = " + errorMessage);
            }
        };
        CommonRequest.postUserSiteListByTypeForTodayInspected(this, this, params, callback);
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("接口返回结果");
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void initWinnerZhike() {
        CommonRequest commonRequest = CommonRequest.getInstance();
        commonRequest.init(MainActivity.this, "http://118.31.165.187:8090/kl/api/v3");

    }

    private void initView() {
        mBtnGetInspectedStores = (Button) findViewById(R.id.btn_get_stores);
        mBtnPostInspectedStores = (Button) findViewById(R.id.btn_post_stores);
        mBtnGetMenuList = (Button) findViewById(R.id.btn_post_menu_list);
        mBtnFileDownload = (Button) findViewById(R.id.btn_download);
        mBtnFileUpload = (Button) findViewById(R.id.btn_upload);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_stores://获取现场巡店的场所列表(get方式请求)
                //获取现场巡检的场所列表
                getUserSiteListByTypeForTodayInspected();
                break;
            case R.id.btn_post_stores://获取现场巡店的场所列表（post方式请求）
                //获取现场巡检的场所列表
                postUserSiteListByTypeForTodayInspected();
                break;
            case R.id.btn_post_menu_list://获取菜单列表
                postMenuList();
                break;
            case R.id.btn_download://文件下载

                break;
            case R.id.btn_upload://文件上传
                break;
        }
    }

    private void postMenuList() {
        Map<String, String> params = new HashMap<>();
        params.put("uid", "dd65768b-935f-464c-b4f1-d1421e408c0d");
        params.put("lang", "zh_CN");
        params.put("menuId", "60022");
        params.put("token", "8bfc7a2afda7861e8b7a9277734f8712");
        CommonRequest.postMenuList(this, this, params, new IDataCallBack<RespMenuList>() {
            @Override
            public void onSuccess(RespMenuList result) {
                if (result.OK()) {
                    ArrayList<RespMenu> menuList = result.getMenuList();
                    StringBuffer stringBuffer = new StringBuffer("     返回结果：   ");
                    for (RespMenu response : menuList) {
                        stringBuffer.append(response.toString());
                        Log.e("tog", "   response = " + response.getMenuName() + "--orderId" + response.getOrderId());
                    }
                    showAlertDialog(stringBuffer.toString());
                } else {
                    Toast.makeText(MainActivity.this, result.getReason(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Toast.makeText(MainActivity.this, "errorCode =:" + errorCode + "errorMessage = " + errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
