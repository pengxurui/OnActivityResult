package com.xurui.onresult;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * proxy fragment
 * <p>
 * Created by pengxr on 2020/8/26.
 */
public class RequestFragment extends Fragment implements Request {

    private final ActivityFragmentLifecycle lifecycle = new ActivityFragmentLifecycle();

    private Set<ActivityIntentLauncher> pendingRequest = new HashSet<>();

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
        for (ActivityIntentLauncher launcher : pendingRequest) {
            start(launcher);
        }
        pendingRequest.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycle.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void start(@NonNull ActivityIntentLauncher launcher) {
        if (null != getActivity()) {
            // 已经 Attach
            int requestCode = lifecycle.addListener(launcher);
            startActivityForResult(launcher.createIntent(), requestCode);
        } else {
            pendingRequest.add(launcher);
        }
    }

    @Override
    public void remove(@NonNull ActivityIntentLauncher launcher) {
        pendingRequest.remove(launcher);
        lifecycle.removeListener(launcher);
    }
}
