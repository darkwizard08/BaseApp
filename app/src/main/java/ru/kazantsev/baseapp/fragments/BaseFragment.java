package ru.kazantsev.baseapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import org.greenrobot.eventbus.EventBus;
import ru.kazantsev.baseapp.R;
import ru.kazantsev.baseapp.activity.BaseActivity;
import ru.kazantsev.baseapp.domain.Constants;
import ru.kazantsev.baseapp.domain.event.Event;
import ru.kazantsev.baseapp.domain.event.FragmentAttachedEvent;
import ru.kazantsev.baseapp.util.FragmentBuilder;
import ru.kazantsev.baseapp.util.GuiUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment implements BaseActivity.BackCallback {

    /**
     * Returns a new instance of this fragment
     */
    public static <F extends BaseFragment> F newInstance(Class<F> fragmentClass, Bundle args) {
        return FragmentBuilder.newInstance(fragmentClass, args);
    }

    public static <F extends BaseFragment> F newInstance(Class<F> fragmentClass) {
        return FragmentBuilder.newInstance(fragmentClass);
    }

    // Базовые методы
    protected static <F extends BaseFragment> F show(FragmentBuilder builder, @IdRes int container, Class<F> fragmentClass, String key, Object obj) {
        return builder.putArg(key, obj).newFragment().replaceFragment(container, fragmentClass);
    }

    protected static <F extends BaseFragment> F show(FragmentManager manager, @IdRes int container, Class<F> fragmentClass, String key, Object obj) {
        return new FragmentBuilder(manager).newFragment().putArg(key, obj).replaceFragment(container, fragmentClass);
    }

    protected static <F extends BaseFragment> F show(BaseFragment fragment, Class<F> fragmentClass, String key, Object obj) {
        return new FragmentBuilder(fragment.getFragmentManager()).newFragment().addToBackStack().putArg(key, obj).replaceFragment(fragment, fragmentClass);
    }

    // Подобными методами должны вызыватся наследуемые фрагменты
    protected static BaseFragment show(FragmentBuilder builder, @IdRes int container, String message) {
        return show(builder, container, BaseFragment.class, Constants.ArgsName.MESSAGE, message);
    }

    protected static BaseFragment show(FragmentManager manager, @IdRes int container, String message) {
        return show(manager, container, BaseFragment.class, Constants.ArgsName.MESSAGE, message);
    }

    protected static BaseFragment show(BaseFragment fragment, String message) {
        return show(fragment, BaseFragment.class, Constants.ArgsName.MESSAGE, message);
    }

    protected boolean retainInstance = true;

    private TextView messageView;


    public BaseFragment() {
    }

    /**
     * This method will only be called once when the retained
     * Fragment is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(retainInstance);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, rootView);
        String message = getArguments().getString(Constants.ArgsName.MESSAGE, "В разработке...");
        GuiUtils.setText(rootView.findViewById(R.id.test_message), message);
        return rootView;
    }

    public BaseFragment show(FragmentManager manager, @IdRes int container, String key, Object obj) {
        return show(manager, container, this.getClass(), key, obj);
    }

    public int getContainerId() {
        return ((ViewGroup) getView().getParent()).getId();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        postEvent(new FragmentAttachedEvent(this));
    }

    public boolean allowBackPress() {
       return true;
    }

    protected void postEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public void bind(View view) {
        ButterKnife.bind(this, view);
    }

    public void unbind() {
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind();
    }


}
