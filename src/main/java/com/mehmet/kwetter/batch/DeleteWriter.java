package com.mehmet.kwetter.batch;

import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.service.UserService;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by mehmet on 3-5-17.
 */
@Named
public class DeleteWriter extends AbstractItemWriter {
    @Inject
    UserService userService;

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            userService.deleteNotActivated((User) item);
        }
    }
}
