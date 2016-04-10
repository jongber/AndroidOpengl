package glproj.jongber.androidopengl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import glproj.jongber.androidopengl.term1.activity.OpenGLActivity;

public class MainActivity extends AppCompatActivity
{
    private ListView mOpenglActivities = null;
    private ArrayAdapter<String> mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenglActivities = (ListView)findViewById(R.id.list_opengl_activity);
        mAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.activity_main_listview_item);

        mOpenglActivities.setAdapter(mAdapter);
        mOpenglActivities.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0 :
                    {
                        Intent intent = new Intent(getApplicationContext(), OpenGLActivity.class);
                        startActivity(intent);
                    }
                        break;
                    default:
                        break;
                }
            }
        });

        mAdapter.add("OpenGL ES Test 1 Green Screen");

    }
}
