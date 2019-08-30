package com.wonly.lib_base.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

import com.wonly.lib_base.BuildConfig;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

/**
 * @Author: HSL
 * @Time: 2019/8/29 16:29
 * @E-mail: xxx@163.com
 * @Description: 传感器工具类~
 */
public class SensorUtils {
    /**
     * 定义sensor管理器
     */
    private SensorManager mSensorManager;
    private Vibrator vibrator;

    /**
     * 初始化传感器
     *
     * @param context
     */
    public void initSensor(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
    }

    /**
     * 注册传感器
     */
    public void registerSensor() {
        //加速度传感器 注册监听
        //还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
        //根据不同应用，需要的反应速率不同，具体根据实际情况设定
        if (mSensorManager == null || mSensorEventListener == null) return;
        mSensorManager.registerListener(mSensorEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unRegisterSensor() {
        //取消注册传感器监听
        if (mSensorManager == null || mSensorEventListener == null) return;
        mSensorManager.unregisterListener(mSensorEventListener);
    }

    /**
     * 摇动回调
     */
    protected void onSensorChangedResult() {

    }

    /**
     * 摇一摇传感器
     */
    private SensorEventListener mSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int sensorType = event.sensor.getType();
            //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
            float[] values = event.values;
            if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                /*
                 * 因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
                 * 的时候，瞬时加速度才会突然增大或减少，所以，经过实际测试，只需监听任一轴的
                 * 加速度大于14的时候，改变你需要的设置就OK了
                 */
                if ((Math.abs(values[0]) > 19 || Math.abs(values[1]) > 19 || Math.abs(values[2]) > 19)) {
                    // TODO: 2019/8/29 防抖处理
                    // TODO: 2019/8/29 防抖处理
                    // TODO: 2019/8/29 防抖处理
                    // TODO: 2019/8/29 防抖处理
                    if (BuildConfig.DEBUG) {
                        //震动
                        vibrator.vibrate(500);
                        //回调
                        onSensorChangedResult();
                    }
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //当传感器精度改变时回调该方法
        }
    };
}
