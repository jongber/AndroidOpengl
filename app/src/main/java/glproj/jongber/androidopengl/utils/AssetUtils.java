package glproj.jongber.androidopengl.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jongber on 2016-04-12.
 */
public class AssetUtils
{
    public static String readString(Context context, String fileName)
    {
        String ret = "";

        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));

            String readLine;
            while ((readLine = reader.readLine()) != null)
            {
                ret += readLine;
                ret += System.lineSeparator();
            }
        }
        catch (IOException e)
        {

        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                }
            }
        }


        return ret;
    }

    public static Bitmap loadImage(Context context, int resourceId)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        return bitmap;
    }

    public static Bitmap loadImage(Context context, String assetFileName)
    {
        AssetManager manager = context.getAssets();
        InputStream inStr = null;
        try
        {
            inStr = manager.open(assetFileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(inStr);

        return bitmap;
    }
}
