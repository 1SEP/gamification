package ru.fsep.enterprise.fseper.controllers.converters;

import com.inspiresoftware.lib.dto.geda.adapter.DtoToEntityMatcher;
import ru.fsep.enterprise.fseper.controllers.dto.PostsDto;
import ru.fsep.enterprise.fseper.models.Posts;

/**
 * Author Fedorov Juriy on 02.09.2015
 */
public class MatcherForPosts implements DtoToEntityMatcher<PostsDto, Posts> {
    public boolean match(PostsDto postsDto, Posts posts) {
        return true;
    }
}
