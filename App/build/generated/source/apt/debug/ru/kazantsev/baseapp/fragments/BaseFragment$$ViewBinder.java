// Generated code from Butter Knife. Do not modify!
package ru.kazantsev.baseapp.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BaseFragment$$ViewBinder<T extends ru.kazantsev.baseapp.fragments.BaseFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2130968613, "field 'messageView'");
    target.messageView = finder.castView(view, 2130968613, "field 'messageView'");
  }

  @Override public void unbind(T target) {
    target.messageView = null;
  }
}
