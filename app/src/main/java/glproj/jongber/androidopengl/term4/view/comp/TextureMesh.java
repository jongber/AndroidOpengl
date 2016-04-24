package glproj.jongber.androidopengl.term4.view.comp;

import android.content.Context;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by jongber on 2016-04-16.
 */
public class TextureMesh
{
    private float[] mPos;
    private float[] mTexCoord;
    private short[] mIndex;

    private FloatBuffer mVertBuf;
    private FloatBuffer mTexBuf;
    private ShortBuffer mIndexBuf;

    private static int COORD_PER_VERTEX = 3;
    private static int COORD_PER_TEXCOORD = 2;
    private static int BYTE_OF_FLOAT = 4;

    private Texture mTexture;

    public TextureMesh(float[] pos, float[] texCoord, short[] index, Texture texture)
    {
        mTexture = texture;

        mPos = pos;
        mTexCoord = texCoord;
        mIndex = index;

        ByteBuffer posBuf = ByteBuffer.allocateDirect(pos.length * BYTE_OF_FLOAT);
        posBuf.order(ByteOrder.nativeOrder());

        mVertBuf = posBuf.asFloatBuffer();
        mVertBuf.put(pos);
        mVertBuf.position(0);

        ByteBuffer texBuf = ByteBuffer.allocateDirect(texCoord.length * BYTE_OF_FLOAT);
        texBuf.order(ByteOrder.nativeOrder());

        mTexBuf = texBuf.asFloatBuffer();
        mTexBuf.put(texCoord);
        mTexBuf.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                index.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        mIndexBuf = dlb.asShortBuffer();
        mIndexBuf.put(index);
        mIndexBuf.position(0);
    }

    public void draw(int posHandle, int texCoordHandle)
    {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture.getTextureId());

        GLES20.glEnableVertexAttribArray(posHandle);
        GLES20.glEnableVertexAttribArray(texCoordHandle);

        GLES20.glVertexAttribPointer(posHandle, COORD_PER_VERTEX , GLES20.GL_FLOAT, false, COORD_PER_VERTEX * BYTE_OF_FLOAT, mVertBuf);
        GLES20.glVertexAttribPointer(texCoordHandle, COORD_PER_TEXCOORD, GLES20.GL_FLOAT, false, COORD_PER_TEXCOORD * BYTE_OF_FLOAT, mTexBuf);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, mIndex.length, GLES20.GL_UNSIGNED_SHORT, mIndexBuf);

        GLES20.glDisableVertexAttribArray(posHandle);
        GLES20.glDisableVertexAttribArray(texCoordHandle);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    public FloatBuffer getPosBuf()
    {
        return mVertBuf;
    }


}
