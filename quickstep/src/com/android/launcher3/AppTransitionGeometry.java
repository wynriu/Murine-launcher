package com.android.launcher3;

import android.graphics.RectF;

/**
 * Shared geometry and timing primitives for launcher app transitions.
 *
 * <p>The transition itself is still driven by Quickstep's remote animation runner. This class only
 * centralizes the icon-to-window and window-to-icon rules so open and close use the same contract.
 */
public final class AppTransitionGeometry {

    /** Fast touch response followed by a fluid expansion. */
    public static final long OPEN_DURATION_MS = 320L;

    /** Shorter return motion, with time reserved for the Home surface to become visible first. */
    public static final long CLOSE_DURATION_MS = 280L;

    /** Small tactile press scale; deliberately avoids a strong bounce. */
    public static final float TOUCH_RESPONSE_SCALE = 0.95f;

    private AppTransitionGeometry() {}

    /**
     * Interpolates a rectangle without allocating a new object. The caller owns {@code out}.
     */
    public static void lerpRect(RectF start, RectF end, float progress, RectF out) {
        out.set(
                lerp(start.left, end.left, progress),
                lerp(start.top, end.top, progress),
                lerp(start.right, end.right, progress),
                lerp(start.bottom, end.bottom, progress));
    }

    public static float lerp(float start, float end, float progress) {
        return start + (end - start) * progress;
    }

    /** Corner-radius morph shared by both directions. */
    public static float morphCornerRadius(float iconRadius, float windowRadius, float progress) {
        return lerp(iconRadius, windowRadius, progress);
    }
}
