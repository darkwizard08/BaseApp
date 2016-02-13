package ru.kazantsev.baseapp.lister;

import ru.kazantsev.baseapp.net.Request;
import org.jsoup.nodes.Document;

/**
 * Created by Rufim on 29.06.2015.
 */
public abstract class PageLister {
    public abstract void setPage(Request request, int index);
    public abstract int getLastPage(Document document);
}
