package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.africano.myapplication1.RowMovie;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RowMovieRealmProxy extends RowMovie
    implements RealmObjectProxy {

    static final class RowMovieColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long posterIndex;
        public final long pannerIndex;
        public final long RelaseDateIndex;
        public final long titleIndex;
        public final long overViewIndex;
        public final long voteAvgIndex;

        RowMovieColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(7);
            this.idIndex = getValidColumnIndex(path, table, "RowMovie", "id");
            indicesMap.put("id", this.idIndex);

            this.posterIndex = getValidColumnIndex(path, table, "RowMovie", "poster");
            indicesMap.put("poster", this.posterIndex);

            this.pannerIndex = getValidColumnIndex(path, table, "RowMovie", "panner");
            indicesMap.put("panner", this.pannerIndex);

            this.RelaseDateIndex = getValidColumnIndex(path, table, "RowMovie", "RelaseDate");
            indicesMap.put("RelaseDate", this.RelaseDateIndex);

            this.titleIndex = getValidColumnIndex(path, table, "RowMovie", "title");
            indicesMap.put("title", this.titleIndex);

            this.overViewIndex = getValidColumnIndex(path, table, "RowMovie", "overView");
            indicesMap.put("overView", this.overViewIndex);

            this.voteAvgIndex = getValidColumnIndex(path, table, "RowMovie", "voteAvg");
            indicesMap.put("voteAvg", this.voteAvgIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final RowMovieColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("poster");
        fieldNames.add("panner");
        fieldNames.add("RelaseDate");
        fieldNames.add("title");
        fieldNames.add("overView");
        fieldNames.add("voteAvg");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    RowMovieRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (RowMovieColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public long getId() {
        realm.checkIfValid();
        return (long) row.getLong(columnInfo.idIndex);
    }

    @Override
    public void setId(long value) {
        realm.checkIfValid();
        row.setLong(columnInfo.idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getPoster() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.posterIndex);
    }

    @Override
    public void setPoster(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.posterIndex);
            return;
        }
        row.setString(columnInfo.posterIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getPanner() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.pannerIndex);
    }

    @Override
    public void setPanner(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.pannerIndex);
            return;
        }
        row.setString(columnInfo.pannerIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getRelaseDate() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.RelaseDateIndex);
    }

    @Override
    public void setRelaseDate(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.RelaseDateIndex);
            return;
        }
        row.setString(columnInfo.RelaseDateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getTitle() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.titleIndex);
    }

    @Override
    public void setTitle(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.titleIndex);
            return;
        }
        row.setString(columnInfo.titleIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getOverView() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.overViewIndex);
    }

    @Override
    public void setOverView(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.overViewIndex);
            return;
        }
        row.setString(columnInfo.overViewIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double getVoteAvg() {
        realm.checkIfValid();
        return (double) row.getDouble(columnInfo.voteAvgIndex);
    }

    @Override
    public void setVoteAvg(double value) {
        realm.checkIfValid();
        row.setDouble(columnInfo.voteAvgIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_RowMovie")) {
            Table table = transaction.getTable("class_RowMovie");
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "poster", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "panner", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "RelaseDate", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "title", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "overView", Table.NULLABLE);
            table.addColumn(RealmFieldType.DOUBLE, "voteAvg", Table.NOT_NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_RowMovie");
    }

    public static RowMovieColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_RowMovie")) {
            Table table = transaction.getTable("class_RowMovie");
            if (table.getColumnCount() != 7) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 7 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 7; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final RowMovieColumnInfo columnInfo = new RowMovieColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("poster")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'poster' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("poster") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'poster' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.posterIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'poster' is required. Either set @Required to field 'poster' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("panner")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'panner' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("panner") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'panner' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.pannerIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'panner' is required. Either set @Required to field 'panner' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("RelaseDate")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'RelaseDate' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("RelaseDate") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'RelaseDate' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.RelaseDateIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'RelaseDate' is required. Either set @Required to field 'RelaseDate' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("title")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'title' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("title") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'title' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.titleIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'title' is required. Either set @Required to field 'title' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("overView")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'overView' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("overView") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'overView' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.overViewIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'overView' is required. Either set @Required to field 'overView' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("voteAvg")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'voteAvg' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("voteAvg") != RealmFieldType.DOUBLE) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'double' for field 'voteAvg' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.voteAvgIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'voteAvg' does support null values in the existing Realm file. Use corresponding boxed type for field 'voteAvg' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The RowMovie class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_RowMovie";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static RowMovie createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        RowMovie obj = null;
        if (update) {
            Table table = realm.getTable(RowMovie.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("id")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new RowMovieRealmProxy(realm.schema.getColumnInfo(RowMovie.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = realm.createObject(RowMovie.class, null);
                } else {
                    obj = realm.createObject(RowMovie.class, json.getLong("id"));
                }
            } else {
                obj = realm.createObject(RowMovie.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
            } else {
                obj.setId((long) json.getLong("id"));
            }
        }
        if (json.has("poster")) {
            if (json.isNull("poster")) {
                obj.setPoster(null);
            } else {
                obj.setPoster((String) json.getString("poster"));
            }
        }
        if (json.has("panner")) {
            if (json.isNull("panner")) {
                obj.setPanner(null);
            } else {
                obj.setPanner((String) json.getString("panner"));
            }
        }
        if (json.has("RelaseDate")) {
            if (json.isNull("RelaseDate")) {
                obj.setRelaseDate(null);
            } else {
                obj.setRelaseDate((String) json.getString("RelaseDate"));
            }
        }
        if (json.has("title")) {
            if (json.isNull("title")) {
                obj.setTitle(null);
            } else {
                obj.setTitle((String) json.getString("title"));
            }
        }
        if (json.has("overView")) {
            if (json.isNull("overView")) {
                obj.setOverView(null);
            } else {
                obj.setOverView((String) json.getString("overView"));
            }
        }
        if (json.has("voteAvg")) {
            if (json.isNull("voteAvg")) {
                throw new IllegalArgumentException("Trying to set non-nullable field voteAvg to null.");
            } else {
                obj.setVoteAvg((double) json.getDouble("voteAvg"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static RowMovie createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        RowMovie obj = realm.createObject(RowMovie.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
                } else {
                    obj.setId((long) reader.nextLong());
                }
            } else if (name.equals("poster")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setPoster(null);
                } else {
                    obj.setPoster((String) reader.nextString());
                }
            } else if (name.equals("panner")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setPanner(null);
                } else {
                    obj.setPanner((String) reader.nextString());
                }
            } else if (name.equals("RelaseDate")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setRelaseDate(null);
                } else {
                    obj.setRelaseDate((String) reader.nextString());
                }
            } else if (name.equals("title")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setTitle(null);
                } else {
                    obj.setTitle((String) reader.nextString());
                }
            } else if (name.equals("overView")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setOverView(null);
                } else {
                    obj.setOverView((String) reader.nextString());
                }
            } else if (name.equals("voteAvg")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field voteAvg to null.");
                } else {
                    obj.setVoteAvg((double) reader.nextDouble());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static RowMovie copyOrUpdate(Realm realm, RowMovie object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        RowMovie realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(RowMovie.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, object.getId());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new RowMovieRealmProxy(realm.schema.getColumnInfo(RowMovie.class));
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static RowMovie copy(Realm realm, RowMovie newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        RowMovie realmObject = realm.createObject(RowMovie.class, newObject.getId());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setId(newObject.getId());
        realmObject.setPoster(newObject.getPoster());
        realmObject.setPanner(newObject.getPanner());
        realmObject.setRelaseDate(newObject.getRelaseDate());
        realmObject.setTitle(newObject.getTitle());
        realmObject.setOverView(newObject.getOverView());
        realmObject.setVoteAvg(newObject.getVoteAvg());
        return realmObject;
    }

    public static RowMovie createDetachedCopy(RowMovie realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RowMovie> cachedObject = (CacheData) cache.get(realmObject);
        RowMovie standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new RowMovie();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setId(realmObject.getId());
        standaloneObject.setPoster(realmObject.getPoster());
        standaloneObject.setPanner(realmObject.getPanner());
        standaloneObject.setRelaseDate(realmObject.getRelaseDate());
        standaloneObject.setTitle(realmObject.getTitle());
        standaloneObject.setOverView(realmObject.getOverView());
        standaloneObject.setVoteAvg(realmObject.getVoteAvg());
        return standaloneObject;
    }

    static RowMovie update(Realm realm, RowMovie realmObject, RowMovie newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setPoster(newObject.getPoster());
        realmObject.setPanner(newObject.getPanner());
        realmObject.setRelaseDate(newObject.getRelaseDate());
        realmObject.setTitle(newObject.getTitle());
        realmObject.setOverView(newObject.getOverView());
        realmObject.setVoteAvg(newObject.getVoteAvg());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("RowMovie = [");
        stringBuilder.append("{id:");
        stringBuilder.append(getId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{poster:");
        stringBuilder.append(getPoster() != null ? getPoster() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{panner:");
        stringBuilder.append(getPanner() != null ? getPanner() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{RelaseDate:");
        stringBuilder.append(getRelaseDate() != null ? getRelaseDate() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{title:");
        stringBuilder.append(getTitle() != null ? getTitle() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{overView:");
        stringBuilder.append(getOverView() != null ? getOverView() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{voteAvg:");
        stringBuilder.append(getVoteAvg());
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
        RowMovieRealmProxy aRowMovie = (RowMovieRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aRowMovie.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aRowMovie.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aRowMovie.row.getIndex()) return false;

        return true;
    }

}
