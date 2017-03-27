package com.android.suning.permissiontest;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends Activity
{

    private static final String TAG = PermissionActivity.class.getSimpleName();
    /* 测试权限列表 */
    String[] permissionList = { Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR, Manifest.permission.CAMERA,
            Manifest.permission.BODY_SENSORS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    private static final int PERMISSION_TYPE = 1;

    /* 还没有通过用户同意的权限 */
    List<String> needApply = new ArrayList<>();
    private TextView textPermission;
    private TextView textPermissionGrantResults;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
    }

    private void initView()
    {
        textPermission = (TextView) findViewById(R.id.text_permission);
        textPermissionGrantResults = (TextView) findViewById(
                R.id.text_permission_grantResults);
    }

    public void checkPermission(View v)
    {
        Log.e(TAG, "checkPermission");
        checkPermissionList();
    }

    public void applyPermission(View v)
    {
        Log.e(TAG, "applyPermission");
        requestPermission();
    }

    public void checkPermissionList()
    {
        needApply.clear();
        for (String s : permissionList)
        {
            if (PackageManager.PERMISSION_DENIED == ContextCompat
                    .checkSelfPermission(this, s))
            {
                needApply.add(s);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < needApply.size(); i++)
        {
            stringBuffer.append(needApply.get(i));
            if (i != needApply.size() - 1)
            {
                stringBuffer.append(",");
            }
        }
        textPermission.setText(stringBuffer.toString());
    }

    private void requestPermission()
    {
        String[] strings = new String[needApply.size()];
        for (int i = 0; i < needApply.size(); i++)
        {
            strings[i] = needApply.get(i);
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            Log.d(TAG, "requestPermission shouldShowRequestPermissionRationale");
            /*该方法只有在用户在上一次已经拒绝过你的这个权限申请。
            也就是说，用户已经拒绝一次了，你又弹个授权框，
            你需要给用户一个解释，为什么要授权，则使用该方法*/
        }
        else
        {
            ActivityCompat.requestPermissions(this, strings, PERMISSION_TYPE);
        }
//        ActivityCompat.requestPermissions(this, strings, PERMISSION_TYPE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
            String[] permissions, int[] grantResults)
    {
        if (requestCode == PERMISSION_TYPE)
        {
            Log.d(TAG, "onRequestPermissionsResult permissions.length =" + permissions.length +
            "/grantResults.length =" + grantResults.length);
            if (grantResults.length > 0)
            {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < permissions.length; i++)
                {
                    stringBuffer.append(permissions[i]);
                    if (i != permissions.length - 1)
                    {
                        stringBuffer.append(",");
                    }
                }
                textPermission.setText(stringBuffer.toString());

                StringBuffer stringBuffer11 = new StringBuffer();
                for (int i = 0; i < grantResults.length; i++)
                {
                    stringBuffer11.append(grantResults[i]);
                    if (i != grantResults.length - 1)
                    {
                        stringBuffer11.append(",");
                    }
                }
                textPermissionGrantResults.setText(stringBuffer11.toString());

            }
        }
    }
}
