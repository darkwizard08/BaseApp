// Generated code from Butter Knife. Do not modify!
package ru.kazantsev.baseapp.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ErrorFragment$$ViewBinder<T extends ru.kazantsev.baseapp.fragments.ErrorFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558517, "field 'errorImage'");
    target.errorImage = finder.castView(view, 2131558517, "field 'errorImage'");
    view = finder.findRequiredView(source, 2131558518, "field 'errorMessage'");
    target.errorMessage = finder.castView(view, 2131558518, "field 'errorMessage'");
    view = finder.findRequiredView(source, 2131558515, "field 'swipeRefresh'");
    target.swipeRefresh = finder.castView(view, 2131558515, "field 'swipeRefresh'");
  }

  @Override public void unbind(T target) {
    target.errorImage = null;
    target.errorMessage = null;
    target.swipeRefresh = null;
  }
}
