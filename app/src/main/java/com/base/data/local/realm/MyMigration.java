package com.base.data.local.realm;

import com.utility.UtilsLib;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Phong on 7/11/2017.
 */

public class MyMigration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        // Access the Realm schema in order to create, modify or delete classes and their fields.
        RealmSchema schema = realm.getSchema();

        // Migrate to version 1: Add a new class.
        if (oldVersion == 0) {
            // Create a new class
            RealmObjectSchema realmObjectSchema = null;
            if (schema.get("LocalCity") == null) {
                realmObjectSchema = schema.create("LocalCity")
                        .addField("address_name", String.class, FieldAttribute.PRIMARY_KEY)
                        .addField("selected", boolean.class)
                        .transform(new RealmObjectSchema.Function() {
                            @Override
                            public void apply(DynamicRealmObject obj) {
                            }
                        });
            }

            // Create a new class
            if (!schema.get("AppSettings").hasField("isUsingGPS")) {
                schema.get("AppSettings")
                        .addField("isUsingGPS", boolean.class)
                        .transform(new RealmObjectSchema.Function() {
                            @Override
                            public void apply(DynamicRealmObject obj) {
                                obj.set("isUsingGPS", false);
                            }
                        });
            }
            oldVersion++;
        }

        /*
        * Migrate to version 2: Add a new field
        * New field: search_name
        */
        if (oldVersion == 1) {
            if (schema.get("LocalCity") != null && !schema.get("LocalCity").hasField("search_name")) {
                schema.get("LocalCity")
                        .addField("search_name", String.class)
                        .transform(new RealmObjectSchema.Function() {
                            @Override
                            public void apply(DynamicRealmObject obj) {
                                try {
                                    obj.set("search_name", UtilsLib.removeAccents(String.valueOf(obj.get("address_name"))));
                                } catch (Exception e) {
                                }
                            }
                        });
            }
            oldVersion++;
        }

        /*
        * Migrate to version 3: Update value for field
        * Update field: search_name
        */
        if (oldVersion == 2) {
            if (schema.get("LocalCity") != null && schema.get("LocalCity").hasField("search_name")) {
                schema.get("LocalCity")
                        .transform(new RealmObjectSchema.Function() {
                            @Override
                            public void apply(DynamicRealmObject obj) {
                                try {
                                    obj.set("search_name", UtilsLib.removeAccents(String.valueOf(obj.get("address_name"))));
                                } catch (Exception e) {
                                }
                            }
                        });
            }
            oldVersion++;
        }

    }
}
