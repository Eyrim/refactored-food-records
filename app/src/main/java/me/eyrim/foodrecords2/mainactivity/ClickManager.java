package me.eyrim.foodrecords2.mainactivity;

import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

// The solution this class uses to track registered views is not ideal, I don't know a better solution curently
public class ClickManager {
    private static final ArrayList<View> views = new ArrayList<>();

    /**
     * Registers a view to manage the click events for<br/><br/>
     * Attempts to register an already managed view will be ignored
     * @param view The view being managed
     */
    public static void register(View view) {
        // Checks if the view is already being managed
        if (!views.contains(view)) {
            // Add the view if it's not already registered
            views.add(view);

            view.setOnClickListener(clickListener);
        }
    }

    /**
     * Unregisters a view<br/><br/>
     * Attempts to unregister an unmanaged view will be ignored
     * @param view The view to remove if present
     */
    public static void unregister(View view) {
        // Remove the view if it's being managed
        views.remove(view);
    }

    private static final View.OnClickListener clickListener = view -> {
        // If the view is registered
        if (views.contains(view)) {

        }
        /*
        Information to gather:
            - Recipe ID

        Swap to edit recipe activity with the recipe id attached

        Unregister on clicks
        */
    };

    /**
     * Unregisters every view
     */
    private static void unregisterAll() {
        views.clear();
    }
}
