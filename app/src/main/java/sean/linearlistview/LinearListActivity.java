package sean.linearlistview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class LinearListActivity extends AppCompatActivity {

    private String TAG = "LinearListView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearListLayout linearListLayout = (LinearListLayout) findViewById(R.id.linear_list_layout);

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(Integer.toString(i));
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, R.layout.list_item, R.id.textview, items);

        linearListLayout.setAdapter(itemsAdapter);


        linearListLayout.setOnItemClickListener(new LinearListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object itemObject, int position) {
                Log.d("LinearListView", "position:" + position + " View:" + view + " itemObject:" + itemObject);

                Snackbar snackbar = Snackbar.make(findViewById(R.id.scrollview), "Position:" + position, Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundResource(android.R.color.holo_green_dark);
                snackbar.show();
            }
        });
    }

}
