package com.mehmet.kwetter.batch;

import com.mehmet.kwetter.domain.User;
import com.mehmet.kwetter.service.UserService;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mehmet on 3-5-17.
 */
@Named
public class DeleteReader extends AbstractItemReader {

    @Inject
    UserService userService;

    @Override
    public User readItem() {
        for (User u : userService.getUsers()) {
            if(olderThanToday(u.getRegisterationDate()) && !u.isActivated())
            return u;
        }
        return null;
    }

    private boolean olderThanToday(Calendar givenDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_WEEK, -1);
        boolean result = givenDate.after(cal);
        return result;
    }
}
