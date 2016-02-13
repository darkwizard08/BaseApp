package ru.kazantsev.baseapp.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import org.greenrobot.eventbus.Subscribe;
import ru.kazantsev.baseapp.R;
import ru.kazantsev.baseapp.database.SuggestionProvider;
import ru.kazantsev.baseapp.domain.event.FragmentAttachedEvent;
import ru.kazantsev.baseapp.fragments.*;
import ru.kazantsev.baseapp.domain.Constants;


public class MainActivity extends BaseActivity {

    private CharSequence title;

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Востанавливаем фрагмент при смене ориентации экрана.
        Fragment sectionFragment = getLastFragment(savedInstanceState);
        if (sectionFragment != null) {
            replaceFragment(sectionFragment);
        } else {
            replaceFragment(DirectoryChooserFragment.class);
        }
    }

    @Override
    protected void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
           // SearchFragment.show(getCurrentFragment(), query); // Implement SearchFragment
        }
    }

    @Override
    protected boolean onNavigationItemSelected(MenuItem item) {
        // update the main content by replacing fragments
        switch (item.getItemId()) {
            default:
                replaceFragment(BaseFragment.class);
                break;
        }
        return false;
    }

    @Override
    protected void onDrawerClosed(View drawerView) {

    }

    @Override
    protected void onDrawerOpened(View drawerView) {

    }

    @Subscribe
    public void onEvent(FragmentAttachedEvent fragmentAttached) {
        title = fragmentAttached.fragment.getArguments().getString(Constants.ArgsName.TITLE);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Snackbar.make(container, R.string.back_to_exit, Snackbar.LENGTH_LONG).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else {
            super.onBackPressed();
        }
    }

}
