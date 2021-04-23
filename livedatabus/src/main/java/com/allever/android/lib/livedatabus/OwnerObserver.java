package com.allever.android.lib.livedatabus;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/**
 * @author allever
 */
public class OwnerObserver {

    private LifecycleOwner lifecycleOwner;
    private Observer observer;

    public OwnerObserver(LifecycleOwner lifecycleOwner, Observer observer) {
        this.lifecycleOwner = lifecycleOwner;
        this.observer = observer;
    }

    public LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public Observer getObserver() {
        return observer;
    }
}
