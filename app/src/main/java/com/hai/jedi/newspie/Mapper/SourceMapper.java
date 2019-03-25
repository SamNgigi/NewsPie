package com.hai.jedi.newspie.Mapper;

import com.hai.jedi.newspie.Models.FBSourceModel;
import com.hai.jedi.newspie.Models.Source;

public class SourceMapper extends FirebaseMapper<FBSourceModel, Source>{

    @Override
    public Source map(FBSourceModel fbSource){
        Source source = new Source();
        source.setSource_id(fbSource.getSource_id());
        source.setSaved_status(fbSource.getSaved_status());
        return source;
    }

}
