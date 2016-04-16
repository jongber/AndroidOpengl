package glproj.jongber.androidopengl.term3.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import glproj.jongber.androidopengl.term3.view.helper.Mesh;
import glproj.jongber.androidopengl.term3.view.helper.Shader;
import glproj.jongber.androidopengl.term3.view.helper.primitive.Cube;
import glproj.jongber.androidopengl.utils.joml.Matrix4f;


/**
 * Created by jongber on 2016-04-10.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer
{
    private Mesh mCube;
    private Shader mShader;

    private float mFOV = (float)Math.toRadians(60.0f);
    private float mNearZ = 0.1f;
    private float mFarZ = 1000.f;
    private FloatBuffer mProjBuf = FloatBuffer.allocate(16);

    Matrix4f mViewMat = new Matrix4f();
    private FloatBuffer mWorldBuf = FloatBuffer.allocate(16);

    private float mRotateX = 0.0f;

    public static class ShaderVariables
    {
        public final static String VertexPos = "aPos";
        public final static String VertexColor = "aColor";

        public final static String MatProjection = "uProjMat";
        public final static String MatWorld = "uWorldMat";
    }

    public MyGLRenderer(Context context)
    {
        mShader = new Shader(context, "term3/vShader.vert", "term3/fShader.frag");
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        // shader variables
        mShader.initShader();
        mShader.setAttribLoc(ShaderVariables.VertexPos);
        mShader.setAttribLoc(ShaderVariables.VertexColor);
        mShader.setUniformLoc(ShaderVariables.MatProjection);
        mShader.setUniformLoc(ShaderVariables.MatWorld);

        Cube cube = new Cube();
        mCube = new Mesh(cube.getPosition(), cube.getColor(), cube.getIndex());

        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }

    public void onDrawFrame(GL10 unused)
    {
        mRotateX += 5;

        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);

        mShader.bindShader();
        {
            mViewMat.identity().translate(1.0f, 1.0f, -10f).
                    rotateX((float)Math.toRadians(0)).
                    rotateY((float)Math.toRadians(mRotateX)).
                    rotateZ((float)Math.toRadians(0)).
                    scale(1.0f);

            mViewMat.get(mWorldBuf);

            mShader.setUniformMatrixF(ShaderVariables.MatWorld, mWorldBuf);
            mShader.setUniformMatrixF(ShaderVariables.MatProjection, mProjBuf);

            int posHandle = mShader.getAttribLoc(ShaderVariables.VertexPos);
            int colorHandle = mShader.getAttribLoc(ShaderVariables.VertexColor);
            mCube.draw(posHandle, colorHandle);
        }

        mShader.unbindShader();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);

        float aspectRatio = (float)width / (float)height;
        Matrix4f projMat = new Matrix4f().perspective(mFOV, aspectRatio, mNearZ, mFarZ);
        projMat.get(mProjBuf);
    }

    public void cleanup()
    {
        mShader.cleanup();
    }
}
