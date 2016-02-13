package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnType;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import ru.kazantsev.baseapp.domain.entity.realm.RealmLink;

public class RealmLinkRealmProxy extends RealmLink
    implements RealmObjectProxy {

    private static long INDEX_TITLE;
    private static long INDEX_LINK;
    private static Map<String, Long> columnIndices;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("title");
        fieldNames.add("link");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    @Override
    public String getTitle() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_TITLE);
    }

    @Override
    public void setTitle(String value) {
        realm.checkIfValid();
        row.setString(INDEX_TITLE, (String) value);
    }

    @Override
    public String getLink() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_LINK);
    }

    @Override
    public void setLink(String value) {
        realm.checkIfValid();
        row.setString(INDEX_LINK, (String) value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_RealmLink")) {
            Table table = transaction.getTable("class_RealmLink");
            table.addColumn(ColumnType.STRING, "title");
            table.addColumn(ColumnType.STRING, "link");
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_RealmLink");
    }

    public static void validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_RealmLink")) {
            Table table = transaction.getTable("class_RealmLink");
            if (table.getColumnCount() != 2) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 2 but was " + table.getColumnCount());
            }
            Map<String, ColumnType> columnTypes = new HashMap<String, ColumnType>();
            for (long i = 0; i < 2; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            columnIndices = new HashMap<String, Long>();
            for (String fieldName : getFieldNames()) {
                long index = table.getColumnIndex(fieldName);
                if (index == -1) {
                    throw new RealmMigrationNeededException(transaction.getPath(), "Field '" + fieldName + "' not found for type RealmLink");
                }
                columnIndices.put(fieldName, index);
            }
            INDEX_TITLE = table.getColumnIndex("title");
            INDEX_LINK = table.getColumnIndex("link");

            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'title'");
            }
            if (columnTypes.get("title") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'title'");
            }
            if (!columnTypes.containsKey("link")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'link'");
            }
            if (columnTypes.get("link") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'link'");
            }
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The RealmLink class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_RealmLink";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Map<String,Long> getColumnIndices() {
        return columnIndices;
    }

    public static RealmLink createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        RealmLink obj = realm.createObject(RealmLink.class);
        if (!json.isNull("title")) {
            obj.setTitle((String) json.getString("title"));
        }
        if (!json.isNull("link")) {
            obj.setLink((String) json.getString("link"));
        }
        return obj;
    }

    public static RealmLink createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        RealmLink obj = realm.createObject(RealmLink.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("title") && reader.peek() != JsonToken.NULL) {
                obj.setTitle((String) reader.nextString());
            } else if (name.equals("link")  && reader.peek() != JsonToken.NULL) {
                obj.setLink((String) reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static RealmLink copyOrUpdate(Realm realm, RealmLink object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static RealmLink copy(Realm realm, RealmLink newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        RealmLink realmObject = realm.createObject(RealmLink.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setTitle(newObject.getTitle() != null ? newObject.getTitle() : "");
        realmObject.setLink(newObject.getLink() != null ? newObject.getLink() : "");
        return realmObject;
    }

    static RealmLink update(Realm realm, RealmLink realmObject, RealmLink newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setTitle(newObject.getTitle() != null ? newObject.getTitle() : "");
        realmObject.setLink(newObject.getLink() != null ? newObject.getLink() : "");
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RealmLink = [");
        stringBuilder.append("{title:");
        stringBuilder.append(getTitle());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{link:");
        stringBuilder.append(getLink());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealmLinkRealmProxy aRealmLink = (RealmLinkRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aRealmLink.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aRealmLink.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aRealmLink.row.getIndex()) return false;

        return true;
    }

}
