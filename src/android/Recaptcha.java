package org.junker.recaptcha;

import java.util.concurrent.Executor;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.tasks.*;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;

/**
 * Cordova Recaptcha plugin to verify Google Recaptcha
 */
public class Recaptcha extends CordovaPlugin
{
    private RecaptchaTasksClient recaptchaTasksClient = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("initialize")) {
        	try {
                String key = args.getString(0);
                int timeout = args.getInt(1);
            	this.initClient(key, timeout, callbackContext);
        	} catch(JSONException e){
				callbackContext.error("Verify called without providing a Key");
        	}
            return true;
        }
        if (action.equals("execLogin")) {
            int timeout = args.getInt(0);
            this.execAction(RecaptchaAction.LOGIN, timeout, callbackContext);
            return true;
        }
        if (action.equals("execSignup")) {
            int timeout = args.getInt(0);
            this.execAction(RecaptchaAction.SIGNUP, timeout, callbackContext);
            return true;
        }
        if (action.equals("execCustom")) {
        	try {
                String reaction = args.getString(0);
                int timeout = args.getInt(1);
                this.execAction(RecaptchaAction.custom(reaction), timeout, callbackContext);
        	} catch(JSONException e){
				callbackContext.error("execCustom called without providing action name");
        	}
            return true;
        }
        return false;
    }

    private void initClient(String key, int timeout, CallbackContext callbackContext) {
        Recaptcha self = this;
        com.google.android.recaptcha.Recaptcha
           .getTasksClient(cordova.getActivity().getApplication(), key, timeout)
           .addOnSuccessListener(
                cordova.getActivity(),
                new OnSuccessListener<RecaptchaTasksClient>() {
                    @Override
                    public void onSuccess(RecaptchaTasksClient client) {
                        self.recaptchaTasksClient = client;
                        callbackContext.success();
                    }
                })
           .addOnFailureListener(
               cordova.getActivity(),
               new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        callbackContext.error(e.getMessage());
                    }
            });
    }

    private void execAction(RecaptchaAction action, int timeout, CallbackContext callbackContext) {
        this.recaptchaTasksClient
            .executeTask(action)
            .addOnSuccessListener(
                cordova.getActivity(),
                new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String token) {
                        callbackContext.success(token);
                    }
                })
            .addOnFailureListener(
                cordova.getActivity(),
                new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        callbackContext.error(e.getMessage());
                    }
                });
    }

}
