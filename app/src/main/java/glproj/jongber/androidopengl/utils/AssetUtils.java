package glproj.jongber.androidopengl.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
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
}
