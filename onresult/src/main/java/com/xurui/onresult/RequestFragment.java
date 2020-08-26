package com.xurui.onresult;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;

/**
 * 空白Fragment
 * <p>
 * Created by pengxr on 2020/8/26.
 */
public class RequestFragment extends Fragment {

    private final ActivityFragmentLifecycle lifecycle = new ActivityFragmentLifecycle();

    private SparseArray<Intent> pendingRequest = new SparseArray<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (0 != pendingRequest.size()) {
            for (int index = 0; index < pendingRequest.size(); index++) {
                int requestCode = pendingRequest.keyAt(index);
                Intent intent = pendingRequest.valueAt(index);
                startActivityForResult(intent, requestCode);
            }
            pendingRequest.clear();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycle.onActivityResult(requestCode, resultCode, data);
    }

    public void start(Intent intent, int requestCode, @NonNull OnResultListener listener) {
        if (null != getActivity()) {
            // 已经 Attach
            startActivityForResult(intent, requestCode);
        } else {
            pendingRequest.put(requestCode, intent);
        }
        lifecycle.addListener(requestCode, listener);
    }

    public void remove(int requestCode, @NonNull OnResultListener listener) {
        pendingRequest.remove(requestCode);
        lifecycle.removeListener(requestCode, listener);
    }

}
