package glproj.jongber.androidopengl.term3.view.helper;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import java.nio.FloatBuffer;
import java.util.HashMap;

import glproj.jongber.androidopengl.StaticContext;
import glproj.jongber.androidopengl.utils.AssetUtils;

/**
 * Created by jongber on 2016-04-16.
 */
public class Shader
{
    private String mVSrc;
    private String mFSrc;

    private int mVShaderId;
    private int mFShaderId;

    private int mProgramId;

    private HashMap<String, Integer> mAttribMap = new HashMap<>();
    private HashMap<String, Integer> mUniformMap = new HashMap<>();

    public Shader(Context context, String vFileName, String fFileName)
    {
        mVSrc = AssetUtils.readString(context, vFileName);
        mFSrc = AssetUtils.readString(context, fFileName);
    }

    public void initShader()
    {
        mVShaderId = createShader(GLES20.GL_VERTEX_SHADER, mVSrc);
        mFShaderId = createShader(GLES20.GL_FRAGMENT_SHADER, mFSrc);

        linkShader();
    }

    private int createShader(int type, String src)
    {
        int shaderId = GLES20.glCreateShader(type);
        if (shaderId == 0)
        {
            Log.e(StaticContext.LogTag, "Cannot create Shader!");
            return shaderId;
        }

        GLES20.glShaderSource(shaderId, src);

        GLES20.glCompileShader(shaderId);

        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0)
        {
            Log.e(StaticContext.LogTag, GLES20.glGetShaderInfoLog(shaderId));
        }

        return shaderId;
    }

    private void linkShader()
    {
        mProgramId = GLES20.glCreateProgram();

        GLES20.glAttachShader(mProgramId, mVShaderId);
        GLES20.glAttachShader(mProgramId, mFShaderId);

        GLES20.glLinkProgram(mProgramId);

        final int[] linkStatus = new int[1];
        GLES20.glGetShaderiv(mProgramId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0)
        {
            Log.e(StaticContext.LogTag, GLES20.glGetShaderInfoLog(mProgramId));
        }
    }

    public void setAttribLoc(String name)
    {
        if (mAttribMap.get(name) == null)
        {
            int loc = GLES20.glGetAttribLocation(mProgramId, name);
            mAttribMap.put(name, loc);
        }
    }

    public int getAttribLoc(String attribName)
    {
        Integer result = mAttribMap.get(attribName);

        if (result == null)
        {
            return 0;
        }
        else
        {
            return result.intValue();
        }
    }

    public void setUniformLoc(String uniformName)
    {
        if (mUniformMap.get(uniformName) == null)
        {
            int loc = GLES20.glGetUniformLocation(mProgramId, uniformName);
            mUniformMap.put(uniformName, loc);
        }
    }

    public int getUniformLoc(String uniformName)
    {
        Integer result = mUniformMap.get(uniformName);

        if (result == null)
        {
            return 0;
        }
        else
        {
            return result.intValue();
        }
    }

    public void setUniformMatrixF(String uniformValue, FloatBuffer buffer)
    {
        int loc = getUniformLoc(uniformValue);
        if (loc >= 0)
        {
            GLES20.glUniformMatrix4fv(loc, 1, false, buffer);
        }
        else
        {
            Log.e(StaticContext.LogTag, "invalid uniform value[" + uniformValue + "]");
        }
    }

    public void setUniform4f(String uniformValue, float[] value)
    {
        int loc = getUniformLoc(uniformValue);
        if (loc != 0)
        {
            GLES20.glUniform4fv(loc, 1, value, 0);
        }
        else
        {
            Log.e(StaticContext.LogTag, "invalid uniform value[" + uniformValue + "]");
        }
    }

    public void bindShader()
    {
        GLES20.glUseProgram(mProgramId);
    }

    public void unbindShader()
    {
        GLES20.glUseProgram(0);
    }

    public void cleanup()
    {
        if (mProgramId != 0)
        {
            if (mVShaderId != 0)
            {
                GLES20.glDetachShader(mProgramId, mVShaderId);
            }
            if (mFShaderId != 0)
            {
                GLES20.glDetachShader(mProgramId, mFShaderId);
            }

            GLES20.glDeleteShader(mProgramId);
        }
    }
}
