package glproj.jongber.androidopengl.term3.view.helper.primitive;

/**
 * Created by jongber on 2016-04-16.
 */
public class Quad {
    private float[] mPositions;
    private float[] mColors;
    private int[] mIndecie;

    public Quad() {
        mPositions = new float[] {
                -0.5f, 0.5f, -1.05f,
                -0.5f, -0.5f, -1.05f,
                0.5f, -0.5f, -1.05f,
                0.5f, 0.5f, -1.05f, };

        mColors = new float[] { 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, };

        mIndecie = new int[] { 0, 1, 3, 3, 1, 2, };
    }


    public float[] getPosition() {
        return mPositions;
    }

    public float[] getColor() {
        return mColors;
    }

    public int[] getIndecie() {
        return mIndecie;
    }
}
