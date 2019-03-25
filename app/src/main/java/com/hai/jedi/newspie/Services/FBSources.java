package com.hai.jedi.newspie.Services;

import com.hai.jedi.newspie.Constants;
import com.hai.jedi.newspie.Mapper.SourceMapper;
import com.hai.jedi.newspie.Models.Source;
import com.hai.jedi.newspie.Utils.FDBService;

public class FBSources extends FDBService<Source> {

    public FBSources(){
        super(new SourceMapper());
    }

    @Override
    protected String getRootNode(){
        return Constants.FIREBASE_SOURCE_BOOKMARKS;
    }
}
