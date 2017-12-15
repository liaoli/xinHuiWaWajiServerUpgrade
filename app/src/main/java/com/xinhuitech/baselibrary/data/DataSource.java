package com.xinhuitech.baselibrary.data;

import com.xinhuitech.baselibrary.data.mapper.EntityToModelMapper;


/**
 * Created by Administrator on 2017/11/30.
 */

public class DataSource<E> {
    protected EntityToModelMapper<E> modelMapper;


    public DataSource(EntityToModelMapper<E> modelMapper) {
        this.modelMapper = modelMapper;
    }


    public interface DataCallback<M> {

        void onTransformed(M model);

        void onTransformFail(Exception ex);
    }


}
