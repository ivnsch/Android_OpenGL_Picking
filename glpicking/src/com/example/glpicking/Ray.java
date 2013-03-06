package com.example.glpicking;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.Matrix;

public class Ray {

    float[] P0;
    float[] P1;
    
    public Ray(GL10 gl, int width, int height, float xTouch, float yTouch) {
        MatrixGrabber matrixGrabber = new MatrixGrabber();
        matrixGrabber.getCurrentState(gl);
 
        int[] viewport = {0, 0, width, height};
 
        float[] nearCoOrds = new float[3];
        float[] farCoOrds = new float[3];
        float[] temp = new float[4];
        float[] temp2 = new float[4];
        // get the near and far ords for the click
 
        float winx = xTouch, winy =(float)viewport[3] - yTouch;
 
//        Log.d(TAG, "modelView is =" + Arrays.toString(matrixGrabber.mModelView));
//        Log.d(TAG, "projection view is =" + Arrays.toString( matrixGrabber.mProjection ));
 
        int result = GLU.gluUnProject(winx, winy, 1.0f, matrixGrabber.mModelView, 0, matrixGrabber.mProjection, 0, viewport, 0, temp, 0);
 
        Matrix.multiplyMV(temp2, 0, matrixGrabber.mModelView, 0, temp, 0);
        if(result == GL10.GL_TRUE){
            nearCoOrds[0] = temp2[0] / temp2[3];
            nearCoOrds[1] = temp2[1] / temp2[3];
            nearCoOrds[2] = temp2[2] / temp2[3];
 
        }
 
        result = GLU.gluUnProject(winx, winy, 0, matrixGrabber.mModelView, 0, matrixGrabber.mProjection, 0, viewport, 0, temp, 0);
        Matrix.multiplyMV(temp2,0,matrixGrabber.mModelView, 0, temp, 0);
        if(result == GL10.GL_TRUE){
            farCoOrds[0] = temp2[0] / temp2[3];
            farCoOrds[1] = temp2[1] / temp2[3];
            farCoOrds[2] = temp2[2] / temp2[3];
        }
        this.P0 = farCoOrds;
        this.P1 = nearCoOrds;
    }   
}
