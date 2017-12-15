package com.xinhuitech.baselibrary.data;

import com.xinhuitech.baselibrary.data.mapper.EntityToModelMapper;

/**
 * Created by Administrator on 2017/12/9.
 */

public class BaseDataSource<E, M> extends DataSource<E> {
    protected DataSource.DataCallback<M> dataCallback;

    public BaseDataSource(EntityToModelMapper modelMapper) {
        super(modelMapper);
    }

    public void setDataCallback(DataCallback<M> dataCallback) {
        this.dataCallback = dataCallback;
    }
}
