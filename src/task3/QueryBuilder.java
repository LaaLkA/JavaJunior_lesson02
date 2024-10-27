package task3;

import java.lang.reflect.Field;

public class QueryBuilder {

    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query
                    .append(tableAnnotation.name())
                    .append(" (");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    query.append(columnAnnotation.name()).append(", ");
                }
            }
            if (query.charAt(query.length() - 2) == ',')
                query.delete(query.length() - 2, query.length() - 1);

            query.append(") VALUES (");

            for (Field field : fields){
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    query.append("'").append(field.get(obj)).append("'").append(", ");
                }
            }
            if (query.charAt(query.length() - 2) == ',')
                query.delete(query.length() - 2, query.length() - 1);
            query.append(")");
            return query.toString();
        } else {
            return null;
        }
    }
}
