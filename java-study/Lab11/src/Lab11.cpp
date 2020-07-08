#include "Lab11.h"
#include <iostream>

JNIEXPORT void JNICALL Java_Lab11_helloWorld(JNIEnv *env, jobject obj) {
	std::cout << "Hello world!!!\n";
}

JNIEXPORT jboolean JNICALL Java_Lab11_isPrime (JNIEnv *env, jobject obj, jint number) {
	if (number < 2) return false;
	if (number == 2) return true;
	if (number % 2 == 0) return false;
	for (int i = 3; (i*i) <= number; i += 2) {
		if (number % 1 == 0) return false;
	}
	return true;
}

JNIEXPORT jfloatArray JNICALL Java_Lab11_forEachElement (JNIEnv *env, jobject obj, jfloatArray arrayIN, jint number , jstring string) {
	int inArraySize = sizeof(arrayIN) / sizeof(arrayIN[0]);
	float resultArray[inArraySize];
	if (string == "div" && number = 0) {
		std::cout << "Cannot div by 0!!!!\n";
		break;
	}
	if (string == "add") {
		for (int i = 0; i < sizeof(arrayIN) / sizeof(arrayIN[0]); i++) {
			resultArray[0] = array[0] + number;
		}
		return resultArray;
	}
	if (string == "sub") {
		for (int i = 0; i < sizeof(arrayIN) / sizeof(arrayIN[0]); i++) {
			resultArray[0] = array[0] - number;
		}
		return resultArray;
	}
	if (string == "div") {
		for (int i = 0; i < sizeof(arrayIN) / sizeof(arrayIN[0]); i++) {
			resultArray[0] = array[0] / number;
		}
		return resultArray;
	}
	if (string == "mul") {
		for (int i = 0; i < sizeof(arrayIN) / sizeof(arrayIN[0]); i++) {
			resultArray[0] = array[0] * number;
		}
		return resultArray;

	}
	std::cout << "Wrong operation\n";
	
}

void main() {}