package glproj.jongber.androidopengl.term4.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import glproj.jongber.androidopengl.term4.view.MyGLSurfaceView;


public class ZoomActivity extends Activity
{
    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }
}
