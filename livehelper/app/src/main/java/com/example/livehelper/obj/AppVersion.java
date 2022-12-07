package com.zrkaxt.testmachine.obj;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class AppVersion {
    String version,name,app;

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getApp() {
        return app;
    }
    public AppVersion(JSONObject json){
        try {
            this.version=json.getString("version");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.name=json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.app=json.getString("app");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean CompareVersion(String myversion, String remoteversion) {
        Log.d("versioncompare", myversion + "=" + remoteversion);
        String[] v1 = myversion.split("\\.");
        String[] v2 = remoteversion.split("\\.");
        try {
            for (int i = 0; i < 3; i++) {
                int vc1 = Integer.parseInt(v1[i]);
                int vc2 = Integer.parseInt(v2[i]);
                Log.d("versioncompare", String.valueOf(vc2) + ">" + String.valueOf(vc1));
                if (vc2 > vc1) {
                    return true;
                }
            }
        } catch (Exception ex) {

        }
        return false;
    }

}
