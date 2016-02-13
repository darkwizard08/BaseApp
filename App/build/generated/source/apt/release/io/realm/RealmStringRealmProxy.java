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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.kazantsev.baseapp.domain.entity.realm.RealmString;

public class RealmStringRealmProxy extends RealmString
    implements RealmObjectProxy {

    private static long INDEX_VALUE;
    private static Map<String, Long> columnIndices;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("value");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    @Override
    public String getValue() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(INDEX_VALUE);
    }

    @Override
    public void setValue(String value) {
        realm.checkIfValid();
        row.setString(INDEX_VALUE, (String) value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_RealmString")) {
            Table table = transaction.getTable("class_RealmString");
            table.addColumn(ColumnType.STRING, "value");
            table.setPrimaryKey("");
            return table;
        }
        return transaction.getTable("class_RealmString");
    }

    public static void validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_RealmString")) {
            Table table = transaction.getTable("class_RealmString");
            if (table.getColumnCount() != 1) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 1 but was " + table.getColumnCount());
            }
            Map<String, ColumnType> columnTypes = new HashMap<String, ColumnType>();
            for (long i = 0; i < 1; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            columnIndices = new HashMap<String, Long>();
            for (String fieldName : getFieldNames()) {
                long index = table.getColumnIndex(fieldName);
                if (index == -1) {
                    throw new RealmMigrationNeededException(transaction.getPath(), "Field '" + fieldName + "' not found for type RealmString");
                }
                columnIndices.put(fieldName, index);
            }
            INDEX_VALUE = table.getColumnIndex("value");

            if (!columnTypes.containsKey("value")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'value'");
            }
            if (columnTypes.get("value") != ColumnType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'value'");
            }
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The RealmString class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_RealmString";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    public static Map<String,Long> getColumnIndices() {
        return columnIndices;
    }

    public static RealmString createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        RealmString obj = realm.createObject(RealmString.class);
        if (!json.isNull("value")) {
            obj.setValue((String) json.getString("value"));
        }
        return obj;
    }

    public static RealmString createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        RealmString obj = realm.createObject(RealmString.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("value") && reader.peek() != JsonToken.NULL) {
                obj.setValue((String) reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static RealmString copyOrUpdate(Realm realm, RealmString object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        return copy(realm, object, update, cache);
    }

    public static RealmString copy(Realm realm, RealmString newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        RealmString realmObject = realm.createObject(RealmString.class);
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setValue(newObject.getValue() != null ? newObject.getValue() : "");
        return realmObject;
    }

    static RealmString update(Realm realm, RealmString realmObject, RealmString newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setValue(newObject.getValue() != null ? newObject.getValue() : "");
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RealmString = [");
        stringBuilder.append("{value:");
        stringBuilder.append(getValue());
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
        RealmStringRealmProxy aRealmString = (RealmStringRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aRealmString.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aRealmString.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aRealmString.row.getIndex()) return false;

        return true;
    }

}
