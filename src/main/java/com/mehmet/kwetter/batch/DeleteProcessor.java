package com.mehmet.kwetter.batch;

import com.mehmet.kwetter.domain.User;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

/**
 * Created by mehmet on 3-5-17.
 */
@Named
public class DeleteProcessor implements ItemProcessor {
    @Override
    public Object processItem(Object item) throws Exception {
        User u = (User) item;
        u.setActivationCode(null);
        u.setDisabled(true);
        return u;
    }
}
