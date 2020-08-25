package com.xurui.onresult;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengxr on 2020/8/25.
 */
public class RequestRetriever implements Handler.Callback {

    private static final String FRAGMENT_TAG = "com.xurui.onresult.RequestRetriever";

    private static final int ID_REMOVE_FRAGMENT = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT = 2;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private final Map<FragmentManager, SupportRequestFragment> pendingSupportRequestFragments = new HashMap<>();

    @NonNull
    SupportRequestFragment getRequestManagerFragment(@NonNull final FragmentManager fm) {
        SupportRequestFragment current = (SupportRequestFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingSupportRequestFragments.get(fm); // 双重检查，因为 commitAllowingStateLoss 可能在消息队列里
            if (current == null) {
                current = new SupportRequestFragment();
                pendingSupportRequestFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                mHandler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT, fm).sendToTarget();
            }
        }
        return current;
    }

    @Override
    public boolean handleMessage(Message msg) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (msg.what) {
            case ID_REMOVE_FRAGMENT:
//                android.app.FragmentManager fm = (android.app.FragmentManager) message.obj;
//                key = fm;
//                removed = pendingRequestManagerFragments.remove(fm);
                break;
            case ID_REMOVE_SUPPORT_FRAGMENT:
                FragmentManager supportFm = (FragmentManager) msg.obj;
                key = supportFm;
                removed = pendingSupportRequestFragments.remove(supportFm);
                break;
            default:
                handled = false;
                break;
        }
//        if (handled && removed == null && Log.isLoggable(TAG, Log.WARN)) {
//            Log.w(TAG, "Failed to remove expected request manager fragment, manager: " + key);
//        }
        return handled;
    }
}
