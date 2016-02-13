// Generated code from Butter Knife. Do not modify!
package ru.kazantsev.baseapp.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BaseActivity$$ViewBinder<T extends ru.kazantsev.baseapp.activity.BaseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558515, "field 'container'");
    target.container = finder.castView(view, 2131558515, "field 'container'");
    view = finder.findRequiredView(source, 2131558517, "field 'containerDetails'");
    target.containerDetails = finder.castView(view, 2131558517, "field 'containerDetails'");
    view = finder.findRequiredView(source, 2131558514, "field 'drawerLayout'");
    target.drawerLayout = finder.castView(view, 2131558514, "field 'drawerLayout'");
    view = finder.findRequiredView(source, 2131558518, "field 'navigationView'");
    target.navigationView = finder.castView(view, 2131558518, "field 'navigationView'");
    view = finder.findRequiredView(source, 2131558567, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558567, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558516, "field 'mainBorder'");
    target.mainBorder = view;
  }

  @Override public void unbind(T target) {
    target.container = null;
    target.containerDetails = null;
    target.drawerLayout = null;
    target.navigationView = null;
    target.toolbar = null;
    target.mainBorder = null;
  }
}
