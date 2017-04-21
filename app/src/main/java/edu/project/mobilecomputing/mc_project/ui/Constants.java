/**
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.project.mobilecomputing.mc_project.ui;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Constants used in this sample.
 */
public final class Constants {

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> SUPERMARKETS = new HashMap<String, LatLng>();
    static {

        //TODO add the location of the place where we'll be recording the video
//        SUPERMARKETS.put("UPALMS", new LatLng(33.419310, -111.919532));
//        SUPERMARKETS.put("Walmart Supercenter, Elliot Rd", new LatLng(33.350258, -111.960815));
//        SUPERMARKETS.put("Walmart Express - Arizona State University", new LatLng(33.414428, -111.929201));
//        SUPERMARKETS.put("Walmart Supercenter, Southern", new LatLng(33.393357, -111.928466));
//        SUPERMARKETS.put("Noble library", new LatLng(33.420132, -111.930642));
        SUPERMARKETS.put("SF", new LatLng(37.621313, -122.378955));



    }
}
