package glproj.jongber.androidopengl.term1.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import glproj.jongber.androidopengl.term1.view.MyGLSurfaceView;

/**
 * Created by jongber on 2016-04-10.
 * <p/>
 * On 'AndroidManifest.xml' file add '<uses-feature android:glEsVersion="0x00020000" android:required="true" />'
 * <p/>
 * This class and samples comes from 'http://developer.android.com/intl/ko/training/graphics/opengl/environment.html'
 */
public class OpenGLActivity extends Activity
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
