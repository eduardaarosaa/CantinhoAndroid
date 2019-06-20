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
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/9 at 3:18:41 for quantic heart studios
 *
 */

package com.app.sample.recipe.Utils;

import android.app.Activity;
import android.content.Intent;


/**
 * Created by John on 29/01/2018.
 */

public class ActivityUtil {

    /**
     * @param activity
     * @param intent
     * @param finish
     */
    public static void callActivity(Activity activity, final Intent intent, final Boolean finish) {
        initIntent(activity, verifieStatusIntent(intent), finish);
    }


    /**
     *
     * @param activity
     * @param intentClass
     * @param finish
     */
    public static void callActivity(Activity activity, final Class intentClass, final Boolean finish) {
        initIntent(activity, verifieStatusIntent(new Intent(activity, intentClass)), finish);
    }


    /**
     * @param intent
     * @return
     */
    private static Intent verifieStatusIntent(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    /**
     * @param activity
     * @param intent
     * @param finish
     */
    private static void initIntent(Activity activity, Intent intent, boolean finish) {
        activity.startActivity(intent);
        if (finish) {
            activity.finish();
        }
    }


}
