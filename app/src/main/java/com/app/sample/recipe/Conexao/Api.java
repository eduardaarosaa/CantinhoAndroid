/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/10 at 8:43:17 for quantic heart studios
 *
 */

package com.app.sample.recipe.Conexao;

import com.app.sample.recipe.Conexao.Models.Publicacoes;
import com.app.sample.recipe.Conexao.Models.RetornoWs;
import com.app.sample.recipe.Conexao.Models.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    //Login, Recover Password and Sing In

    @GET("Publicacoes")
    Call<RetornoWs> RetornaPublic();

    @POST("criar_usuarios")
    Call<RetornoWs> Criar_usuarios(@Body Usuario pessoa);

    @GET("login_comum")
    Call<RetornoWs> RetornarLogin(@Query("email") String email, @Query("senha") String senha);

    @POST("editar_usuario")
    Call<RetornoWs> editar_usuario(@Body Usuario pessoa);

    @POST("criar_publicacoes")
    Call<RetornoWs> add_publicacao(@Body Publicacoes publicacoes);

    @GET("exibir_comentarios")
    Call<RetornoWs> getComentarios();
//    @POST("login_usuario")
//    Call<WSResponse> setLogin(@Header("Authorization") String authKey, @Body Login login);
//
//    @POST("login_usuario")
//    Call<WSResponse> setLoginSocialNetwork(@Body Login login);
//
//    @POST("ativar_sms")
//    Call<WSResponse> smsActivate(@Body Login login);
//
////    @GET(newWs + "wsMagazines?limit=10")
////    Call<WSResponse> getMagazines(@Query("page") int wsPage);
////
////    //
////    @POST(newWs + "wsCreateUser")
////    Call<WSResponse> createNewUser(@Body Usuario usuario);
////
////    @POST(newWs + "wsForgotPassword")
////    Call<WSResponse> setRecoverPassword(@Body Usuario usuario);
//
//    //Inside in app!
//
//
//    //==============================================================================================
//    //
//    // News Connections
//    //
//    //==============================================================================================

}

