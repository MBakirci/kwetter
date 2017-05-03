package com.mehmet.kwetter.dao.impl;

import com.mehmet.kwetter.dao.FaulLanguageInterceptor;
import com.mehmet.kwetter.domain.Tweet;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehmet on 3-5-17.
 */
@Interceptor
@FaulLanguageInterceptor
public class TweetFaulLanguageInceptor {
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        List<String> cursewords = new ArrayList();
        cursewords.add("fuck");
        cursewords.add("ass");
        cursewords.add("nigger");
        cursewords.add("nigga");


        Object[] parameters = context.getParameters();
        Tweet t = (Tweet) parameters[0];

        String lowerCaseText = t.getTweet().toLowerCase();

        for (String curse : cursewords) {
            if (lowerCaseText.contains(curse)) {
                lowerCaseText = lowerCaseText.replace(curse, "******");
            }
        }

        t.setTweet(lowerCaseText);
        parameters[0] = t;
        context.setParameters(parameters);
        return context.proceed();
    }
}
