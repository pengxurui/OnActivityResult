package com.xurui.onresult;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static android.app.Activity.RESULT_OK;

/**
 * Created by pengxr on 2020/8/25.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface OnActivityResult {

    int requestCode();

    int resultCode() default RESULT_OK;
}
