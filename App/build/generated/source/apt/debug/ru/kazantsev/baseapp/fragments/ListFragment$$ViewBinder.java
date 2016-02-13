// Generated code from Butter Knife. Do not modify!
package ru.kazantsev.baseapp.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ListFragment$$ViewBinder<T extends ru.kazantsev.baseapp.fragments.ListFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558563, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131558563, "field 'progressBar'");
    view = finder.findRequiredView(source, 2131558564, "field 'loadingText'");
    target.loadingText = finder.castView(view, 2131558564, "field 'loadingText'");
    view = finder.findRequiredView(source, 2131558532, "field 'loadMoreBar'");
    target.loadMoreBar = finder.castView(view, 2131558532, "field 'loadMoreBar'");
    view = finder.findRequiredView(source, 2131558533, "field 'itemList'");
    target.itemList = finder.castView(view, 2131558533, "field 'itemList'");
    view = finder.findRequiredView(source, 2131558528, "field 'swipeRefresh'");
    target.swipeRefresh = finder.castView(view, 2131558528, "field 'swipeRefresh'");
  }

  @Override public void unbind(T target) {
    target.progressBar = null;
    target.loadingText = null;
    target.loadMoreBar = null;
    target.itemList = null;
    target.swipeRefresh = null;
  }
}
