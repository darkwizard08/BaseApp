package ru.kazantsev.baseapp.lister;

/**
 * Created by Dmitry on 30.10.2015.
 */
public class DefaultJsoupSelector extends JsoupRowSelector {
    @Override
    public String getRowSelector() {
        return "dl";
    }
}
