package com.xurui.onresult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengxr on 2020/8/25.
 */
public class SupportRequestFragment extends Fragment {

    private final ActivityFragmentLifecycle lifecycle = new ActivityFragmentLifecycle();

    private Map<Integer, Intent> pendingRequest = new HashMap<>();

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
        if (!pendingRequest.isEmpty()) {
            for (Map.Entry<Integer, Intent> entry : pendingRequest.entrySet()) {
                startActivityForResult(entry.getValue(), entry.getKey());
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
