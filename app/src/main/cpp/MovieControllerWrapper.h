//
// Created by Guillaume on 1/14/19.
//
#include <jni.h>

#ifndef HIGHRISE_MOVIECONTROLLERWRAPPER_H
#define HIGHRISE_MOVIECONTROLLERWRAPPER_H


#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_hub_gui_interview_model_Actor
 * Method:    getAge
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_hub_gui_interview_model_Actor_getAge
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_Actor
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_Actor_getName
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_Actor
 * Method:    getImageUrl
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_Actor_getImageUrl
        (JNIEnv *, jobject);

/*
* Class:     com_hub_gui_interview_model_Movie
* Method:    getName
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_Movie_getName
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_Movie
 * Method:    getLastUpdate
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_hub_gui_interview_model_Movie_getLastUpdate
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_MovieDetail_getName
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getScore
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_hub_gui_interview_model_MovieDetail_getScore
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getActors
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_com_hub_gui_interview_model_MovieDetail_getActors
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getDescription
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_MovieDetail_getDescription
        (JNIEnv *, jobject);



/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    jniCreateManager
 */
JNIEXPORT jlong JNICALL Java_com_hub_gui_interview_manager_MovieManager_jniCreateManager
        (JNIEnv *, jclass);

/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    delete
 */
void Java_com_hub_gui_interview_manager_MovieManager_delete(JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    getMovieList
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_com_hub_gui_interview_manager_MovieManager_jniGetMovieList
        (JNIEnv *, jobject);

/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    getMovieDetails
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_hub_gui_interview_manager_MovieManager_jniGetMovieDetails
        (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif

#endif //HIGHRISE_MOVIECONTROLLERWRAPPER_H
