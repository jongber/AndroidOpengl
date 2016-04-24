package glproj.jongber.androidopengl.term4.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import glproj.jongber.androidopengl.term4.view.comp.Shader;
import glproj.jongber.androidopengl.term4.view.comp.Texture;
import glproj.jongber.androidopengl.term4.view.comp.TextureMesh;
import glproj.jongber.androidopengl.term4.view.comp.primitive.TexCube;
import glproj.jongber.androidopengl.utils.joml.Matrix4f;

/**
 * Created by jongber on 2016-04-10.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer
{
    public static class ShaderVar
    {
        public static final String AttrPosition = "aPos";
        public static final String AttrTexCoord = "aTexCoord";

        public static final String UniProjMat = "uProjMat";
        public static final String UniWorldMat = "uWorldMat";

        public static final String UniTextureUnit = "uTextureUnit";
    }

    private Context mContext;

    private Shader mShader;
    private TextureMesh mCube;

    private float mFOV = (float)Math.toRadians(60.0f);
    private float mNearZ = 0.1f;
    private float mFarZ = 1000.f;
    private FloatBuffer mProjBuf = FloatBuffer.allocate(16);

    Matrix4f mViewMat = new Matrix4f();
    private FloatBuffer mWorldBuf = FloatBuffer.allocate(16);

    private float mRotateX = 0.0f;

    public MyGLRenderer(Context context)
    {
        mContext = context;
    }

    private void setupShader()
    {
        mShader = new Shader(mContext, "term4/vShader.vert", "term4/fShader.frag");
        mShader.initShader();

        mShader.setAttribLoc(ShaderVar.AttrPosition);
        mShader.setAttribLoc(ShaderVar.AttrTexCoord);

        mShader.setUniformLoc(ShaderVar.UniProjMat);
        mShader.setUniformLoc(ShaderVar.UniTextureUnit);
        mShader.setUniformLoc(ShaderVar.UniWorldMat);
    }

    private void setupCube()
    {
        TexCube texCube = new TexCube();
        Texture texture = new Texture(mContext, "term4/grassblock.png");

        mCube = new TextureMesh(texCube.getPosition(), texCube.getTexCoord(), texCube.getIndex(), texture);
    }

    private void setupCamera(int width, int height)
    {
        float aspectRatio = (float)width / (float)height;
        Matrix4f projMat = new Matrix4f().perspective(mFOV, aspectRatio, mNearZ, mFarZ);
        projMat.get(mProjBuf);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        setupShader();
        setupCube();

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    }

    public void onDrawFrame(GL10 unused)
    {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0.0f, 0.5f, 0.5f, 1.0f);

        mRotateX += 5;

        mShader.bindShader();
        {
            mViewMat.identity().translate(1.0f, 1.0f, -10f).
                    rotateX((float)Math.toRadians(0)).
                    rotateY((float)Math.toRadians(mRotateX)).
                    rotateZ((float)Math.toRadians(0)).
                    scale(1.0f);

            mViewMat.get(mWorldBuf);

            mShader.setUniformMatrixF(ShaderVar.UniWorldMat, mWorldBuf);
            mShader.setUniformMatrixF(ShaderVar.UniProjMat, mProjBuf);

            mShader.setUniformSampler(ShaderVar.UniTextureUnit);

            int posHandle = mShader.getAttribLoc(ShaderVar.AttrPosition);
            int texCoordHandle = mShader.getAttribLoc(ShaderVar.AttrTexCoord);

            mCube.draw(posHandle, texCoordHandle);
        }
        mShader.unbindShader();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);

        setupCamera(width, height);
    }
}
