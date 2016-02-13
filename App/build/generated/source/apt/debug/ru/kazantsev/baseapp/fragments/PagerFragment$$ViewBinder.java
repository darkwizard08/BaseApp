// Generated code from Butter Knife. Do not modify!
package ru.kazantsev.baseapp.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PagerFragment$$ViewBinder<T extends ru.kazantsev.baseapp.fragments.PagerFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558532, "field 'loadMoreBar'");
    target.loadMoreBar = finder.castView(view, 2131558532, "field 'loadMoreBar'");
    view = finder.findRequiredView(source, 2131558536, "field 'pagerHeader'");
    target.pagerHeader = finder.castView(view, 2131558536, "field 'pagerHeader'");
    view = finder.findRequiredView(source, 2131558535, "field 'pager'");
    target.pager = finder.castView(view, 2131558535, "field 'pager'");
  }

  @Override public void unbind(T target) {
    target.loadMoreBar = null;
    target.pagerHeader = null;
    target.pager = null;
  }
}
