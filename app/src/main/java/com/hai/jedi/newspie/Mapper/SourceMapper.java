package com.hai.jedi.newspie.Mapper;

import com.hai.jedi.newspie.Models.FBSourceModel;
import com.hai.jedi.newspie.Models.Source;

public class SourceMapper extends FirebaseMapper<FBSourceModel, Source>{

    @Override
    public Source map(FBSourceModel fbSource){
        Source source = new Source();
        source.setSaved_status(fbSource.getSaved_status());
        source.setSource_id(fbSource.getSource_id());
        source.setSource_Uid(fbSource.getSource_Uid());
        source.setSource_category(fbSource.getSource_category());
        source.setSource_name(fbSource.getSource_name());
        source.setSource_url(fbSource.getSource_url());
        return source;
    }

}
