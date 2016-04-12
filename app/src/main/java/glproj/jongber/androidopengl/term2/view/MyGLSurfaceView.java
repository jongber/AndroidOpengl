package glproj.jongber.androidopengl.term2.view;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by jongber on 2016-04-10.
 */
public class MyGLSurfaceView extends GLSurfaceView
{
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context)
    {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer(getContext());

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}
