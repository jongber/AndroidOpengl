package glproj.jongber.androidopengl.term2.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import glproj.jongber.androidopengl.utils.AssetUtils;


/**
 * Created by jongber on 2016-04-10.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer
{
    private int mShaderProg = 0;
    private int mVShader = 0;
    private int mFShader = 0;

    private String mVSsrc;
    private String mFSsrc;

    private FloatBuffer mTriMeshBuf;
    private int mPositionHandle = 0;

    public MyGLRenderer(Context context)
    {
        mVSsrc = AssetUtils.readString(context, "term2/vShader.vert");
        mFSsrc = AssetUtils.readString(context, "term2/fShader.frag");

    }

    private void init()
    {
        {
            float[] vertecies = new float[]{   // in counterclockwise order:
                    0.0f, 0.622008459f, 0.0f, // top
                    -0.5f, -0.311004243f, 0.0f, // bottom left
                    0.5f, -0.311004243f, 0.0f  // bottom right
            };

            ByteBuffer bb = ByteBuffer.allocateDirect(vertecies.length * 4);
            bb.order(ByteOrder.nativeOrder());

            mTriMeshBuf = bb.asFloatBuffer();
            mTriMeshBuf.put(vertecies);
            mTriMeshBuf.position(0);

        }
        // shader creation block
        {
            mShaderProg = GLES20.glCreateProgram();

            mVShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
            GLES20.glShaderSource(mVShader, mVSsrc);
            GLES20.glCompileShader(mVShader);

            mFShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
            GLES20.glShaderSource(mFShader, mFSsrc);
            GLES20.glCompileShader(mFShader);


            GLES20.glAttachShader(mShaderProg, mVShader);
            GLES20.glAttachShader(mShaderProg, mFShader);

            GLES20.glLinkProgram(mShaderProg);
        }

    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        init();
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
    }

    public void onDrawFrame(GL10 unused)
    {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glUseProgram(mShaderProg);
        {
            mPositionHandle = GLES20.glGetAttribLocation(mShaderProg, "pos");
            GLES20.glEnableVertexAttribArray(mPositionHandle);
            GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 12, mTriMeshBuf);
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 12);
            GLES20.glDisableVertexAttribArray(mPositionHandle);
        }
        GLES20.glUseProgram(0);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);
    }
}
