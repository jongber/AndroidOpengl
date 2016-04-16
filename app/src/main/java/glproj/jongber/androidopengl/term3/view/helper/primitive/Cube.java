package glproj.jongber.androidopengl.term3.view.helper.primitive;

/**
 * Created by jongber on 2016-04-16.
 */
public class Cube
{

    private float[] mPositions;
    private float[] mColor;
    private short[] mIndex;

    public Cube()
    {
        mPositions = new float[]{
                // VO
                -0.5f, 0.5f, 0.5f,
                // V1
                -0.5f, -0.5f, 0.5f,
                // V2
                0.5f, -0.5f, 0.5f,
                // V3
                0.5f, 0.5f, 0.5f,
                // V4
                -0.5f, 0.5f, -0.5f,
                // V5
                0.5f, 0.5f, -0.5f,
                // V6
                -0.5f, -0.5f, -0.5f,
                // V7
                0.5f, -0.5f, -0.5f,};

        mColor = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };

        mIndex = new short[]{
                // Front face
                0, 1, 3, 3, 1, 2,
                // Top Face
                4, 0, 3, 5, 4, 3,
                // Right face
                3, 2, 7, 5, 3, 7,
                // Left face
                0, 1, 6, 4, 0, 6,
                // Bottom face
                6, 1, 2, 7, 6, 2,
                // Back face
                4, 6, 7, 5, 4, 7,
        };
    }

    public float[] getPosition()
    {
        return mPositions;
    }

    public float[] getColor()   {return mColor;}

    public short[] getIndex()   {return mIndex;}
}
