package com.example.kemal.androidappformvcapplication.BLL;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public interface IRepository<T>
{
    void insertAll(JSONArray jsonArray) throws JSONException;

    List<T> getList();

    T findById(int id);
}
