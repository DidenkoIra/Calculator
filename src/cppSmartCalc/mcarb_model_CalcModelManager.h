/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class mcarb_model_CalcModelManager */

#ifndef _Included_mcarb_model_CalcModelManager
#define _Included_mcarb_model_CalcModelManager
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     mcarb_model_CalcModelManager
 * Method:    calcResult
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_mcarb_model_CalcModelManager_calcResult
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     mcarb_model_CalcModelManager
 * Method:    calcGraphData
 * Signature: (Ljava/lang/String;DD)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_mcarb_model_CalcModelManager_calcGraphData
  (JNIEnv *, jobject, jstring, jdouble, jdouble);

#ifdef __cplusplus
}
#endif
#endif