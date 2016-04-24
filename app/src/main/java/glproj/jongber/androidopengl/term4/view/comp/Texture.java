package glproj.jongber.androidopengl.term4.view.comp;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import glproj.jongber.androidopengl.StaticContext;
import glproj.jongber.androidopengl.utils.AssetUtils;

/**
 * Created by jongber on 2016-04-24.
 */
public class Texture
{
    private int[] mTextureObjIds;

    public Texture(Context context, String assetFileName)
    {
        init(context, assetFileName);
    }

    private void init(Context context, String assetFileName)
    {
        Bitmap bitmap = AssetUtils.loadImage(context, assetFileName);

        if (bitmap == null)
        {
            Log.e(StaticContext.LogTag, "inavlid bitmap [" + assetFileName + "]");
            return;
        }

        mTextureObjIds = new int[1];
        GLES20.glGenTextures(1, mTextureObjIds, 0); // generate 1 texture object and stored ids to mTextureObjIds

        if (mTextureObjIds[0] == 0)
        {
            Log.e(StaticContext.LogTag, "cannot generate texture object");
            return;
        }

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureObjIds[0]);
        {
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
        }
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,0);
    }


    public int getTextureId()
    {
        return mTextureObjIds[0];
    }

    public void cleanup()
    {
        if (mTextureObjIds[0] != 0)
        {
            GLES20.glDeleteTextures(1, mTextureObjIds, 0);
        }
    }
}











