///*
// *
// *  *                                     /@
// *  *                      __        __   /\/
// *  *                     /==\      /  \_/\/
// *  *                   /======\    \/\__ \__
// *  *                 /==/\  /\==\    /\_|__ \
// *  *              /==/    ||    \=\ / / / /_/
// *  *            /=/    /\ || /\   \=\/ /
// *  *         /===/   /   \||/   \   \===\
// *  *       /===/   /_________________ \===\
// *  *    /====/   / |                /  \====\
// *  *  /====/   /   |  _________    /      \===\
// *  *  /==/   /     | /   /  \ / / /         /===/
// *  * |===| /       |/   /____/ / /         /===/
// *  *  \==\             /\   / / /          /===/
// *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
// *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
// *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
// *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
// *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
// *  *        \==\  / \ / / ///          /===/
// *  *        \==\ /   / / /________/    /==/
// *  *          \==\  /               | /==/
// *  *          \=\  /________________|/=/
// *  *            \==\     _____     /==/
// *  *           / \===\   \   /   /===/
// *  *          / / /\===\  \_/  /===/
// *  *         / / /   \====\ /====/
// *  *        / / /      \===|===/
// *  *        |/_/         \===/
// *  *                       =
// *  *
// *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/10 at 8:43:17 for quantic heart studios
// *
// */
//
//package com.app.sample.recipe.Conexao;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.support.annotation.NonNull;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Objects;
//
//import maripoppis.com.connection.ConnectionUtils.ConnectUtils;
//import maripoppis.com.connection.ConnectionUtils.LoadingUtils;
//import maripoppis.com.connection.ConnectionUtils.LoginUtils;
//import maripoppis.com.connection.Model.WSResponse;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import static maripoppis.com.connection.ConnectionUtils.ConnectUtils.logFunction;
//
//public class Connect {
//
//    //Log Help
//    private String connectDescription = "";
//    private static ConnectCallback callback;
//
//    //Contructor
//    @SuppressLint("StaticFieldLeak")
//    private static Activity activity;
//    private static ConnectionType connectionTypeID;
//    private static WSResponse.dados dados = null;
//
//    //Retrofit
//    private Api apiInterface;
//    private Call<WSResponse> call = null;
//
//    /**
//     * @param mActivity call WS
//     */
//    public Connect(Activity mActivity) {
//        activity = mActivity;
//        apiInterface = RestClient.getClient(activity).create(Api.class);
//    }
//
//    public enum ConnectionType {
//        WS_LOGIN,
//        WS_SMS_ACTIVATE,
//        WS_SINGIN_USER,
//        WS_RECOVERPASSWORD
//    }
//
//    /**
//     * @param connectionTypeID connection enum for
//     * @param dados            for construct
//     * @param showLoading      show Load?
//     */
//    public void getDataFrom(ConnectionType connectionTypeID, WSResponse.dados dados, Boolean showLoading) {
//        Connect.dados = null;
//        Connect.dados = dados;
//        if (showLoading) {
////            ConnectUtils.showSimpleLoadingDialog(activity);
//        }
//        initWsConection(connectionTypeID);
//    }
//
//    /**
//     * @param connectionTypeID      connection enum for
//     * @param dados_Para_Parametros
//     * @param showLoading           show Load?
//     */
//    public void getDataFrom(ConnectionType connectionTypeID, Map<String, String> dados_Para_Parametros, Boolean showLoading) {
//        if (showLoading) {
////            ConnectUtils.showSimpleLoadingDialog(activity);
//
//        }
//        initWsConection(connectionTypeID);
//    }
//
//    private void initWsConection(ConnectionType ID) {
//
//        connectionTypeID = ID;
//        LoadingUtils.showLoading(activity);
//
//        switch (ID) {
//            case WS_LOGIN:
//
//                if (dados.getLogin().getFacebook_usuario() == null) {
//                    call = apiInterface.setLogin(LoginUtils.getAuthorizationHeader(dados.getLogin().getUser(), dados.getLogin().getPass()), dados.getLogin());
//                } else {
//                    call = apiInterface.setLoginSocialNetwork(dados.getLogin());
//                }
//                break;
//            case WS_SMS_ACTIVATE:
//                call = apiInterface.smsActivate(dados.getLogin());
//                break;
//
//
////            case WS_SINGIN_USER:
////                connectDescription = "Register Called";
////                call = apiInterface.createNewUser(dados.getUsuario());
////
////                break;
////
////            case WS_RECOVERPASSWORD:
////                connectDescription = "RecoverPass Called";
////                call = apiInterface.setRecoverPassword(dados.getUsuario());
////                break;
//
//        }
//
//        call.enqueue(new Callback<WSResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<WSResponse> call, @NonNull Response<WSResponse> response) {
////                logFunction(response, connectDescription);
//                //
//                if (response.isSuccessful()) {// Return in 200 at 300 http status
//                    int status = verifieStatus(response);
//                    if (status == STATUS_OK) {
//                        callback.ConnectionSuccess(response.body(), connectionTypeID);
//                        LoadingUtils.closeLoading();
//                    }
//                } else if (response.code() >= 400) {
//                    WSResponse wsResponse = getErrorBody(Objects.requireNonNull(response.errorBody()));
//                    ConnectUtils.logW("Status", wsResponse.getResultado());
//                    callback.ConnectionError(STATUS_FAIL, wsResponse, connectionTypeID);
//                    LoadingUtils.closeLoading(activity, wsResponse, ConnectionType.WS_LOGIN);
//                }
//                // ConnectUtils.hideSimpleLoadingDialog();
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<WSResponse> call, @NonNull Throwable t) {
//                logFunction(t, connectDescription);
//                callback.ConnectionFail(t);
////                ConnectUtils.hideSimpleLoadingDialog();
//            }
//        });
//
//    }
//
//    private int verifieStatus(Response<WSResponse> response) {
//        int statusWS = Objects.requireNonNull(response.body()).getStatus();
//
//        switch (statusWS) {
//            case 0:
//                if (connectionTypeID == ConnectionType.WS_LOGIN && dados.getLogin().getFacebook_usuario() != null) {
//                    callback.ConnectionError(STATUS_NEW_FACEBOOK_USER, response.body(), connectionTypeID);
//                    LoadingUtils.closeLoading();
//                }
//                break;
//            case -3:
//                LoadingUtils.closeLoadingByStatus(activity, response.body().getResultado(), statusWS);
//                callback.ConnectionError(STATUS_SMS_ACTIVATION, response.body(), connectionTypeID);
//                break;
//            default:
//                LoadingUtils.closeLoading(activity, response.body(), connectionTypeID);
//                callback.ConnectionError(STATUS_FAIL, response.body(), connectionTypeID);
//                break;
//        }
//        return statusWS;
//    }
//
//
//    //FavoriteUtils
//    // =============================================================================================
//
//    /**
//     * @param responseBody error body for Gson
//     * @return WSResponse
//     */
//    private WSResponse getErrorBody(ResponseBody responseBody) {
//        Gson gson = new GsonBuilder().create();
//        try {
//            return gson.fromJson(responseBody.string(), WSResponse.class);
//        } catch (IOException e) {
//            return new WSResponse();
//        }
//    }
//
//    //Interface
//    // =============================================================================================
//
//    //Status Return WS
//    public static int STATUS_FAIL = 0;
//    public static int STATUS_OK = 1;
//
//    //Others Status
//    public static int STATUS_SMS_ACTIVATION = -3;
//    public static int STATUS_NEW_FACEBOOK_USER = -10;
//
//    public static void setCallback(ConnectCallback mCallback) {
//        callback = mCallback;
//    }
//
//    public interface ConnectCallback {
//
//        void ConnectionSuccess(WSResponse response, ConnectionType connectionTypeID);
//
//        void ConnectionError(int statusType, WSResponse response, ConnectionType connectionTypeID);
//
//        void ConnectionFail(Throwable t);
//
//    }
//
//}
