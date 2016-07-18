package com.ccdev.quality.Utils;

import android.content.Context;

import com.ccdev.quality.Ignore.AuthSettings;
import com.securepreferences.SecurePreferences;

/**
 * Created by Coleby on 6/30/2016.
 */

public class Prefs {

    private static final String FILE_NAME = "quality_prefs";
    private static final String SERVER = "server";
    private static final String ROOT = "root";
    private static final String DOMAIN = "domain";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String REMEMBER_ME = "remember_me";
    private static final String USE_ADMIN = "use_admin";
    private static final String ADMIN = "admin";

    public static final int SETTINGS_OK = 0;
    public static final int MISSING_DOMAIN = -1;
    public static final int MISSING_SERVER = -2;
    public static final int MISSING_ROOT = -3;
    public static final int MISSING_USERNAME = -4;
    public static final int MISSING_PASSWORD = -5;

    private static SecurePreferences mSecurePreferences;
    private static String mServer;
    private static String mRoot;
    private static String mDomain;
    private static boolean mRememberMe;
    private static String mUsername;
    private static String mPassword;
    private static boolean mUseAdmin;
    private static String mAdmin;

    public static int getPrefs(Context context) {
        mSecurePreferences = new SecurePreferences(context, AuthSettings.SECRET_KEY, FILE_NAME);
        mServer = mSecurePreferences.getString(SERVER, "");
        mRoot = mSecurePreferences.getString(ROOT, "");
        mDomain = mSecurePreferences.getString(DOMAIN, "");
        mRememberMe = mSecurePreferences.getBoolean(REMEMBER_ME, false);
        mUsername = mSecurePreferences.getString(USERNAME, "");
        mPassword = mSecurePreferences.getString(PASSWORD, "");
        mUseAdmin = mSecurePreferences.getBoolean(USE_ADMIN, false);
        mAdmin = mSecurePreferences.getString(ADMIN, "");

        return checkNetworkSettings();
    }

    public static int checkNetworkSettings() {

        if (mServer == null || mServer.isEmpty()) {
            return MISSING_SERVER;
        }

        if (mRoot == null || mRoot.isEmpty()) {
            return MISSING_ROOT;
        }

        if (mDomain == null || mDomain.isEmpty()) {
            return MISSING_DOMAIN;
        }

        if (mUsername == null || mUsername.isEmpty()) {
            return MISSING_USERNAME;
        }

        if (mPassword == null || mPassword.isEmpty()) {
            return MISSING_PASSWORD;
        }

        return SETTINGS_OK;
    }

    public static void resetAll() {
        mServer = mSecurePreferences.getString(SERVER, "");
        mRoot = mSecurePreferences.getString(ROOT, "");
        mDomain = mSecurePreferences.getString(DOMAIN, "");
        mRememberMe = mSecurePreferences.getBoolean(REMEMBER_ME, false);
        mUsername = mSecurePreferences.getString(USERNAME, "");
        mPassword = mSecurePreferences.getString(PASSWORD, "");
        mUseAdmin = mSecurePreferences.getBoolean(USE_ADMIN, false);
        mAdmin = mSecurePreferences.getString(ADMIN, "");
    }

    public static String resetServer() {
        mServer = mSecurePreferences.getString(SERVER, "");
        return mServer;
    }

    public static String resetRoot() {
        mRoot = mSecurePreferences.getString(ROOT, "");
        return mRoot;
    }

    public static String restDomain() {
        mDomain = mSecurePreferences.getString(DOMAIN, "");
        return mDomain;
    }

    public static boolean resetRememberMe() {
        mRememberMe = mSecurePreferences.getBoolean(REMEMBER_ME, false);
        return mRememberMe;
    }

    public static String resetUsername() {
        mUsername = mSecurePreferences.getString(USERNAME, "");
        return mUsername;
    }

    public static String resetPassword() {
        mPassword = mSecurePreferences.getString(PASSWORD, "");
        return mPassword;
    }

    public static boolean resetUseAdmin() {
        mUseAdmin = mSecurePreferences.getBoolean(USE_ADMIN, false);
        return mUseAdmin;
    }

    public static String resetAdmin() {
        mAdmin = mSecurePreferences.getString(ADMIN, "");
        return mAdmin;
    }

    public static void commitAll() {
        commitServer();
        commitRoot();
        commitDomain();
        commitRememberMe();
        commitUsername();
        commitPassword();
        commitUseAdmin();
        commitAdmin();
    }

    public static void commitServer() {
        mSecurePreferences.edit().putString(SERVER, mServer).apply();
    }

    public static void commitRoot() {
        mSecurePreferences.edit().putString(ROOT, mRoot).apply();
    }

    public static void commitDomain() {
        mSecurePreferences.edit().putString(DOMAIN, mDomain).apply();
    }

    public static void commitUsername() {
        mSecurePreferences.edit().putString(USERNAME, mUsername).apply();
    }

    public static void commitPassword() {
        mSecurePreferences.edit().putString(PASSWORD, mPassword).apply();
    }

    public static void commitRememberMe() {
        mSecurePreferences.edit().putBoolean(REMEMBER_ME, mRememberMe).apply();
    }

    public static void commitUseAdmin() {
        mSecurePreferences.edit().putBoolean(USE_ADMIN, mUseAdmin).apply();
    }

    public static void commitAdmin() {
        mSecurePreferences.edit().putString(ADMIN, mAdmin).apply();
    }

    public static String getAuthString() {
        // TODO check for null
        return String.format("smb://%s;%s:%s@%s/",mDomain, mUsername, mPassword, mServer);
    }

    public static String getServer() {
        return mServer;
    }

    public static String getRoot() {
        return mRoot;
    }

    public static String getDomain() {
        return mDomain;
    }

    public static boolean getRememberMe() {
        return mRememberMe;
    }

    public static String getUsername() {
        return mUsername;
    }

    public static String getPassword() {
        return mPassword;
    }

    public static boolean getUseAdmin() {
        return mUseAdmin;
    }

    public static String getAdmin() { return mAdmin; }

    public static void setServer(String value) {
        mServer = value;
    }

    public static void setRoot(String value) {
        mRoot = value;
    }

    public static void setDomain(String value) {
        mDomain = value;
    }

    public static void setRememberMe(boolean value) {
        mRememberMe = value;
    }

    public static void setUsername(String value) {
        mUsername = value;
    }

    public static void setPassword(String value) {
        mPassword = value;
    }

    public static void setUseAdmin(boolean value) { mUseAdmin = value; }

    public static void setAdmin(String value) { mAdmin = value; }

}
