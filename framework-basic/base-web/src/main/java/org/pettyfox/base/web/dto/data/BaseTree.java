package org.pettyfox.base.web.dto.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public abstract class BaseTree<T, E> implements Serializable {

    private E data;
    private T id;
    private T pId;
    private String label;
    private List<BaseTree<T, E>> children;
}
