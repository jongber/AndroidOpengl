package glproj.jongber.androidopengl.term3.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import glproj.jongber.androidopengl.term3.view.helper.Mesh;
import glproj.jongber.androidopengl.term3.view.helper.Shader;
import glproj.jongber.androidopengl.term3.view.helper.primitive.Cube;
import glproj.jongber.androidopengl.utils.AssetUtils;


/**
 * Created by jongber on 2016-04-10.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer
{
    private Mesh mCube;
    private Shader mShader;



    public MyGLRenderer(Context context)
    {
        mShader = new Shader(context, "term3/vShader.vert", "term3/fShader.frag");
    }


    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        mShader.initShader();
        mShader.setAttribLoc("aPos");
        mShader.setAttribLoc("aColor");

        setCube();

        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }

    private void setCube()
    {
        Cube cube = new Cube();
        mCube = new Mesh(cube.getPosition(), cube.getColor(), cube.getIndex());

    }

    public void onDrawFrame(GL10 unused)
    {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);

        mShader.bindShader();
        {
            int posHandle = mShader.getAttribLoc("aPos");
            int colorHandle = mShader.getAttribLoc("aColor");

            mCube.draw(posHandle, colorHandle);
        }

        mShader.unbindShader();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);
    }

    public void cleanup()
    {
        mShader.cleanup();
    }
}
