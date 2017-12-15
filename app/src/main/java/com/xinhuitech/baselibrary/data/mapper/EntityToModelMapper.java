package com.xinhuitech.baselibrary.data.mapper;

import com.xinhuitech.baselibrary.domain.Model;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */

public interface EntityToModelMapper<E> {

    <T extends Model> T transform(E entity);

    <T extends Model> List<T> transformToList(E entity);
}
