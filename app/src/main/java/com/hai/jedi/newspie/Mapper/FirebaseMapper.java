package com.hai.jedi.newspie.Mapper;

import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class FirebaseMapper<Entity, Model> implements MapperInterface<Entity, Model>{

    public Model map(DataSnapshot dataSnapshot){
        Entity entity = dataSnapshot.getValue(getEntityClass());
        /* *
        * This is a recusive function that maps whatever entity is to the Model tha
        * calls the function I think
        * */
        return map(entity);
    }

    public List<Model> mapList(DataSnapshot dataSnapshot){
        List<Model> list = new ArrayList<>();
        for(DataSnapshot data : dataSnapshot.getChildren()){
            /*
            * We call our map function from above to get the return type
            * of data.
            * */
            list.add(map(data));
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    private Class<Entity> getEntityClass(){
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        assert superclass != null;
        return (Class<Entity>) superclass.getActualTypeArguments()[0];
    }


}
