# android-Permission6.0
android6.0权限测试


6.0权限的基本知识，以下是需要单独申请的权限，共分为9组，
每组只要有一个权限申请成功了，就默认整组权限都可以使用了,属于危险权限

group:android.permission-group.CONTACTS
    permission:android.permission.WRITE_CONTACTS
    permission:android.permission.GET_ACCOUNTS    
    permission:android.permission.READ_CONTACTS

  group:android.permission-group.PHONE
    permission:android.permission.READ_CALL_LOG
    permission:android.permission.READ_PHONE_STATE 
    permission:android.permission.CALL_PHONE
    permission:android.permission.WRITE_CALL_LOG
    permission:android.permission.USE_SIP
    permission:android.permission.PROCESS_OUTGOING_CALLS
    permission:com.android.voicemail.permission.ADD_VOICEMAIL

  group:android.permission-group.CALENDAR
    permission:android.permission.READ_CALENDAR
    permission:android.permission.WRITE_CALENDAR

  group:android.permission-group.CAMERA
    permission:android.permission.CAMERA

  group:android.permission-group.SENSORS
    permission:android.permission.BODY_SENSORS

  group:android.permission-group.LOCATION
    permission:android.permission.ACCESS_FINE_LOCATION
    permission:android.permission.ACCESS_COARSE_LOCATION

  group:android.permission-group.STORAGE
    permission:android.permission.READ_EXTERNAL_STORAGE
    permission:android.permission.WRITE_EXTERNAL_STORAGE

  group:android.permission-group.MICROPHONE
    permission:android.permission.RECORD_AUDIO

  group:android.permission-group.SMS
    permission:android.permission.READ_SMS
    permission:android.permission.RECEIVE_WAP_PUSH
    permission:android.permission.RECEIVE_MMS
    permission:android.permission.RECEIVE_SMS
    permission:android.permission.SEND_SMS
    permission:android.permission.READ_CELL_BROADCASTS
    
 将targetSdkVersion设置为23，注意，如果你将targetSdkVersion设置为>=23，则必须按照Android谷歌的要求，动态的申请权限，如果你暂时不打算支持动态权限申请，则targetSdkVersion最大只能设置为22
 在AndroidManifest.xml中申请你需要的权限，包括普通权限和需要申请的特殊权限。

开始申请权限，此处分为3部。

（1）检查是否由此权限checkSelfPermission()，如果已经开启，则直接做你想做的。

（2）如果未开启，则判断是否需要向用户解释为何申请权限shouldShowRequestPermissionRationale。

（3）如果需要（即返回true），则可以弹出对话框提示用户申请权限原因，用户确认后申请权限requestPermissions()，如果不需要（即返回false），则直接申请权限requestPermissions()。



demo中只有一个Activity 所有权限还是需要在mainfest中先注册 2个方法 
1.checkPermission:检测自己需要启用的权限是否开启了
2.requestPermission:申请自己需要启用的权限
