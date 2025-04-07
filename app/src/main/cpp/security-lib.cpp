#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_dmos_example_biometricclient_nativ_SecurityNative_encryptData(JNIEnv *env, jobject thiz,
                                                                   jstring data) {
    std::string dummyEncrypted = "encrypted_dummy";
    return env->NewStringUTF(dummyEncrypted.c_str());
}