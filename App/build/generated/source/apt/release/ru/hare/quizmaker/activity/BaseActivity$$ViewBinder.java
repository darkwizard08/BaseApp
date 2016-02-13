// Generated code from Butter Knife. Do not modify!
package ru.kazantsev.baseapp.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BaseActivity$$ViewBinder<T extends ru.kazantsev.baseapp.activity.BaseActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558510, "field 'container'");
    target.container = finder.castView(view, 2131558510, "field 'container'");
    view = finder.findRequiredView(source, 2131558512, "field 'containerDetails'");
    target.containerDetails = finder.castView(view, 2131558512, "field 'containerDetails'");
    view = finder.findRequiredView(source, 2131558557, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131558557, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131558511, "field 'mainBorder'");
    target.mainBorder = view;
  }

  @Override public void unbind(T target) {
    target.container = null;
    target.containerDetails = null;
    target.toolbar = null;
    target.mainBorder = null;
  }
}
