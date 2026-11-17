package com.example.l6_menus;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.popupButton);

        //Tells Android that whenever the user long-presses the textView, a Context Menu should pop up
        registerForContextMenu(textView);

        //Listen for a normal tap on the button, which triggers your custom showPopupMenu() method
        button.setOnClickListener(v -> showPopupMenu(v));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);  //Inflates your main_menu.xml file into the app bar
        return true;
    }

    //Listens for when a user taps an item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSettings) {
            Toast.makeText(this,
                    "Settings selected", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menuAbout) {
            Toast.makeText(this,
                    "About selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.context_edit) {
            Toast.makeText(this,
                    "Edit item selected", Toast.LENGTH_SHORT).show();

        }else if (item.getItemId() == R.id.context_delete) {
            Toast.makeText(this,
                    "Delete item selected", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    private void showPopupMenu(View view) {
        //Creates a popup anchored directly to the button that was clicked.
        PopupMenu popup = new PopupMenu(this, view);

        //Loads the items from popup_menu.xml
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //An anonymous inner class that handles clicks
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.popup_option1) {
                    Toast.makeText(MainActivity.this,
                            "Option 1 selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.popup_option2) {
                    Toast.makeText(MainActivity.this,
                            "Option 2 selected", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
        popup.show();   //Renders and displays the menu on the screen
    }
}