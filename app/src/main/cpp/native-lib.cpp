#include <jni.h>
#include <string>
#include "opencv2/opencv.hpp"
#include <opencv2/core/core.hpp>

using namespace std;
using namespace cv;

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_wyr_cbimh_Video_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C" JNIEXPORT jintArray

JNICALL
Java_com_example_wyr_cbimh_Video_grayProc(JNIEnv *env, jobject instance,
                                                    jintArray buf, jint w, jint h) {

    jint *cbuf;
    jboolean ptfalse = false;
    cbuf = env->GetIntArrayElements(buf, &ptfalse);
    if(cbuf == NULL){
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char*)cbuf);

    uchar* ptr = imgData.ptr(0);
    for(int i = 0; i < w*h; i ++){
        uchar grayScale = (uchar)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }

    int size=w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;
}

extern "C" JNIEXPORT jintArray

JNICALL
Java_com_example_wyr_cbimh_Video_grayProd(JNIEnv *env, jobject instance,
                                               jintArray buf, jint w, jint h) {

    jint *cbuf;
    jboolean ptfalse = false;
    cbuf = env->GetIntArrayElements(buf, &ptfalse);
    if(cbuf == NULL){
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char*)cbuf);

    uchar* ptr = imgData.ptr(0);
    for(int i = 0; i < w*h; i ++){
        uchar grayScale = (uchar)(ptr[4*i+2]*0.299 + ptr[4*i+1]*0.587 + ptr[4*i+0]*0.114);
        ptr[4*i+1] = grayScale;
        ptr[4*i+2] = grayScale;
        ptr[4*i+0] = grayScale;
    }

    int size=w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;
}


extern "C" JNIEXPORT jintArray

JNICALL
Java_com_example_wyr_cbimh_Video_faceDetect(JNIEnv *env, jobject instance,
                                               jintArray buf, jint w, jint h) {

    jint *cbuf;
    jboolean ptfalse = false;
    cbuf = env->GetIntArrayElements(buf, &ptfalse);
    if(cbuf == NULL){
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char*)cbuf);
    Mat gray;
    CascadeClassifier cascade;
    if(!cascade.load("/sdcard/lbpcascade_frontalface.xml")){
        return 0;
    }
    //cascade.load("/Users/wyr/Downloads/OpenCV-android-sdk/sdk/etc/lbpcascades/lbpcascade_frontalface.xml");
    vector<Rect> faces;

    Scalar colors[] = {CV_RGB(255, 0, 0),
                       CV_RGB(255, 97, 0),
                       CV_RGB(255, 255, 0),
                       CV_RGB(0, 255, 0),
                       CV_RGB(0, 255, 255),
                       CV_RGB(0, 0, 255),
                       CV_RGB(160, 32, 240)};

    cvtColor(imgData,gray,CV_BGR2GRAY);
    equalizeHist(gray, gray);
    cascade.detectMultiScale(gray,faces,1.1,3,0);
    printf("faces:%d\n",faces.size());

    for(int i = 0; i < faces.size();i++){
        Point center;
        int radius;
        center.x = cvRound((faces[i].x + faces[i].width * 0.5));
        center.y = cvRound((faces[i].y + faces[i].height * 0.5));

        radius = cvRound((faces[i].width + faces[i].height) * 0.25);
        circle(imgData, center, radius, colors[i % 7], 2);
    }

    int size=w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, cbuf);
    env->ReleaseIntArrayElements(buf, cbuf, 0);
    return result;

}
