package repositories.interfaces;

import db.interfaces.IDB;
import jakarta.inject.Inject;
import repositories.interfaces.base.IRepository;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractRepository<T> implements IRepository<T> {
    @Inject
    public IDB db;
    protected String tableName = null;
    protected List<String> fields = null;
    protected List<Type> fieldsTypes = null;

    public AbstractRepository() throws InstantiationException, IllegalAccessException {
        fillFields();
    }

    private T createInstance() throws IllegalAccessException, InstantiationException {
        T obj = ((Class<T>)((ParameterizedType)this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();

        return obj;
    }

    protected void fillFields() throws IllegalAccessException, InstantiationException {
        T obj = createInstance();

        if(tableName == null)
            tableName = obj.getClass().getName().toLowerCase() + "s";

        if(fields == null){
            fields = new ArrayList<>();
            for(Field field : obj.getClass().getFields()){
                fields.add(field.getName());
            }
        }

        if(fieldsTypes == null){
            fieldsTypes = new ArrayList<>();
            for(Field field : obj.getClass().getFields()){
                fieldsTypes.add(field.getType());
            }
        }
    }

    private String getFieldsList(){
        String res = "";
        for(String str : fields) {
            res += str + ", ";
        }
        res += "$";

        return res.replace(", $", "");
    }

    private String getQuestions(){
        String res = "?";
        for(int i = 1; i < fields.size(); i++) {
            res += ",?";
        }

        return res;
    }

    @Override
    public boolean create(T user) {
        Connection con = null;
        try {
            con = db.getConnection();
            String fieldsStr = getFieldsList().replace("id, ", "");
            String sql = "INSERT INTO " + tableName + "(" + fieldsStr + ") VALUES (" + getQuestions() + ")";
            PreparedStatement st = con.prepareStatement(sql);

            for(int i = 1; i <= fields.size(); i++){
                if(fields.get(i - 1).equals("id"))
                    continue;

                Object value = user.getClass().getField(fields.get(i - 1)).get(user);
                Type type = fieldsTypes.get(i - 1);
                if(type.equals(String.class)){
                    st.setString(i, value.toString());
                }
                else if(type.equals(Integer.TYPE)){
                    st.setInt(i,(Integer)value);
                }
                else if(type.equals(Double.TYPE)){
                    st.setDouble(i, (Double)value);
                }
                else if(type.equals(Boolean.TYPE)){
                    st.setBoolean(i, (Boolean)value);
                }
            }

            st.execute();
            return true;
        } catch (SQLException | NoSuchFieldException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public T get(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT " + getFieldsList() + " FROM " + tableName + " WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                T obj = createInstance();

                for(int i = 1; i <= fields.size(); i++){
                    if(fieldsTypes.get(i - 1).equals(String.class)){
                        obj.getClass().getField(fields.get(i - 1)).set(obj, rs.getString(fields.get(i - 1)));
                    }
                    else if(fieldsTypes.get(i - 1).equals(Integer.class)){
                        obj.getClass().getField(fields.get(i - 1)).setInt(obj, rs.getInt(fields.get(i - 1)));
                    }
                    else if(fieldsTypes.get(i - 1).equals(Double.class)) {
                        obj.getClass().getField(fields.get(i - 1)).setDouble(obj, rs.getDouble(fields.get(i - 1)));
                    }
                    else if(fieldsTypes.get(i - 1).equals(Boolean.class)){
                        obj.getClass().getField(fields.get(i - 1)).setBoolean(obj, rs.getBoolean(fields.get(i - 1)));
                    }
                }

                return obj;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT " + getFieldsList() + " FROM " + tableName;
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<T> objs = new ArrayList<>();
            while (rs.next()) {
                T obj = createInstance();

                for(int i = 2; i <= fields.size(); i++){
                    if(fieldsTypes.get(i - 1).equals(String.class)){
                        obj.getClass().getField(fields.get(i - 1)).set(obj, rs.getString(fields.get(i - 1)));
                    }
                    else if(fieldsTypes.get(i - 1).equals(Integer.class)){
                        obj.getClass().getField(fields.get(i - 1)).setInt(obj, rs.getInt(fields.get(i - 1)));
                    }
                    else if(fieldsTypes.get(i - 1).equals(Double.class)) {
                        obj.getClass().getField(fields.get(i - 1)).setDouble(obj, rs.getDouble(fields.get(i - 1)));
                    }
                    else if(fieldsTypes.get(i - 1).equals(Boolean.class)){
                        obj.getClass().getField(fields.get(i - 1)).setBoolean(obj, rs.getBoolean(fields.get(i - 1)));
                    }
                }

                objs.add(obj);
            }

            return objs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM " + tableName + " WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            st.execute();

            return st.getUpdateCount() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }
}
