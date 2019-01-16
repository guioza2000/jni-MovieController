#include <jni.h>
#include <string>
#include <android/log.h>
#include "MovieController.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_hub_gui_interview_activity_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}




