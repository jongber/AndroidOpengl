package glproj.jongber.androidopengl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import glproj.jongber.androidopengl.term1.activity.OpenGLActivity;
import glproj.jongber.androidopengl.term2.activity.TriangleActivity;
import glproj.jongber.androidopengl.term3.activity.RotateCubeActivity;
import glproj.jongber.androidopengl.term4.activity.TextureCubeActivity;
import glproj.jongber.androidopengl.term5.activity.ZoomActivity;


public class MainActivity extends AppCompatActivity
{
    private ListView mOpenglActivities = null;
    private ArrayAdapter<String> mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenglActivities = (ListView) findViewById(R.id.list_opengl_activity);
        mAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.activity_main_listview_item);

        mOpenglActivities.setAdapter(mAdapter);
        mOpenglActivities.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case StudyPlan.Plan1Numb:
                    {
                        Intent intent = new Intent(getApplicationContext(), OpenGLActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case StudyPlan.Plan2Numb:
                    {
                        Intent intent = new Intent(getApplicationContext(), TriangleActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case StudyPlan.Plan3Numb:
                    {
                        Intent intent = new Intent(getApplicationContext(), RotateCubeActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case StudyPlan.Plan4Numb:
                    {
                        Intent intent = new Intent(getApplicationContext(), TextureCubeActivity.class);
                        startActivity(intent);
                        break;
                    }
                    default:
                        break;
                }
            }
        });

        mAdapter.add(StudyPlan.Plan1Name);
        mAdapter.add(StudyPlan.Plan2Name);
        mAdapter.add(StudyPlan.Plan3Name);
        mAdapter.add(StudyPlan.Plan4Name);

    }
}
