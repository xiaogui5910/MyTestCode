//// Created by XY on 16/1/4.//#include "com_wobiancao_ndkjnidemo_ndk_JniUtils.h"/*
/* Class:     Java_com_wobiancao_ndkjnidemo_ndk_JniUtils
* Method:    getStringFormC
* Signature: ()Ljava/lang/String;
*/
#include "com_example_lenovo_mytestcode_utils_NdkTestUtils.h"
#include <string.h>
JNIEXPORT jstring JNICALL Java_com_example_lenovo_mytestcode_utils_NdkTestUtils_getStringFromC
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "这里是来自c的string");
}
