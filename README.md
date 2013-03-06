Android OpenGL picking

This is when we need to select certain objects in the OpenGL scene, when we tap on the screen.

There are plenty of articles about this in the web, but only a few about how to implement it in Android.

There are 2 main ways to do Picking - color picking and ray picking. Ray picking is more reliable.

This is, currently, the only complete and working article about how to do ray picking in Android: http://android-raypick.blogspot.de/2012/04/first-i-want-to-state-this-is-my-first.html

And it works! But it's missing a finished, runnable example, and there's some people asking for that. So I uploaded one.


Notes:
It works from OpenGL 1.

In the example there are 4 squares. It will show messages in logcat when you tap the squares. 
Note that in the intersection area, it shows messages for all the squares - it's left to the user to select the nearest one, e.g. using index or z coord.


There's also a small "framework" where each square is an object. To organize a bit. The GL object is passed to them to draw.


Installation: Download and import glpicking project. It's ready to run. If you run it, it should look like this:

![ScreenShot](https://raw.github.com/i-schuetz/Android_OpenGL_Picking/master/SC20130306-175411.png)


It requires min. API 8, but I think it's easy to modify it to run it in lower APIs (I don't need it, so I haven't checked).