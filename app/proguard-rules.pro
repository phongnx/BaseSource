# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Works\SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Keep Google services
-keep class com.google.android.gms.** {*;}
-keep class com.google.** {*;}
-keep class com.google.android.** {*;}
-keep class com.google.gson.** {*;}
-keep class com.android.** {*;}
-keep class android.support.design.** {*;}
-keep class android.support.v4.** {*;}
-keep class android.support.v7.** {*;}

# Libraries
-keep class okhttp3.** {*;}
-keep class butterknife.** {*;}
-keep class com.google.zxing.** {*;}
-keep class io.reactivex.rxjava2.** {*;}
-keep class io.reactivex.** {*;}
-keep class io.realm.** {*;}
-keep class com.intuit.sdp.** {*;}
-keep class jp.wasabeef.** {*;}
-keep class java.nio.** {*;}
-keep class org.joda.** {*;}
-keep class org.codehaus.** {*;}
-keep class java.lang.invoke.** {*;}
-keep class sun.misc.** {*;}
-keep class com.utility.** {*;}

# Glide
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl

# Retrofit
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# Application
-keep class com.base.data.models.** {*;}

# dontwarn
-dontwarn com.squareup.okhttp.**
-dontwarn java.nio.**
-dontwarn org.joda.**
-dontwarn org.codehaus.**
-dontwarn java.lang.invoke.**
-dontwarn sun.misc.**
-dontwarn net.fortuna.**
-dontwarn groovy.lang.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn com.utility.**

-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
#-keepattributes SourceFile,LineNumberTable
