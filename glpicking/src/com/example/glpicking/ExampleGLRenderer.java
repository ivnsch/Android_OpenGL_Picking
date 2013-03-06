package com.example.glpicking;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class ExampleGLRenderer implements Renderer {

	private List<ExampleGLObject> slides = new ArrayList<ExampleGLObject>();

	private float ratio;
	
	private int width;
	private int height;
	
	private int pressX = -99;
	private int pressY = -99;
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

		//add 4 test objects to the scene
		slides.add(new ExampleGLObject("red", new float[] {1, 0, 0, 1}, new float[] {0, 0, .5f}));
		slides.add(new ExampleGLObject("yellow", new float[] {1, 1, 0, 1}, new float[] {.5f, .5f, 1f}));
		slides.add(new ExampleGLObject("green", new float[] {0, 1, 0, 1}, new float[] {-1.2f, 0, 0}));
		slides.add(new ExampleGLObject("blue", new float[] {0, 0, 1, 1}, new float[] {.5f, -1f, 0}));
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);		

		gl.glMatrixMode(GL10.GL_MODELVIEW);

		Ray ray = null;
		if (pressX != -99) {
			ray = new Ray(gl, width, height, pressX, pressY);
		}
		
		for (ExampleGLObject slide : slides) {
			slide.draw(gl, ray);
		}
		pressX = -99;
	}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		this.width = width;
		this.height = height;
		
		ratio = (float)width / height;
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 27);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);

		gl.glLoadIdentity();
	}

	public void onPress(int x, int y) {
		pressX = x;
		pressY = y;
	}
}