package cc.rememberme.demo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import cc.rememberme.demo.databinding.ActivityMainTabBinding;
import cc.rememberme.demo.ui.main.SectionsPagerAdapter;
import cc.rememberme.demo.ui.nav.NavMainActivity;

public class MainTabActivity extends AppCompatActivity {

    private ActivityMainTabBinding binding;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainTabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MainTabActivity.this.openOptionsMenu();
                            }
                        }).show();
            }
        });

        NavMainActivity.launch(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST + 1, 1,
                getString(R.string.menu_open)).setIcon(android.R.drawable.ic_menu_add);
        menu.add(Menu.NONE, Menu.FIRST + 2, 2, getString(R.string.menu_calendar))
                .setIcon(android.R.drawable.ic_menu_agenda);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                Snackbar.make(fab, "First Menu Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case Menu.FIRST + 2:
                Snackbar.make(fab, "Second Menu Clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}