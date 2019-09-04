package com.wonly.lib_base.permission;

import android.content.Context;

import com.wonly.lib_base.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:获取权限名称
 */
public class TransformUtils {

    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR";
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";

    public static final String CAMERA = "android.permission.CAMERA";

    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";

    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";

    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";

    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
    public static final String USE_SIP = "android.permission.USE_SIP";
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";

    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS";

    public static final String SEND_SMS = "android.permission.SEND_SMS";
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    public static final String READ_SMS = "android.permission.READ_SMS";
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";

    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    public static final class Group {
        public static final String[] CALENDAR = new String[]{
                TransformUtils.READ_CALENDAR,
                TransformUtils.WRITE_CALENDAR};

        public static final String[] CAMERA = new String[]{TransformUtils.CAMERA};

        public static final String[] CONTACTS = new String[]{
                TransformUtils.READ_CONTACTS,
                TransformUtils.WRITE_CONTACTS,
                TransformUtils.GET_ACCOUNTS};

        public static final String[] LOCATION = new String[]{
                TransformUtils.ACCESS_FINE_LOCATION,
                TransformUtils.ACCESS_COARSE_LOCATION};

        public static final String[] MICROPHONE = new String[]{TransformUtils.RECORD_AUDIO};

        public static final String[] PHONE = new String[]{
                TransformUtils.READ_PHONE_STATE,
                TransformUtils.CALL_PHONE,
                TransformUtils.READ_CALL_LOG,
                TransformUtils.WRITE_CALL_LOG,
                TransformUtils.ADD_VOICEMAIL,
                TransformUtils.USE_SIP,
                TransformUtils.PROCESS_OUTGOING_CALLS};

        public static final String[] SENSORS = new String[]{TransformUtils.BODY_SENSORS};

        public static final String[] SMS = new String[]{
                TransformUtils.SEND_SMS,
                TransformUtils.RECEIVE_SMS,
                TransformUtils.READ_SMS,
                TransformUtils.RECEIVE_WAP_PUSH,
                TransformUtils.RECEIVE_MMS};

        public static final String[] STORAGE = new String[]{
                TransformUtils.READ_EXTERNAL_STORAGE,
                TransformUtils.WRITE_EXTERNAL_STORAGE};
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, String... permissions) {
        return transformText(context, Arrays.asList(permissions));
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, String[]... groups) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : groups) {
            permissionList.addAll(Arrays.asList(group));
        }
        return transformText(context, permissionList);
    }

    /**
     * Turn permissions into text.
     */
    public static List<String> transformText(Context context, List<String> permissions) {
        List<String> textList = new ArrayList<>();
        for (String permission : permissions) {
            String message = transformText(context, permission);
            if (!textList.contains(message)) {
                textList.add(message);
            }
        }
        return textList;
    }

    /**
     * 获取权限名称
     *
     * @param context
     * @param permission
     * @return
     */
    public static String transformText(Context context, String permission) {
        String permissionName = "";
        switch (permission) {
            case READ_CALENDAR:
            case WRITE_CALENDAR: {
                permissionName = context.getString(R.string.permission_name_calendar);
                break;
            }

            case CAMERA: {
                permissionName = context.getString(R.string.permission_name_camera);
                break;
            }
            case READ_CONTACTS:
            case WRITE_CONTACTS:
            case GET_ACCOUNTS: {
                permissionName = context.getString(R.string.permission_name_contacts);
                break;
            }
            case ACCESS_FINE_LOCATION:
            case ACCESS_COARSE_LOCATION: {
                permissionName = context.getString(R.string.permission_name_location);
                break;
            }
            case RECORD_AUDIO: {
                permissionName = context.getString(R.string.permission_name_microphone);
                break;
            }
            case READ_PHONE_STATE:
            case CALL_PHONE:
            case READ_CALL_LOG:
            case WRITE_CALL_LOG:
            case USE_SIP:
            case PROCESS_OUTGOING_CALLS: {
                permissionName = context.getString(R.string.permission_name_phone);
                break;
            }
            case BODY_SENSORS: {
                permissionName = context.getString(R.string.permission_name_sensors);
                break;
            }
            case SEND_SMS:
            case RECEIVE_SMS:
            case READ_SMS:
            case RECEIVE_WAP_PUSH:
            case RECEIVE_MMS: {
                permissionName = context.getString(R.string.permission_name_sms);
                break;
            }
            case READ_EXTERNAL_STORAGE:
            case WRITE_EXTERNAL_STORAGE: {
                permissionName = context.getString(R.string.permission_name_storage);
                break;
            }
        }
        return permissionName;
    }
}
