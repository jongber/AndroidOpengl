package glproj.jongber.androidopengl.term3.view.helper;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by jongber on 2016-04-16.
 */
public class Mesh
{
    private float[] mPos;
    private float[] mColor;
    private short[] mIndex;

    private FloatBuffer mVertBuf;
    private FloatBuffer mColorBuf;
    private ShortBuffer mIndexBuf;

    public Mesh(float[] pos, float[] color, short[] index)
    {
        mPos = pos;
        mColor = color;
        mIndex = index;

        ByteBuffer posBuf = ByteBuffer.allocateDirect(pos.length * 4);
        posBuf.order(ByteOrder.nativeOrder());

        mVertBuf = posBuf.asFloatBuffer();
        mVertBuf.put(pos);
        mVertBuf.position(0);

        ByteBuffer colorBuf = ByteBuffer.allocateDirect(color.length * 4);
        colorBuf.order(ByteOrder.nativeOrder());

        mColorBuf = colorBuf.asFloatBuffer();
        mColorBuf.put(color);
        mColorBuf.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                index.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        mIndexBuf = dlb.asShortBuffer();
        mIndexBuf.put(index);
        mIndexBuf.position(0);
    }

    public void draw(int posHandle)
    {
        GLES20.glEnableVertexAttribArray(posHandle);

        GLES20.glVertexAttribPointer(posHandle, 3 , GLES20.GL_FLOAT, false, 3 * 4, mVertBuf);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, mIndex.length, GLES20.GL_UNSIGNED_SHORT, mIndexBuf);

        GLES20.glDisableVertexAttribArray(posHandle);
    }

    public void draw(int posHandle, int colorHandle)
    {
        GLES20.glEnableVertexAttribArray(posHandle);
        GLES20.glEnableVertexAttribArray(colorHandle);

        GLES20.glVertexAttribPointer(posHandle, 3 , GLES20.GL_FLOAT, false, 3 * 4, mVertBuf);
        GLES20.glVertexAttribPointer(colorHandle, 3 , GLES20.GL_FLOAT, false, 3 * 4, mColorBuf);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, mIndex.length, GLES20.GL_UNSIGNED_SHORT, mIndexBuf);

        GLES20.glDisableVertexAttribArray(posHandle);
        GLES20.glDisableVertexAttribArray(colorHandle);

    }

    public FloatBuffer getPosBuf()
    {
        return mVertBuf;
    }

    public FloatBuffer getColorBuf()
    {
        return mColorBuf;
    }


}
