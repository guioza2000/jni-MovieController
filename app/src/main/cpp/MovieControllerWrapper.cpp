//
// Created by Guillaume on 1/14/19.
//

#include <jni.h>
#include <android/log.h>
#include "MovieControllerWrapper.h"
#include "MovieController.h"
#include "handle.h"

/*
 * Class:     com_hub_gui_interview_model_Actor
 * Method:    getAge
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_hub_gui_interview_model_Actor_getAge(JNIEnv *env, jobject obj) {
    movies::Actor *p = getHandle<movies::Actor>(env, obj);
    if(p == 0) return 0;
    return p->age;
}

/*
 * Class:     com_hub_gui_interview_model_Actor
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_Actor_getName
        (JNIEnv *env, jobject obj) {
    movies::Actor *p = getHandle<movies::Actor>(env, obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->name.c_str());
}

/*
 * Class:     com_hub_gui_interview_model_Actor
 * Method:    getImageUrl
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_Actor_getImageUrl
        (JNIEnv *env, jobject obj) {
    movies::Actor *p = getHandle<movies::Actor>(env, obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->imageUrl.c_str());
}

/*
* Class:     com_hub_gui_interview_model_Movie
* Method:    getName
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_Movie_getName
        (JNIEnv *env, jobject obj) {
    movies::Movie *p = getHandle<movies::Movie>(env, obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->name.c_str());
}

/*
 * Class:     com_hub_gui_interview_model_Movie
 * Method:    getLastUpdate
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_hub_gui_interview_model_Movie_getLastUpdate
        (JNIEnv *env, jobject obj) {
    movies::Movie *p = getHandle<movies::Movie>(env, obj);
    return p->lastUpdated;
}


/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    createManager
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_hub_gui_interview_manager_MovieManager_jniCreateManager
        (JNIEnv *env, jclass type) {
    movies::MovieController *controller = new movies::MovieController();
    if(controller == 0) return 0;

    return (jlong) controller;
}

/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    delete
 */
void
Java_com_hub_gui_interview_manager_MovieManager_delete(JNIEnv *env, jobject obj)
{
    movies::MovieController *p = getHandle<movies::MovieController>(env, obj);
    setHandle<movies::MovieController>(env, obj, 0);
    delete p;
}

/*
* Class:     com_hub_gui_interview_manager_MovieManager
* Method:    getMovieList
* Signature: ()[J
*/
JNIEXPORT jlongArray JNICALL Java_com_hub_gui_interview_manager_MovieManager_jniGetMovieList
        (JNIEnv *env, jobject obj) {

    movies::MovieController *controller = getHandle<movies::MovieController>(env, obj);
    if(controller == 0) return 0;

    std::vector<movies::Movie*> movies = controller->getMovies();

    int size = movies.size();
    jlongArray result = env->NewLongArray(size);

    jlong arr [size];
    //convert int[] to jlong[]
    for(int i = 0; i< size; i++) {
        arr[i] = (jlong) movies[i];
    }

    env-> SetLongArrayRegion(result,0,size,&arr[0]);

    return result;
}

/*
 * Class:     com_hub_gui_interview_manager_MovieManager
 * Method:    getMovieDetails
 * Signature: (Ljava/lang/String;)J
 */

JNIEXPORT jlong JNICALL Java_com_hub_gui_interview_manager_MovieManager_jniGetMovieDetails
        (JNIEnv * env, jobject obj, jstring movie_name) {

    movies::MovieController *controller = getHandle<movies::MovieController>(env, obj);
    if(controller == 0) return 0;

    const char *str = env->GetStringUTFChars(movie_name,0);
    movies::MovieDetail *detail = controller->getMovieDetail(str);

    //__android_log_write(ANDROID_LOG_ERROR, "Tag", detail->description.c_str());

    return (jlong) detail;
}


/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_MovieDetail_getName
        (JNIEnv * env, jobject obj){
    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->name.c_str());
}

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getScore
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_hub_gui_interview_model_MovieDetail_getScore
        (JNIEnv * env, jobject obj){
    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;
    return (jfloat)p->score;
}

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getDescription
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_hub_gui_interview_model_MovieDetail_getDescription
        (JNIEnv * env, jobject obj){
    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->description.c_str());
}

/*
 * Class:     com_hub_gui_interview_model_MovieDetail
 * Method:    getActors
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_com_hub_gui_interview_model_MovieDetail_getActors
        (JNIEnv * env, jobject obj){

    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;

    std::vector<movies::Actor*> actors = p->actors;

    int size = actors.size();
    jlongArray result = env->NewLongArray(size);

    jlong arr [size];
    //convert int[] to jlong[]
    for(int i = 0; i< size; i++) {
        arr[i] = (jlong) actors[i];
    }

    env-> SetLongArrayRegion(result,0,size,&arr[0]);

    return result;

}


