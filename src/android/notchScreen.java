package org.apache.cordova.notchScreen;

import android.widget.Toast;
import android.util.Log;
import android.widget.Toast;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONException;
 
import java.net.URISyntaxException;

import android.app.Activity;

 
/**
 * notchScreen
 * 
 */
public class notchScreen extends CordovaPlugin {
	 
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        //  判断是否有刘海屏
		if ("isNotchScreen".equals(action)){
            if (getInt("ro.miui.notch",cordova.getActivity()) == 1 || hasNotchAtHuawei(cordova.getActivity()) || hasNotchAtOPPO()
                    || hasNotchAtVivo(cordova.getActivity())  ){ //TODO 各种品牌
                callbackContext.success("true");
                return true;
            } 
            callbackContext.success("false");
            return false;
  
		}
		// 获取刘海屏的参数
		else if ("getNotchParams".equals(action)){
            
            return true;
        }
        return false;
    }


 
    // 是否是小米手机
    public static boolean isXiaomi() {
      return "Xiaomi".equals(Build.MANUFACTURER);
    }


    /**
    * 小米刘海屏判断.
    * @return 0 if it is not notch ; return 1 means notch
    * @throws IllegalArgumentException if the key exceeds 32 characters
    */
    public static int getInt(String key,Activity activity) {
        int result = 0;
        if (isXiaomi()){
        try {
            ClassLoader classLoader = activity.getClassLoader();
            @SuppressWarnings("rawtypes")
            Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
            //参数类型
            @SuppressWarnings("rawtypes")
            Class[] paramTypes = new Class[2];
            paramTypes[0] = String.class;
            paramTypes[1] = int.class;
            Method getInt = SystemProperties.getMethod("getInt", paramTypes);
            //参数
            Object[] params = new Object[2];
            params[0] = new String(key);
            params[1] = new Integer(0);
            result = (Integer) getInt.invoke(SystemProperties, params);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        }
        return result;
    }

    /**
    * 华为刘海屏判断
    * @return
    */
    public static boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
        ClassLoader classLoader = context.getClassLoader();
        Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
        Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
        ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
        Log.e(null,"hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
        Log.e(null,"hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
        Log.e( null,"hasNotchAtHuawei Exception");
        } finally {
        return ret;
        }
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
    * VIVO刘海屏判断
    * @return
    */
    public static boolean hasNotchAtVivo(Context context) {
        boolean ret = false;
        try {
        ClassLoader classLoader = context.getClassLoader();
        Class FtFeature = classLoader.loadClass("android.util.FtFeature");
        Method method = FtFeature.getMethod("isFeatureSupport", int.class);
        ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
        Log.e(null,"hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
        Log.e(null,  "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
        Log.e(null,  "hasNotchAtVivo Exception");
        } finally {
        return ret;
        }
    }
    /**
    * OPPO刘海屏判断
    * @return
    */
    public static boolean hasNotchAtOPPO(Context context) {
        return  context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }
    
        
 

}
